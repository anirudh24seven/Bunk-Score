package bunk.score.count;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubjectEdit extends Activity {
	String subject;
    Integer score;
    Integer position;
    
    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.subject_edit);
	        
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
	        
	        Button update_score = (Button) findViewById(R.id.update_score);
	        
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
}
