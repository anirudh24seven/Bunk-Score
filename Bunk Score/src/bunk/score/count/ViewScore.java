package bunk.score.count;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ViewScore extends Activity {
		
		protected EditText searchText;
		public SQLiteDatabase db;
		protected Cursor cursor;
		protected ListAdapter adapter;
		protected ListView subjectList;
		int menuId = Menu.FIRST;
		private int group1Id = 1;
		
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.viewscore);
	        db = (new DatabaseHelper(this)).getWritableDatabase();
	        
	        subjectList = (ListView) findViewById (R.id.list);
	   
			cursor = db.rawQuery("SELECT * FROM bunk_score WHERE subject LIKE ?", 
							new String[]{"%"});
			adapter = new SimpleCursorAdapter(
					this, 
					R.layout.subject_list_item, 
					cursor, 
					new String[] {"subject", "score"}, 
					new int[] {R.id.subject, R.id.score});
			
			subjectList.setAdapter(adapter);
			
			OnItemClickListener listener = new OnItemClickListener (){
				  @Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id){
					  Intent i = new Intent (ViewScore.this, SubjectEdit.class);
					  String score1 = ((TextView)view.findViewById(R.id.score)).getText().toString();
					  String subject1 = ((TextView)view.findViewById(R.id.subject)).getText().toString();
					  i.putExtra("subject", subject1);
					  i.putExtra("row", position);
					  i.putExtra("score", Integer.parseInt(score1));
					  db.close();
					  startActivity(i);   
					  finish();
				  }
			};
	    
			subjectList.setOnItemClickListener(listener);
			subjectList.setItemsCanFocus(true);
			
			
	    }
	    
	    
	    	    	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	menu.add(group1Id, menuId, menuId, "Remove Subjects");
	    	//menu.add(group1Id, menuId+1, menuId+1, "Exit");
	    	return super.onCreateOptionsMenu(menu); 
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	
	    	switch (item.getItemId()) {
	    	
	    	case 1:
	    		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    		    @Override
	    		    public void onClick(DialogInterface dialog, int which) {
	    		        switch (which){
	    		        case DialogInterface.BUTTON_POSITIVE:
	    		            //Yes button clicked
	    		        	db.delete("bunk_score", null, null);
	    		        	    		        		    			        
	    			        Intent i = new Intent (ViewScore.this, ViewScore.class);
	    		    		startActivity(i);	 
	    		    		db.close();
	    		    		finish();
	    			        
	    		            break;

	    		        case DialogInterface.BUTTON_NEGATIVE:
	    		            //No button clicked
	    		            break;
	    		        }
	    		    }
	    		};

	    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    		builder.setMessage("Are you sure you want to remove all subjects?").setPositiveButton("Yes", dialogClickListener)
	    		    .setNegativeButton("No", dialogClickListener).show();

	    		
	    		return true;
	    	
	    	case 2:
	    		DialogInterface.OnClickListener dialogClickListener2 = new DialogInterface.OnClickListener() {
	    		    @Override
	    		    public void onClick(DialogInterface dialog, int which) {
	    		        switch (which){
	    		        case DialogInterface.BUTTON_POSITIVE:
	    		            //Yes button clicked
	    		        	finish();
	    		            break;

	    		        case DialogInterface.BUTTON_NEGATIVE:
	    		            //No button clicked
	    		            break;
	    		        }
	    		    }
	    		};
	    		AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
	    		builder2.setMessage("Are you sure that you want to exit?").setPositiveButton("Yes", dialogClickListener2)
	    		    .setNegativeButton("No", dialogClickListener2).show();
	    		return true;
	    	
	    	default:
	    		break;
	    	
	    	}
	    	return super.onOptionsItemSelected(item);
	}
}

