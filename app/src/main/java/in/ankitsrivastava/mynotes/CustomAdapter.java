package in.ankitsrivastava.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<NoteValue> notes;

    public CustomAdapter(Context c, ArrayList<NoteValue> notes) {
        this.c = c;
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
            view= LayoutInflater.from(c).inflate(R.layout.card_model,viewGroup,false);
        }

        final NoteValue s= (NoteValue) this.getItem(i);

        TextView nameTxt= (TextView) view.findViewById(R.id.card_title);
        TextView propTxt= (TextView) view.findViewById(R.id.card_content);

        //BIND
        nameTxt.setText(s.getTitle());
        propTxt.setText(s.getContent());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, s.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
