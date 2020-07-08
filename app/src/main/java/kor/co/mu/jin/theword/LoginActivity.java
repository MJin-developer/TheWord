package kor.co.mu.jin.theword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Session.getCurrentSession().addCallback(sessionCallback);
    }

    private ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }
}
