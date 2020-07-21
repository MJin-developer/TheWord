package kor.co.mu.jin.theword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FamousItemClickActivity extends AppCompatActivity {

    ArrayList<subwordList> subwordLists = new ArrayList<>();
    RecyclerView recyclerView;
    subwordAdapter adapter;
    ProgressBar progressBar;

    YouTubePlayerFragment youTubePlayerFragment;
    TextView TITLE;
    TextView CONTENT;
    TextView favoriteNum;
    TextView subwordNum;
    TextView writesubword;
    LinearLayout btn_up_favorite;
    ImageView favorite;
    EditText subword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famous_item_click);
        CustomActionBar();
        progressBar = findViewById(R.id.progress_bar);
        TITLE = findViewById(R.id.item_title);
        CONTENT = findViewById(R.id.item_content);
        favoriteNum = findViewById(R.id.item_favoriteNum);
        subwordNum = findViewById(R.id.item_subwordNum);
        btn_up_favorite = findViewById(R.id.btn_up_favorite);
        favorite = findViewById(R.id.iv1);
        subword = findViewById(R.id.et1);
        writesubword = findViewById(R.id.tv3);
        progressBar.setVisibility(View.VISIBLE);

        requestUserInfo();

        recyclerView = findViewById(R.id.sub_word);
        adapter = new subwordAdapter(subwordLists, this);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        TITLE.setText(title);

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        int fswitch = sharedPreferences.getInt(TITLE.getText().toString() + "Switch", 0);
        if(fswitch == 1){
            favorite.setImageResource(R.drawable.favorite_true);
            btn_up_favorite.setClickable(false);
        }else if (fswitch == 0){
            favorite.setImageResource(R.drawable.favorite);
            btn_up_favorite.setClickable(true);
        }


        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeFragment);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootref = firebaseDatabase.getReference();
        DatabaseReference dataref = rootref.child("A FAMOUS");
        dataref.orderByChild("title").equalTo(TITLE.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    final ItemdataList itemdataList = snapshot.getValue(ItemdataList.class);
                    CONTENT.setText(itemdataList.content);
                    favoriteNum.setText(itemdataList.favoriteNumber+"");
                    subwordNum.setText(itemdataList.subwordNumber+"");
                    youTubePlayerFragment.initialize("item", new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.cueVideo(itemdataList.youtubeID);
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference subref = rootref.child(TITLE.getText().toString() + " SUB");
        subref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                subwordLists.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    subwordList subList = snapshot.getValue(subwordList.class);
                    subwordLists.add(subList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void CustomActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        View CustomActionbar = LayoutInflater.from(this).inflate(R.layout.famousitemtitlebar, null);

        actionBar.setCustomView(CustomActionbar);
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickUpFavorite(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TITLE.getText().toString() + "Switch", 1);
        favorite.setImageResource(R.drawable.favorite_true);
        btn_up_favorite.setClickable(false);
        editor.commit();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootref = firebaseDatabase.getReference();
        final DatabaseReference dataref = rootref.child("A FAMOUS");
        dataref.orderByChild("title").equalTo(TITLE.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ItemdataList itemdataList = snapshot.getValue(ItemdataList.class);
                    int a = itemdataList.favoriteNumber;
                    a++;
                    String s = snapshot.getKey();
                    Map<String, Object> map = new HashMap<>();
                    map.put(s + "/favoriteNumber", a);
                    dataref.updateChildren(map);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void requestUserInfo() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(FamousItemClickActivity.this, "로그인이 되어있지 않습니다.\n댓글 작성 기능이 제한됩니다.", Toast.LENGTH_SHORT).show();
                subword.setEnabled(false);
                writesubword.setClickable(false);
            }

            @Override
            public void onSuccess(MeV2Response result) {
                subword.setEnabled(true);
                writesubword.setClickable(true);
            }
        });
    }

    public void WriteSubword(View view) {

        if(subword.length() <= 0 ){
            Toast.makeText(this, "댓글을 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }else {
            new AlertDialog.Builder(this).setMessage("해당 내용으로 댓글을 등록하시겠습니까 ??").setNegativeButton("취소",null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                    DatabaseReference sub = databaseReference.child(TITLE.getText().toString() + " SUB");

                    SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                    String name = sharedPreferences.getString("NickName", null) + " : ";
                    String imgurl = sharedPreferences.getString("ImgUrl", null);
                    String content = subword.getText().toString();

                    subwordList subwordList = new subwordList(imgurl, name, content);
                    sub.push().setValue(subwordList);
                    subword.setText("");

                    final DatabaseReference dataref = databaseReference.child("A FAMOUS");
                    dataref.orderByChild("title").equalTo(TITLE.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                ItemdataList itemdataList = snapshot.getValue(ItemdataList.class);
                                int a = itemdataList.subwordNumber;
                                a++;
                                String s = snapshot.getKey();
                                Map<String, Object> map = new HashMap<>();
                                map.put(s + "/subwordNumber", a);
                                dataref.updateChildren(map);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }).create().show();
        }

    }
}
