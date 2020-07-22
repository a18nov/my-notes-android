package in.ankitsrivastava.mynotes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mainActivityContext;
    ArrayList<NoteValue> notes;

    public CustomAdapter(Context mainActivityContext, ArrayList<NoteValue> notes) {
        this.mainActivityContext = mainActivityContext;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(mainActivityContext).inflate(R.layout.card_model,viewGroup,false);
        }

        final NoteValue s= (NoteValue) this.getItem(i);

        TextView titleText= (TextView) view.findViewById(R.id.card_title);
        TextView contentText= (TextView) view.findViewById(R.id.card_content);

        //BIND
        titleText.setText(s.getTitle());
        contentText.setText(s.getContent());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivityContext instanceof MainActivity) {
                    Log.e("function", "getView - CustomAdapter");
                      ((MainActivity) mainActivityContext).startOpenNoteActivity(s.getId(), s.getTitle(), s.getContent());
                }
            }
        });

        return view;
    }
}
