package kor.co.mu.jin.theword;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MYStroyWriteActivity extends AppCompatActivity {

    EditText Etitle;
    EditText Econtent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_y_stroy_write);
        Etitle = findViewById(R.id.write_title);
        Econtent = findViewById(R.id.write_string);
    }


    public void clickCancel(View view) {
        new AlertDialog.Builder(this).setMessage("정말 취소하시겠습니까 ??").setNegativeButton("취소", null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create().show();
    }

    public void clickConfirm(View view) {

        if(Etitle.length() < 3){
            new AlertDialog.Builder(MYStroyWriteActivity.this).setMessage("제목이 너무 짧습니다.").setPositiveButton("확인", null).create().show();
        }

        if(Econtent.length() < 10){
            new AlertDialog.Builder(MYStroyWriteActivity.this).setMessage("내용이 너무 짧습니다.").setPositiveButton("확인", null).create().show();
        }

        if(Etitle.length() > 3 && Econtent.length() > 10){
            new AlertDialog.Builder(this).setMessage("작성을 완료하시겠습니까 ??").setNegativeButton("취소", null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference ref = firebaseDatabase.getReference();
                    DatabaseReference dataref = ref.child("A MYSTORY");

                    SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                    String name = sharedPreferences.getString("NickName", null);
                    String profileImg = sharedPreferences.getString("ImgUrl", null);
                    String title = Etitle.getText().toString();
                    String content = Econtent.getText().toString();
                    int showNum = 0;
                    int favoriteNum = 0;
                    int subNum = 0;

                    MYStoryItemList itemList = new MYStoryItemList(title, profileImg, name, content, showNum, favoriteNum, subNum);
                    dataref.push().setValue(itemList);
                    finish();
                }
            }).create().show();
        }
    }
}
