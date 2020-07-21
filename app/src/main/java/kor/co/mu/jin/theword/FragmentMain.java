package kor.co.mu.jin.theword;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class FragmentMain extends Fragment {

    ArrayList<CustomList> customLists = new ArrayList<>();
    ArrayList<CustomList> Plist = new ArrayList<>();
    ArrayList<CustomList> Glist = new ArrayList<>();
    ArrayList<CustomList> Flist = new ArrayList<>();

    RecyclerView recycler_main;
    RecyclerAdapterMain adapter_f;

    RecyclerView recycler_main_popular;
    RecyclerAdapter2Main adapter_p;

    RecyclerView recycler_main_good;
    RecyclerAdapter3Main adapter_g;

    RecyclerView recycler_main_funny;
    RecyclerAdapter4Main adapter_y;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recycler_main = v.findViewById(R.id.recycler_main);
        adapter_f = new RecyclerAdapterMain(customLists, getActivity());
        recycler_main.setAdapter(adapter_f);

        recycler_main_popular = v.findViewById(R.id.recycler_main_popular);
        adapter_p = new RecyclerAdapter2Main(Plist, getActivity());
        recycler_main_popular.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_main_popular.setAdapter(adapter_p);

        recycler_main_good = v.findViewById(R.id.recycler_main_good);
        adapter_g = new RecyclerAdapter3Main(Glist, getActivity());
        recycler_main_good.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_main_good.setAdapter(adapter_g);

        recycler_main_funny = v.findViewById(R.id.recycler_main_funny);
        adapter_y = new RecyclerAdapter4Main(Flist, getActivity());
        recycler_main_funny.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_main_funny.setAdapter(adapter_y);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference RootRef = firebaseDatabase.getReference();
        DatabaseReference famousRef = RootRef.child("A FAMOUS");
        famousRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customLists.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CustomList customList = snapshot.getValue(CustomList.class);
                    customLists.add(0, customList);
                    adapter_f.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference popularRef = RootRef.child("A POPULAR");
        popularRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Plist.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CustomList plist = snapshot.getValue(CustomList.class);
                    Plist.add(0, plist);
                    adapter_p.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference goodRef = RootRef.child("A GOOD");
        goodRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glist.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CustomList glist = snapshot.getValue(CustomList.class);
                    Glist.add(0, glist);
                    adapter_g.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference funnyRef = RootRef.child("A FUNNY");
        funnyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Flist.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CustomList flist = snapshot.getValue(CustomList.class);
                    Flist.add(0, flist);
                    adapter_y.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

}
