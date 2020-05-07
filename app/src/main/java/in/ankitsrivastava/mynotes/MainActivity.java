package in.ankitsrivastava.mynotes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    CustomAdapter adapter;
    GridView gv;
    SQLiteDatabase notesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notesDB = createOrGetDatabase();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            Toast.makeText(this, "Search Item", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private SQLiteDatabase createOrGetDatabase(){
        SQLiteDatabase notesDB = openOrCreateDatabase("NOTES_DB", MODE_PRIVATE, null);
        notesDB.execSQL("CREATE TABLE IF NOT EXISTS NOTES " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title VARCHAR,Content VARCHAR);");
        return notesDB;
    }

    private int getRowCount(SQLiteDatabase notesDB){
        Cursor resultSet = notesDB.rawQuery("Select * from NOTES",null);
        int i = 0;
        while(resultSet.moveToNext()){
            i++;
        }
        return i;
    }

    public void updateContent(int id, String title, String content){
        ContentValues cv = new ContentValues();
        cv.put("Title", title);
        cv.put("Content", content);
        notesDB.update("NOTES", cv, "id="+id, null);
    }

    private ArrayList<NoteValue> getData(){
        ArrayList<NoteValue> notes = new ArrayList<>();
        Cursor resultSet = createOrGetDatabase().rawQuery("Select * from NOTES",null);
        while(resultSet.moveToNext()){
            NoteValue note = new NoteValue();
            note.setId(Integer.parseInt(resultSet.getString(0)));
            note.setTitle(resultSet.getString(1));
            note.setContent(resultSet.getString(2));
            notes.add(note);
        }
        return notes;
    }

    public void startMainStuff(View mainView){
        if(getRowCount(createOrGetDatabase()) > 0){
            if(mainView != null){
                ImageView mainImage = mainView.findViewById(R.id.mainpage_img);
                TextView mainTitle = mainView.findViewById(R.id.textview_first);
                TextView mainDesc = mainView.findViewById(R.id.mainpage_desc);
                mainTitle.setText("");
                mainDesc.setText("");
                mainImage.setVisibility(View.GONE);
                gv= (GridView) mainView.findViewById(R.id.gv);

                adapter=new CustomAdapter(this, getData());
                gv.setAdapter(adapter);
            }
        }
    }

    public void startOpenNoteActivity(int id, String title, String content){
        Intent intent = new Intent(MainActivity.this, OpenNoteActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        startActivity(intent);
    }
}
