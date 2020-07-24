package kor.co.mu.jin.theword;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMyStroy extends Fragment {

    FloatingActionButton btn_write;
    ArrayList<MYStoryItemList> itemLists = new ArrayList<>();
    RecyclerView recycler;
    RecyclerAdapter5 adapter;

    TextView an_text;
    TextView my_title;
    TextView my_num;
    TextView my_name;
    CircleImageView my_profileImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_story, container, false);
        an_text = v.findViewById(R.id.an_text);
        btn_write = v.findViewById(R.id.btn_write);
        my_title = v.findViewById(R.id.my_title);
        my_num = v.findViewById(R.id.my_num);
        my_name = v.findViewById(R.id.my_name);
        my_profileImg = v.findViewById(R.id.my_profileimg);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUserInfo();
            }
        });
        recycler = v.findViewById(R.id.recycler_m);
        adapter = new RecyclerAdapter5(itemLists, getActivity());
        recycler.setAdapter(adapter);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = firebaseDatabase.getReference();
        DatabaseReference storyRef = rootRef.child("A MYSTORY");
        storyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemLists.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MYStoryItemList itemList = snapshot.getValue(MYStoryItemList.class);
                    itemLists.add(0, itemList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(itemLists.size() < 1) an_text.setVisibility(View.VISIBLE);
        else an_text.setVisibility(View.GONE);

        return v;
    }

    void requestUserInfo() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getActivity(), "로그인이 되어있지 않습니다.\n글 작성 기능이 제한됩니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                new AlertDialog.Builder(getActivity()).setMessage("글 작성 페이지로 이동하시겠습니까 ??").setNegativeButton("취소", null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), MYStroyWriteActivity.class);
                        startActivity(intent);
                    }
                }).create().show();
                btn_write.setEnabled(true);
            }
        });
    }

}
