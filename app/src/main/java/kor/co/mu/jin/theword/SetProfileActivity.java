package kor.co.mu.jin.theword;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetProfileActivity extends AppCompatActivity {

    TextInputEditText textInputEditText;
    TextView nickname_profile_setting;
    CircleImageView img_profile_setting;

    String Uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        textInputEditText = findViewById(R.id.edit_text);
        nickname_profile_setting = findViewById(R.id.nickname_profile_setting);
        img_profile_setting = findViewById(R.id.img_profile_set);

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        nickname_profile_setting.setText(sharedPreferences.getString("NickName", null));
        Glide.with(SetProfileActivity.this).load(sharedPreferences.getString("ImgUrl", null)).into(img_profile_setting);

        textInputEditText.setText(nickname_profile_setting.getText().toString());
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nickname_profile_setting.setText(s);
                s = textInputEditText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void clickCancel(View view) {
        new AlertDialog.Builder(this).setTitle("취소").setMessage("정말 취소하시겠습니까 ?").setNegativeButton("취소", null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create().show();
    }

    public void clickSave(View view) {

        new AlertDialog.Builder(this).setMessage("저장하시겠습니까 ?").setNegativeButton("취소", null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NickName", nickname_profile_setting.getText().toString());
                editor.commit();

                if(Uri != null){
                    editor.putString("ImgUrl", Uri);
                    editor.commit();
                }

                Intent intent = getIntent();
                SetProfileActivity.this.setResult(RESULT_OK, intent);
                finish();
            }
        }).create().show();

    }

    public void changeProfileImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, 150);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 150 && resultCode == RESULT_OK){
            Uri ImgUri = data.getData();
            Uri = ImgUri.toString();
            Glide.with(this).load(ImgUri).into(img_profile_setting);
        }
    }

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }
}
