package kor.co.mu.jin.theword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class Intro extends AppCompatActivity {

    TextView text_intro_word;
    IntrowordList introwordList = new IntrowordList();
// ㅋㄷㅋㄷ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        handler.sendEmptyMessageDelayed(0, 4500);

        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        text_intro_word = findViewById(R.id.text_intro_word);
        text_intro_word.setText(introwordList.returnWord());
        text_intro_word.startAnimation(animationIn);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Intent intent = new Intent(Intro.this, LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fadein2, R.anim.fadeout);
        }
    };

}
