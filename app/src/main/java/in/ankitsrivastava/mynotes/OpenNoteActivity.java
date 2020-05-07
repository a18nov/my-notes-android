package in.ankitsrivastava.mynotes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import android.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class OpenNoteActivity extends AppCompatActivity {

    SQLiteDatabase notesDB = null;
    EditText titleText,contentText;
    String mTitle, mContent;
    int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_note_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar_create);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleText = (EditText) findViewById(R.id.open_title);
        contentText = (EditText) findViewById(R.id.open_content);
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        mTitle = intent.getStringExtra("title");
        mContent = intent.getStringExtra("content");
        titleText.setText(intent.getStringExtra("title"));
        contentText.setText(intent.getStringExtra("content"));

        notesDB = createOrGetDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_open, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.open_add_note) {
            updateContent(notesDB, mId, titleText.getText().toString(), contentText.getText().toString());
            finish();
            return true;
        } else if (id == R.id.open_delete) {
            deleteContent(notesDB, mId);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        updateContent(notesDB, mId, titleText.getText().toString(), contentText.getText().toString());
        onBackPressed();
        return true;
    }

    private SQLiteDatabase createOrGetDatabase(){
        SQLiteDatabase notesDB = openOrCreateDatabase("NOTES_DB", MODE_PRIVATE, null);
        notesDB.execSQL("CREATE TABLE IF NOT EXISTS NOTES " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title VARCHAR,Content VARCHAR);");
        return notesDB;
    }

    public void updateContent(SQLiteDatabase notesDB, int id, String title, String content){
        notesDB.execSQL("UPDATE NOTES SET Title='" + title +"' WHERE ID=" + id);
        notesDB.execSQL("UPDATE NOTES SET Content='" + content +"' WHERE ID=" + id);
    }

    public void deleteContent(SQLiteDatabase notesDB, int id){
        notesDB.execSQL("DELETE FROM NOTES WHERE ID=" + id);
    }
}
