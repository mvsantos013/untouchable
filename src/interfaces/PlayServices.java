package com.kalgames.interfaces;

public interface PlayServices {

    void signIn();
    void rateGame();
    void visitItgap();
    void submitScore(int highScore);
    void showScore();
    boolean isSignedIn();
}
