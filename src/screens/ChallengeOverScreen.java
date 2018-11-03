package com.kalgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kalgames.com.kalgames.assets.Storage;
import com.kalgames.untouchable.Untouchable;

public class ChallengeOverScreen implements Screen, InputProcessor{

    private final Untouchable screen;

    private Sprite backGround, rip, showScore, showScore2;

    private Image retryButton, ladderButton, rateButton, homeButton, crownAnimation;

    private boolean holdButtons;

    public ChallengeOverScreen(final Untouchable screen){
        this.screen = screen;

        this.backGround = new Sprite(Storage.backGroundTex);
        this.rip = new Sprite(Storage.ripTex);
        this.showScore = new Sprite(Storage.showScoreTex);
        this.showScore2 = new Sprite(Storage.showScoreTex);

        this.retryButton = new Image(Storage.retryButtonTex2);
        this.ladderButton = new Image(Storage.ladderTex);
        this.rateButton = new Image(Storage.rateTex);
        this.homeButton = new Image(Storage.homeTex);
        this.crownAnimation = new Image(Storage.crownTex);

        backGround.setPosition(-1,-1);
        rip.setPosition(screen.camera.viewportWidth - rip.getWidth() - 1, screen.camera.viewportHeight - rip.getHeight() - 1);
        showScore.setPosition(0.3f*screen.camera.viewportWidth - 0.5f*showScore.getWidth(), 0.53f*screen.camera.viewportHeight - 0.5f*showScore.getHeight());
        showScore2.setPosition(0.7f*screen.camera.viewportWidth - 0.5f*showScore2.getWidth(), 0.53f*screen.camera.viewportHeight - 0.5f*showScore2.getHeight());
        crownAnimation.setOrigin(0.5f*crownAnimation.getWidth(), 0.5f*crownAnimation.getHeight());
        crownAnimation.setPosition(0.865f*screen.camera.viewportWidth - 0.5f*crownAnimation.getWidth(), 0.63f*screen.camera.viewportHeight - 0.5f*crownAnimation.getHeight());

        retryButton.setOrigin(0.5f*retryButton.getWidth(), 0.5f*retryButton.getHeight());
        ladderButton.setOrigin(0.5f*ladderButton.getWidth(), 0.5f*ladderButton.getHeight());
        rateButton.setOrigin(0.5f*rateButton.getWidth(), 0.5f*rateButton.getHeight());
        homeButton.setOrigin(0.5f*homeButton.getWidth(), 0.5f*homeButton.getHeight());

        retryButton.setPosition(0.3f*screen.camera.viewportWidth - 0.5f*retryButton.getWidth(), 0.36f*screen.camera.viewportHeight - 0.5f*retryButton.getHeight());
        ladderButton.setPosition(0.7f*screen.camera.viewportWidth - 0.5f*ladderButton.getWidth(), 0.36f*screen.camera.viewportHeight - 0.5f*ladderButton.getHeight());
        rateButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*rateButton.getWidth(), 0.25f*screen.camera.viewportHeight - 0.5f*rateButton.getHeight());
        homeButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*homeButton.getWidth(), 0.14f*screen.camera.viewportHeight - 0.5f*homeButton.getHeight());
    }

    @Override
    public void show() {
        screen.stage.clear();

        screen.stage.addActor(retryButton);
        screen.stage.addActor(ladderButton);
        screen.stage.addActor(rateButton);
        screen.stage.addActor(homeButton);

        if(ChallengeScreen.newRecord){
            crownAnimation.clearActions();
            crownAnimation.setRotation(-400f);
            crownAnimation.setScale(1f,1f);
            crownAnimation.addAction(Actions.parallel(Actions.scaleTo(0.3f, 0.3f, 1f), Actions.rotateTo(-40, 1f)));
            screen.stage.addActor(crownAnimation);
            if(screen.musicOn)
                screen.recordSound.play(0.45f);
        }

        holdButtons = false;

        screen.stage.addActor(screen.transition);
        screen.transition.addAction(Actions.alpha(1f));
        screen.transition.addAction(Actions.sequence(Actions.fadeOut(0.4f), Actions.run(screen.runnables.setZIndexTransition())));

        screen.inputMultiplexer.clear();
        screen.inputMultiplexer.addProcessor(this);
        screen.inputMultiplexer.addProcessor(screen.stage);
        Gdx.input.setInputProcessor(screen.inputMultiplexer);

        if(screen.adsController.isWifiConnected() == 1 || screen.adsController.isWifiConnected() == -1)
            screen.adsController.showBannerAd();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        screen.camera.update();
        screen.batch.setProjectionMatrix(screen.camera.combined);

        screen.batch.begin();
        backGround.draw(screen.batch);
        rip.draw(screen.batch);
        showScore.draw(screen.batch);
        showScore2.draw(screen.batch);
        screen.glyphLayout.setText(screen.scoreFont2, "Score");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.3f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.587f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont2, String.valueOf(ChallengeScreen.score));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.3f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.5f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont2, "Best");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.7f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.587f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont2, String.valueOf(screen.settings.getInteger("high_score", 0)));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.7f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.5f*screen.camera.viewportHeight);
        screen.batch.end();

        screen.stage.act(delta);
        screen.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        screen.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screen.stageCoords.set((float) screenX, (float) screenY);
        screen.touchPos = screen.stage.screenToStageCoordinates(screen.stageCoords);
        screen.hitActor = screen.stage.hit(screen.touchPos.x, screen.touchPos.y, false);

        if(screen.hitActor != null){
            if(screen.hitActor == retryButton)
                retryButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == ladderButton)
                ladderButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == rateButton)
                rateButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == homeButton)
                homeButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!holdButtons && screen.hitActor != null) {
            if (screen.hitActor == retryButton) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.adsController.hideBannerAd();
                screen.transition.setZIndex(5);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToChallengeScreen())));
                holdButtons = true;
            }
            else if(screen.hitActor == ladderButton){
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.playServices.showScore();
            }
            else if(screen.hitActor == rateButton){
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.playServices.rateGame();
            }
            else if (screen.hitActor == homeButton){
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.adsController.hideBannerAd();
                screen.transition.setZIndex(5);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToMenu())));
                holdButtons = true;
            }

            retryButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            ladderButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            rateButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            homeButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));


        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
