package kor.co.mu.jin.theword;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 카카오 SDK와 앱의 Application 연결
        KakaoSDK.init(new KakaoAdapter() {
            @Override
            public IApplicationConfig getApplicationConfig() {
                return new IApplicationConfig() {
                    @Override
                    public Context getApplicationContext() {
                        return MyApplication.this;
                    }
                };
            }
        });
    }
}
