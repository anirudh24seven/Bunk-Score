package bunk.score.count;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SubjectEdit extends Activity {
	String subject;
    Integer score;
    Integer position;
    public SQLiteDatabase db;
    int menuId = Menu.FIRST;
	private int group1Id = 1;
    
    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.subject_edit);
	        db = (new DatabaseHelper(this)).getWritableDatabase();
	        
	        Intent extras = getIntent();
	        if (extras != null) {
	            subject = extras.getStringExtra("subject");
	            score = extras.getIntExtra("score", 0);
	            position = extras.getIntExtra("row",0);
	        }
	        
	        
	        final TextView edit_subject = (TextView) findViewById(R.id.edit_subject);
	        final EditText edit_score = (EditText) findViewById(R.id.edit_score);
	        
	        edit_subject.setText(subject);
	        edit_score.setText(score.toString());
	        
	        ImageView update_score = (ImageView) findViewById(R.id.update_score);
	        
	        DatabaseHelper dbhelper = new DatabaseHelper(getBaseContext());
	    	final SQLiteDatabase db = dbhelper.getWritableDatabase();
	    	
	        update_score.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		
	        		Integer updated_score = Integer.parseInt(edit_score.getText().toString());
	        		
	        		ContentValues values = new ContentValues();
	        		values.put("score", updated_score);
	        		
	        		db.update("bunk_score", values, "subject = '" + subject +"'", null);
	        		
	        		Intent i = new Intent (SubjectEdit.this, ViewScore.class);
	        		db.close();
	        		startActivity(i);
		    		finish();
		    		
	        	}
	        });
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	menu.add(group1Id, menuId, menuId, "Remove this subject");
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
	    		        	String temp[] = {subject};
	    		        	db.delete("bunk_score", "subject=?", temp);	    		        	    		        		    			        
	    			        Intent i = new Intent (SubjectEdit.this, ViewScore.class);
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
	    		builder.setMessage("Are you sure you want to remove this subject?").setPositiveButton("Yes", dialogClickListener)
	    		    .setNegativeButton("No", dialogClickListener).show();	    		
	    		return true;
	    		
	    	default:
	    		break;
	    	
	    	}
	    	return super.onOptionsItemSelected(item);
	    }
}
