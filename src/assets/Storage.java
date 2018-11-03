package com.kalgames.com.kalgames.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Storage {

    public static TextureAtlas atlas, deathAtlas, winAtlas;
    public static TextureRegion kmiLogoTex, playerBallTex, blackBallTex, staticBallTex, arenaTex, hudTex, hud2Tex,
    backGroundTex, levelsTitleTex, ripTex, showScoreTex, crownTex, leftTex, rightTex, retryButtonTex,
    retryButtonTex2, nextLevelTex, levelsTex, homeTex, cMTex, rateTex, aboutTex, visitTex, soundOnTex, soundOffTex,
    ladderTex, transitionTex, menuBackGroundTex, level_1_ButtonTex, level_2_ButtonTex, level_3_ButtonTex,
    level_4_ButtonTex, level_5_ButtonTex, level_6_ButtonTex, level_7_ButtonTex, level_8_ButtonTex, level_9_ButtonTex,
    level_10_ButtonTex, blankSpaceTex, testeToFilterTex, testeToFilterTex2, itgapIcon, itgapTitle;

    public static void loadStorage(){
        atlas = new TextureAtlas("Texture.atlas");
        deathAtlas = new TextureAtlas("Death.atlas");
        winAtlas = new TextureAtlas("Win.atlas");

        kmiLogoTex = atlas.findRegion("logoKmiGames");
        playerBallTex = atlas.findRegion("playerBall");
        blackBallTex = atlas.findRegion("blackBall");
        staticBallTex = atlas.findRegion("staticBall");
        arenaTex = atlas.findRegion("arena");
        hudTex = atlas.findRegion("hud");
        hud2Tex = atlas.findRegion("hud2");
        backGroundTex = atlas.findRegion("backGround");
        levelsTitleTex = atlas.findRegion("Levels");
        ripTex = atlas.findRegion("rip");
        showScoreTex = atlas.findRegion("showScore");
        crownTex = atlas.findRegion("crown");
        leftTex = atlas.findRegion("left");
        rightTex = atlas.findRegion("right");

        retryButtonTex = atlas.findRegion("retryButton");
        retryButtonTex2 = atlas.findRegion("retryButton2");
        nextLevelTex = atlas.findRegion("nextLevelButton");
        levelsTex = atlas.findRegion("levelsButton");
        homeTex = atlas.findRegion("homeButton");
        cMTex = atlas.findRegion("cMButton");
        rateTex = atlas.findRegion("rateButton");
        aboutTex = atlas.findRegion("aboutButton");
        visitTex = atlas.findRegion("visitButton");
        soundOnTex = atlas.findRegion("SoundOnButton");
        soundOffTex = atlas.findRegion("SoundOffButton");
        ladderTex = atlas.findRegion("LeaderboardButton");
        transitionTex = atlas.findRegion("Transition");
        menuBackGroundTex = atlas.findRegion("menuBackGround");
        itgapIcon = atlas.findRegion("itgap");
        itgapTitle = atlas.findRegion("itgapTitle");

        level_1_ButtonTex = atlas.findRegion("level_1_Button");
        level_2_ButtonTex = atlas.findRegion("level_2_Button");
        level_3_ButtonTex = atlas.findRegion("level_3_Button");
        level_4_ButtonTex = atlas.findRegion("level_4_Button");
        level_5_ButtonTex = atlas.findRegion("level_5_Button");
        level_6_ButtonTex = atlas.findRegion("level_6_Button");
        level_7_ButtonTex = atlas.findRegion("level_7_Button");
        level_8_ButtonTex = atlas.findRegion("level_8_Button");
        level_9_ButtonTex = atlas.findRegion("level_9_Button");
        level_10_ButtonTex = atlas.findRegion("level_10_Button");
        blankSpaceTex = atlas.findRegion("blankSpace");

        playerBallTex.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        testeToFilterTex = deathAtlas.findRegion("2");
        testeToFilterTex.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        testeToFilterTex2 = winAtlas.findRegion("15");
        testeToFilterTex2.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public static void disposeStorage(){
        atlas.dispose();
    }
}
