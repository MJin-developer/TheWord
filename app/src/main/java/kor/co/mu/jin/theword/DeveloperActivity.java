package kor.co.mu.jin.theword;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeveloperActivity extends AppCompatActivity {

    Uri Uri;
    ImageView iv;
    EditText editText;
    String FILENAME;
    EditText youtubeID;
    EditText favoriteNum;
    EditText subwordNum;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        iv = findViewById(R.id.input_img);
        editText = findViewById(R.id.input_title);
        youtubeID = findViewById(R.id.input_youtubeID);
        favoriteNum = findViewById(R.id.input_favoriteNum);
        subwordNum = findViewById(R.id.input_subwordNum);
        content = findViewById(R.id.input_content);
    }

    public void clickchangeimg(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, 300);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 300 && resultCode == RESULT_OK){
            Uri ImgUri = data.getData();
            Uri = ImgUri;
            Glide.with(this).load(ImgUri).into(iv);
        }
    }

    public void confirm(View view) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = sdf.format(new Date()) + ".png";
        FILENAME = filename;
        StorageReference imgref = firebaseStorage.getReference("uploads/" + filename);
        UploadTask task = imgref.putFile(Uri);

        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(DeveloperActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void confirm2(View view) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference rootRef = firebaseStorage.getReference();
        final StorageReference imgRef = rootRef.child("uploads/" + FILENAME);
        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<android.net.Uri>() {
            @Override
            public void onSuccess(android.net.Uri uri) {
                Toast.makeText(DeveloperActivity.this, "URI 불러오기 성공", Toast.LENGTH_SHORT).show();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref = firebaseDatabase.getReference();
                DatabaseReference imgref = ref.child("A FAMOUS");

                String f = favoriteNum.getText().toString();
                int fn = Integer.parseInt(f);
                String s = subwordNum.getText().toString();
                int sn = Integer.parseInt(s);
                ItemdataList itemdataList = new ItemdataList(uri.toString(), editText.getText().toString(), fn, sn, youtubeID.getText().toString(), content.getText().toString());

                imgref.push().setValue(itemdataList);
            }
        });
    }

    public void confirm3(View view) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference rootRef = firebaseStorage.getReference();
        final StorageReference imgRef = rootRef.child("uploads/" + FILENAME);
        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<android.net.Uri>() {
            @Override
            public void onSuccess(android.net.Uri uri) {
                Toast.makeText(DeveloperActivity.this, "URI 불러오기 성공", Toast.LENGTH_SHORT).show();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref = firebaseDatabase.getReference();
                DatabaseReference imgref = ref.child("A POPULAR");

                String f = favoriteNum.getText().toString();
                int fn = Integer.parseInt(f);
                String s = subwordNum.getText().toString();
                int sn = Integer.parseInt(s);
                ItemdataList itemdataList = new ItemdataList(uri.toString(), editText.getText().toString(), fn, sn, youtubeID.getText().toString(), content.getText().toString());

                imgref.push().setValue(itemdataList);
            }
        });
    }

    public void confirm4(View view) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference rootRef = firebaseStorage.getReference();
        final StorageReference imgRef = rootRef.child("uploads/" + FILENAME);
        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<android.net.Uri>() {
            @Override
            public void onSuccess(android.net.Uri uri) {
                Toast.makeText(DeveloperActivity.this, "URI 불러오기 성공", Toast.LENGTH_SHORT).show();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref = firebaseDatabase.getReference();
                DatabaseReference imgref = ref.child("A GOOD");

                String f = favoriteNum.getText().toString();
                int fn = Integer.parseInt(f);
                String s = subwordNum.getText().toString();
                int sn = Integer.parseInt(s);
                ItemdataList itemdataList = new ItemdataList(uri.toString(), editText.getText().toString(), fn, sn, youtubeID.getText().toString(), content.getText().toString());

                imgref.push().setValue(itemdataList);
            }
        });
    }

    public void confirm5(View view) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference rootRef = firebaseStorage.getReference();
        final StorageReference imgRef = rootRef.child("uploads/" + FILENAME);
        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<android.net.Uri>() {
            @Override
            public void onSuccess(android.net.Uri uri) {
                Toast.makeText(DeveloperActivity.this, "URI 불러오기 성공", Toast.LENGTH_SHORT).show();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref = firebaseDatabase.getReference();
                DatabaseReference imgref = ref.child("A FUNNY");

                String f = favoriteNum.getText().toString();
                int fn = Integer.parseInt(f);
                String s = subwordNum.getText().toString();
                int sn = Integer.parseInt(s);
                ItemdataList itemdataList = new ItemdataList(uri.toString(), editText.getText().toString(), fn, sn, youtubeID.getText().toString(), content.getText().toString());

                imgref.push().setValue(itemdataList);
            }
        });
    }
}
