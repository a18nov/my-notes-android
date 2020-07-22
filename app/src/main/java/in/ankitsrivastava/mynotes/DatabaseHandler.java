package in.ankitsrivastava.mynotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseHandler extends MainActivity {

    public DatabaseHandler(){
        this.createOrGetDatabase();
    }

    private SQLiteDatabase createOrGetDatabase(){
        SQLiteDatabase notesDB = openOrCreateDatabase("NOTES_DB", MODE_PRIVATE, null);
        notesDB.execSQL("CREATE TABLE IF NOT EXISTS NOTES " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title VARCHAR,Content VARCHAR);");
        return notesDB;
    }

    private void insertIntoDB(SQLiteDatabase notesDB, String title, String content){
        ContentValues vals = new ContentValues();
        vals.put("Title", title);
        vals.put("Content", content);
        notesDB.insert("NOTES", null, vals);
    }

    private int getRowCount(SQLiteDatabase notesDB){
        Cursor resultSet = notesDB.rawQuery("Select * from NOTES",null);
        int i = 0;
        while(resultSet.moveToNext()){
            i++;
        }
        return i;
    }
}
