package bunk.score.count;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "bunks";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/*
		 * Create the employee table and populate it with sample data.
		 * In step 6, we will move these hardcoded statements to an XML document.
		 */
		String sql = "CREATE TABLE IF NOT EXISTS bunk_score (" +
						"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
						"subject TEXT," +
						"score INTEGER )";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS employees");
		onCreate(db);
	}

	protected void finalize(SQLiteDatabase db) throws Throwable {
        super.finalize();
        db.close();
    }
}
