package kor.co.mu.jin.theword;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.exception.KakaoException;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    CircleImageView pimg;
    TextView pnickname;
    TextView pmessage;

    String Nickname;
    String ImgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED){
                requestPermissions(permissions, 100);
            }
        }

        Session.getCurrentSession().addCallback(sessionCallback);
        LoginCheck();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "권한 거부됨, 일부 기능 사용 불가능", Toast.LENGTH_SHORT).show();
        }
    }

    private ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.whenlogincomplete, null);
            pimg = view.findViewById(R.id.login_img);
            pnickname = view.findViewById(R.id.login_nickname);
            pmessage = view.findViewById(R.id.login_message);

            requestUserInfo();

            new AlertDialog.Builder(LoginActivity.this).setView(view).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ImgUrl", ImgUrl);
                    editor.putString("NickName", Nickname);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }).create().show();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(LoginActivity.this, "로그인 실패, 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    public void clickNo_login(View view) {

        new AlertDialog.Builder(this).setMessage("정말 로그인 없이 진행하시겠습니까 ?").setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton("취소", null).create().show();
    }

    void requestUserInfo() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onSuccess(MeV2Response result) {
                UserAccount userAccount = result.getKakaoAccount();
                if(userAccount == null) return;

                Profile profile = userAccount.getProfile();
                if(profile == null) return;

                Nickname = profile.getNickname();
                ImgUrl = profile.getProfileImageUrl();

                pnickname.setText(Nickname);
                pmessage.setText("' " + Nickname + " ' " + "님 으로 진행하시겠습니까 ?");
                Glide.with(LoginActivity.this).load(ImgUrl).into(pimg);
            }
        });
    }

    void LoginCheck(){
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                return;
            }

            @Override
            public void onSuccess(MeV2Response result) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
