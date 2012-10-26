package bunk.score.count;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends Activity {
	
    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.startscreen);
	     	
	    }
	    
	    public void addSubject (View v) {
	    	Intent i = new Intent (StartScreen.this, AddSubject.class);
    		startActivity(i);
	    }
	    
	    public void viewScore (View v) {
	    	Intent i = new Intent (StartScreen.this, ViewScore.class);
	    	startActivity(i);
	    }
}
