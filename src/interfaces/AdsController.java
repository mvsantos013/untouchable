package com.kalgames.interfaces;

public interface AdsController {

    void showBannerAd();
    void hideBannerAd();
    void showInterstitialAd (Runnable then);
    int isWifiConnected();
}
