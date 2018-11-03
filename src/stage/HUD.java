package com.kalgames.stage;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kalgames.com.kalgames.assets.Storage;

public class HUD extends Group{
    private Image retryButton, nextLevelButton, levelsButton, homeButton;

    public HUD(){
        setBounds(0,0, Storage.retryButtonTex.getRegionWidth(), 6*Storage.retryButtonTex.getRegionHeight());

        retryButton = new Image(Storage.retryButtonTex);
        retryButton.setOrigin(0.5f*retryButton.getWidth(), 0.5f*retryButton.getHeight());

        nextLevelButton = new Image(Storage.nextLevelTex);
        nextLevelButton.setOrigin(0.5f*nextLevelButton.getWidth(), 0.5f*nextLevelButton.getHeight());

        levelsButton = new Image(Storage.levelsTex);
        levelsButton.setOrigin(0.5f*levelsButton.getWidth(), 0.5f*levelsButton.getHeight());

        homeButton = new Image(Storage.homeTex);
        homeButton.setOrigin(0.5f*homeButton.getWidth(), 0.5f*homeButton.getHeight());

        addActor(retryButton);
        addActor(nextLevelButton);
        addActor(levelsButton);
        addActor(homeButton);
    }

    public void setTheWinPosition(){
        retryButton.setPosition(0.5f*getWidth() - 0.5f*retryButton.getWidth(), 0.6f*getHeight() - 0.5f*retryButton.getHeight());
        nextLevelButton.setPosition(0.5f*getWidth() - 0.5f*nextLevelButton.getWidth(), 0.8f*getHeight() - 0.5f*nextLevelButton.getHeight());
        levelsButton.setPosition(0.5f*getWidth() - 0.5f*levelsButton.getWidth(), 0.4f*getHeight() - 0.5f*levelsButton.getHeight());
        homeButton.setPosition(0.5f*getWidth() - 0.5f*homeButton.getWidth(), 0.2f*getHeight() - 0.5f*homeButton.getHeight());
    }

    public void setTheLosePosition(){
        retryButton.setPosition(0.5f*getWidth() - 0.5f*retryButton.getWidth(), 0.8f*getHeight() - 0.5f*retryButton.getHeight());
        nextLevelButton.setPosition(0.5f*getWidth() - 0.5f*nextLevelButton.getWidth(), 0.6f*getHeight() - 0.5f*nextLevelButton.getHeight());
        levelsButton.setPosition(0.5f*getWidth() - 0.5f*levelsButton.getWidth(), 0.4f*getHeight() - 0.5f*levelsButton.getHeight());
        homeButton.setPosition(0.5f*getWidth() - 0.5f*homeButton.getWidth(), 0.2f*getHeight() - 0.5f*homeButton.getHeight());
    }

    public void setTheAlpha(){
        retryButton.addAction(Actions.alpha(0f));
        nextLevelButton.addAction(Actions.alpha(0f));
        levelsButton.addAction(Actions.alpha(0f));
        homeButton.addAction(Actions.alpha(0f));
    }

    public void setTheWinAnimation(){
        nextLevelButton.addAction(Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5)));
        retryButton.addAction(Actions.delay(0.2f,Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
        levelsButton.addAction(Actions.delay(0.4f, Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
        homeButton.addAction(Actions.delay(0.6f, Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
    }

    public void setTheLoseAnimation(){
        retryButton.addAction(Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5)));
        nextLevelButton.addAction(Actions.delay(0.2f, Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
        levelsButton.addAction(Actions.delay(0.4f, Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
        homeButton.addAction(Actions.delay(0.6f, Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
    }

    public void setTheLoseAnimation2(){
        retryButton.addAction(Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5)));
        nextLevelButton.addAction(Actions.delay(0.2f, Actions.parallel(Actions.alpha(0.4f, 0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
        levelsButton.addAction(Actions.delay(0.4f, Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
        homeButton.addAction(Actions.delay(0.6f, Actions.parallel(Actions.fadeIn(0.2f), Actions.moveBy(0, 100, 0.2f, Interpolation.pow5))));
    }

    public void clearTheActions(){
        retryButton.clearActions();
        nextLevelButton.clearActions();
        levelsButton.clearActions();
        homeButton.clearActions();
    }
}
