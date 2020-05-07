package in.ankitsrivastava.mynotes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.logging.Logger;

public class MainPageFragment extends Fragment {

    View mView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_page_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView = view;
        runMainStuff(view);
    }

    public void runMainStuff(View view){
        Activity activity = getActivity();
        if(activity instanceof MainActivity){
            MainActivity myactivity = (MainActivity) activity;
            myactivity.startMainStuff(view);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        runMainStuff(mView);
    }
}
