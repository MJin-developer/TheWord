package kor.co.mu.jin.theword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentMain extends Fragment {

    ArrayList<CustomList> customLists = new ArrayList<>();
    RecyclerView famous;
    RecyclerView popular;
    RecyclerView good;
    RecyclerView funny;
    RecyclerAdapter adapter_famous;
    RecyclerAdapter2 adapter_popular;
    RecyclerAdapter3 adapter_good;
    RecyclerAdapter4 adapter_funny;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }

}
