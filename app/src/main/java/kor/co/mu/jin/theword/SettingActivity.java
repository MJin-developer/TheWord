package kor.co.mu.jin.theword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {

    CircleImageView img_setting;
    TextView nickname_setting;
    TextView log_setting;
    LinearLayout linearLayout;

    Button noti_setting;
    int btn_setting = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        noti_setting = findViewById(R.id.noti_setting);
        img_setting = findViewById(R.id.img_setting);
        nickname_setting = findViewById(R.id.nickname_setting);
        log_setting = findViewById(R.id.log_setting);
        linearLayout = findViewById(R.id.linear1);

        CustomActionBar();
        requestUserInfo();
    }

    void CustomActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        View CustomActionbar = LayoutInflater.from(this).inflate(R.layout.settingtitlebar, null);

        actionBar.setCustomView(CustomActionbar);
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickNotiSetting(View view) {

        btn_setting++;
        if (btn_setting > 2) btn_setting = 1;

        switch (btn_setting) {
            case 1:
                noti_setting.setText("ON");
                noti_setting.setBackgroundResource(R.drawable.btn_on);
                break;
            case 2:
                noti_setting.setText("OFF");
                noti_setting.setBackgroundResource(R.drawable.btn_off);
                break;
        }
    }

    void requestUserInfo() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                log_setting.setText("로그인");
            }

            @Override
            public void onSuccess(MeV2Response result) {
                log_setting.setText("로그 아웃");
                UserAccount userAccount = result.getKakaoAccount();

                Profile profile = userAccount.getProfile();

                String nickname = profile.getNickname();
                String imgUrl = profile.getProfileImageUrl();

                nickname_setting.setText(nickname);
                Glide.with(SettingActivity.this).load(imgUrl).into(img_setting);
            }
        });
    }

    public void clickLog(View view) {

        if(log_setting.getText().equals("로그 아웃")){

            new AlertDialog.Builder(this).setMessage("정말 로그아웃 하시겠습니까 ?").setNegativeButton("취소", null).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onCompleteLogout() {
                            Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.fadein2, R.anim.fadeout);
                        }
                    });
                }
            }).create().show();
        }else if (log_setting.getText().equals("로그인")){
            Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void clickSetProfile(View view) {
        Intent intent = new Intent(SettingActivity.this, SetProfileActivity.class);
        startActivityForResult(intent, 10);
    }
}
