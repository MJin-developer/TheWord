package kor.co.mu.jin.theword;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class SetProfileActivity extends AppCompatActivity {

    TextInputEditText textInputEditText;
    TextView nickname_profile_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        textInputEditText = findViewById(R.id.edit_text);
        nickname_profile_setting = findViewById(R.id.nickname_profile_setting);

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
}
