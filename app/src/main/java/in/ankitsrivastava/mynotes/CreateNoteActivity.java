package in.ankitsrivastava.mynotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateNoteActivity extends AppCompatActivity {

    SQLiteDatabase notesDB = null;
    EditText titleText,contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar_create);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleText = (EditText) findViewById(R.id.create_title);
        contentText = (EditText) findViewById(R.id.create_content);

        notesDB = createOrGetDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_note) {
            insertIntoDB(notesDB, titleText.getText().toString(), contentText.getText().toString());
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        insertIntoDB(notesDB, titleText.getText().toString(), contentText.getText().toString());
        onBackPressed();
        return true;
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
}
