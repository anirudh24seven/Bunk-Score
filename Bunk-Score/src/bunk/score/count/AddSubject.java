package bunk.score.count;

import java.io.BufferedReader;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddSubject extends Activity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.addsubject);
        
         
        
        Button add_subject = (Button) findViewById(R.id.searchButton);
        

        DatabaseHelper dbhelper = new DatabaseHelper(getBaseContext());
    	final SQLiteDatabase db = dbhelper.getWritableDatabase();
    	
        add_subject.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		EditText e = (EditText) findViewById(R.id.searchText);
            	String txt = (String) e.getText().toString();
        		ContentValues values = new ContentValues();
        		values.put("subject", txt);
        		values.put("score", 0);
        		db.insert("bunk_score", "subject", values);
	    		finish();
        	}
        });

    }

}
