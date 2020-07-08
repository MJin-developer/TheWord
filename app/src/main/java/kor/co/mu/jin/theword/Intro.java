package kor.co.mu.jin.theword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Intro extends AppCompatActivity {

    TextView text_intro_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        handler.sendEmptyMessageDelayed(0, 4500);

        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        text_intro_word = findViewById(R.id.text_intro_word);
        text_intro_word.startAnimation(animationIn);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Intent intent = new Intent(Intro.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
