package com.kidslearning.kidsplay.smartkids.derdroid.preschool.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kidslearning.kidsplay.smartkids.derdroid.preschool.R;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.customclasses.Constant;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.interfaces.AdsCallback;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.interfaces.CallbackListener;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.utils.CommonConstantAd;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.utils.Utils;

public class SplashActivity extends AppCompatActivity implements CallbackListener, AdsCallback {
    /*For Internet*/
    @Override
    public void onSuccess() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onRetry() {
        callApi();
    }


    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        context = this;
        callApi();

    }

    public void callApi() {
        if (Utils.isNetworkConnected(this)) {
            successCall();
        } else {
            Utils.openInternetDialog(this, true,this);
        }

        handler.postDelayed(myRunnable, 10000);
    }

    private void successCall() {
        if (Utils.getPref(this, Constant.SPLASH_SCREEN_COUNT, 1) == 1) {
            Log.e("TAG", "successCall::::IFFFFF " + Utils.getPref(this, Constant.SPLASH_SCREEN_COUNT, 1));
            Utils.setPref(this, Constant.SPLASH_SCREEN_COUNT, 2);

            startNextActivity(1000);
        } else {
            Log.e("TAG", "successCall::::ELSEEE " + Utils.getPref(this, Constant.SPLASH_SCREEN_COUNT, 1));
            checkAd();
        }
    }

    private void checkAd() {
        if (Utils.getPref(this, Constant.STATUS_ENABLE_DISABLE, "").equals(Constant.ENABLE)) {
            if (Utils.getPref(this, Constant.AD_TYPE_FB_GOOGLE, "").equals(Constant.AD_GOOGLE)) {
                CommonConstantAd.googlebeforloadAd(this);
                Log.e("TAG", "checkAd:Google::::  ");
            } else if (Utils.getPref(this, Constant.AD_TYPE_FB_GOOGLE, "").equals(Constant.AD_FACEBOOK)) {
                CommonConstantAd.facebookbeforeloadFullAd(this);
                Log.e("TAG", "checkAd:Facebook:::: ");
            }
            if (Utils.getPref(this, Constant.STATUS_ENABLE_DISABLE, "").equals(Constant.ENABLE)) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (Utils.getPref(SplashActivity.this, Constant.AD_TYPE_FB_GOOGLE, "").equals(Constant.AD_GOOGLE)) {
                            CommonConstantAd.showInterstitialAdsGoogle(SplashActivity.this,SplashActivity.this);
                        } else if (Utils.getPref(SplashActivity.this, Constant.AD_TYPE_FB_GOOGLE, "").equals(Constant.AD_FACEBOOK)) {
                            CommonConstantAd.showInterstitialAdsFacebook(SplashActivity.this);
                        } else {
                            startNextActivity(0);
                        }
                    }

                }, 3000);
                Utils.setPref(this, Constant.SPLASH_SCREEN_COUNT, 1);

            } else {
                startNextActivity(0);
            }
        } else {
            Utils.setPref(this, Constant.SPLASH_SCREEN_COUNT, 1);
            Log.e("TAG", "checkAd:ELSE:::: " + Utils.getPref(this, Constant.STATUS_ENABLE_DISABLE, ""));
            startNextActivity(1000);
        }
    }


    public void startNextActivity(Integer time) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, time);
    }


    /*For ads*/
    @Override
    public void adLoadingFailed() {
        startNextActivity(0);
    }

    @Override
    public void adClose() {
        startNextActivity(0);
    }

    @Override
    public void startNextScreen() {
        startNextActivity(0);
    }

    private Boolean isLoaded = false;

    @Override
    public void onLoaded() {
        isLoaded = true;
    }

    private Handler handler = new Handler();
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            if (Utils.isNetworkConnected(SplashActivity.this)) {
                if (!isLoaded) {
                    startNextActivity(0);
                }
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(myRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(myRunnable);
    }
}

