package kor.co.mu.jin.theword;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeveloperActivity extends AppCompatActivity {

    String Uri;
    ImageView iv;
    EditText editText;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        iv = findViewById(R.id.input_img);
        editText = findViewById(R.id.input_title);
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
            Uri = ImgUri.toString();
            Glide.with(this).load(ImgUri).into(iv);
        }
    }

    public void confirm(View view) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference titleref = ref.child("Title");
        titleref.push().setValue(editText.getText().toString());

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference rootref = firebaseStorage.getReference();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = sdf.format(new Date()) + ".png";





    }
}
