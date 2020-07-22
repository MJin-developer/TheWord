package kor.co.mu.jin.theword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentFunny extends Fragment {

    ArrayList<CustomList> customLists = new ArrayList<>();
    RecyclerAdapter4 adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_funny, container, false);
        progressBar = v.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_f);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new RecyclerAdapter4(customLists, getActivity());
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = firebaseDatabase.getReference();
        DatabaseReference famousRef = rootRef.child("A FUNNY");

        famousRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                customLists.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CustomList c = snapshot.getValue(CustomList.class);
                    customLists.add(0, c);
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

}
