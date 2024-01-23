package com.kidslearning.kidsplay.smartkids.derdroid.preschool.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.customclasses.Constant;
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.interfaces.AdsCallback;

public class CommonConstantAd {

    private static AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }



    static InterstitialAd googleinterstitial;

    public static void googlebeforloadAd(Context context) {
        try {
            InterstitialAd.load(context, Utils.getPref(context, Constant.GOOGLE_INTERSTITIAL, ""), getAdRequest(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            super.onAdLoaded(interstitialAd);
                            googleinterstitial = interstitialAd;
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            googleinterstitial = null;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void showInterstitialAdsGoogle(Activity activity, final AdsCallback adsCallback) {
        try {
            if (googleinterstitial != null) {
                googleinterstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        googleinterstitial = null;
                        adsCallback.startNextScreen();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        adsCallback.onLoaded();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        googleinterstitial = null;
                        adsCallback.startNextScreen();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });

                googleinterstitial.show(activity);
            } else {
                adsCallback.startNextScreen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    static com.facebook.ads.InterstitialAd facebookinterstitialAd;
    private static AdsCallback facebookAdscallback;

    public static void facebookbeforeloadFullAd(Context context) {
        try {
            facebookinterstitialAd = new com.facebook.ads.InterstitialAd(context, Utils.getPref(context, Constant.FB_INTERSTITIAL, ""));
            facebookAdscallback = null;
            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    Log.e("TAG", "Interstitial ad displayed.");
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    Log.e("TAG", "Interstitial ad dismissed.");
                    facebookAdscallback.adClose();
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.e("TAG", "onError:Facebook :::::::::  " + adError.getErrorMessage() + "  " + adError.getErrorCode());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    Log.e("TAG", "Interstitial ad is loaded and ready to be displayed!");
                }

                @Override
                public void onAdClicked(Ad ad) {
                    Log.e("TAG", "Interstitial ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    Log.e("TAG", "Interstitial ad impression logged!");
                }
            };

            facebookinterstitialAd.loadAd(facebookinterstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showInterstitialAdsFacebook(AdsCallback facebookAdscallback) {
        facebookAdscallback = facebookAdscallback;
        if (facebookinterstitialAd != null) {
            if (facebookinterstitialAd.isAdLoaded()) {
                facebookinterstitialAd.show();
                facebookAdscallback.onLoaded();
            } else {
                facebookAdscallback.startNextScreen();
            }
        } else {
            facebookAdscallback.startNextScreen();
        }
    }


    public static void loadFacebookBannerAd(Context context, final LinearLayout banner_container) {
        AdView adView = new AdView(context, Utils.getPref(context, Constant.FB_BANNER, ""), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        banner_container.addView(adView);

        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.e("TAG", "onError:Fb:::: ${adError.errorCode}   ${adError.errorMessage}   " + adError.getErrorMessage() + "  " + adError.getErrorCode());
                banner_container.setVisibility(View.GONE);

            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.e("TAG", "onAdLoaded:::::: ");
                banner_container.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());
    }

    public static void loadBannerGoogleAd(Context context, final RelativeLayout llAdview) {
        final com.google.android.gms.ads.AdView adViewBottom = new com.google.android.gms.ads.AdView(context);
        adViewBottom.setAdSize(AdSize.BANNER);
        adViewBottom.setAdUnitId(Utils.getPref(context, Constant.GOOGLE_BANNER, ""));
        llAdview.addView(adViewBottom);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewBottom.loadAd(adRequest);
        adViewBottom.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adViewBottom.setVisibility(View.VISIBLE);
                llAdview.setVisibility(View.VISIBLE);
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                llAdview.setVisibility(View.VISIBLE);
            }


        });
    }

}
