package com.kalgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.kalgames.com.kalgames.assets.Storage;
import com.kalgames.untouchable.Untouchable;

public class MenuScreen implements Screen, InputProcessor{

    private final Untouchable screen;

    private Image levelsButton, cMButton, rateButton, aboutButton, soundButton;
    private Sprite menuBackGround, soundOnSprite, soundOffSprite;
    private SpriteDrawable spriteDrawableOn, spriteDrawableOff;

    private boolean holdButtons;

    public MenuScreen(final Untouchable screen){
        this.screen = screen;

        this.levelsButton = new Image(Storage.levelsTex);
        this.cMButton = new Image(Storage.cMTex);
        this.rateButton = new Image(Storage.rateTex);
        this.aboutButton = new Image(Storage.aboutTex);
        this.soundButton = new Image(Storage.soundOnTex);

        this.menuBackGround = new Sprite(Storage.menuBackGroundTex);
        this.soundOnSprite = new Sprite(Storage.soundOnTex);
        this.soundOffSprite = new Sprite(Storage.soundOffTex);
        this.spriteDrawableOn = new SpriteDrawable(soundOnSprite);
        this.spriteDrawableOff = new SpriteDrawable(soundOffSprite);

        levelsButton.setOrigin(0.5f*levelsButton.getWidth(), 0.5f*levelsButton.getHeight());
        cMButton.setOrigin(0.5f*cMButton.getWidth(), 0.5f*cMButton.getHeight());
        rateButton.setOrigin(0.5f*rateButton.getWidth(), 0.5f*rateButton.getHeight());
        aboutButton.setOrigin(0.5f*aboutButton.getWidth(), 0.5f*aboutButton.getHeight());
        soundButton.setOrigin(0.5f*soundButton.getWidth(), 0.5f*soundButton.getHeight());
        levelsButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*levelsButton.getWidth(), 0.44f*screen.camera.viewportHeight - 0.5f*levelsButton.getHeight());
        cMButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*cMButton.getWidth(), 0.32f*screen.camera.viewportHeight - 0.5f*cMButton.getHeight());
        rateButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*rateButton.getWidth(), 0.20f*screen.camera.viewportHeight - 0.5f*rateButton.getHeight());
        aboutButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*aboutButton.getWidth(), 0.08f*screen.camera.viewportHeight - 0.5f*aboutButton.getHeight());
        soundButton.setPosition(0.9f*screen.camera.viewportWidth - 0.5f*soundButton.getWidth(), 0.06f*screen.camera.viewportHeight - 0.5f*soundButton.getHeight());

        menuBackGround.setPosition(-1,-1);
    }

    @Override
    public void show() {
        screen.stage.clear();

        screen.stage.addActor(levelsButton);
        screen.stage.addActor(cMButton);
        screen.stage.addActor(rateButton);
        screen.stage.addActor(aboutButton);
        screen.stage.addActor(soundButton);

        if(screen.musicOn) {
            if(!screen.menuMusic.isPlaying())
                screen.menuMusic.play();
        }

        holdButtons = false;

        screen.stage.addActor(screen.transition);
        screen.transition.addAction(Actions.alpha(1f));
        screen.transition.addAction(Actions.sequence(Actions.fadeOut(0.4f), Actions.run(screen.runnables.setZIndexTransition())));

        screen.inputMultiplexer.clear();
        screen.inputMultiplexer.addProcessor(this);
        screen.inputMultiplexer.addProcessor(screen.stage);
        Gdx.input.setInputProcessor(screen.inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        screen.camera.update();
        screen.batch.setProjectionMatrix(screen.camera.combined);

        screen.batch.begin();
        menuBackGround.draw(screen.batch);
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
            if(screen.hitActor == levelsButton)
                levelsButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == cMButton)
                cMButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == rateButton)
                rateButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == aboutButton)
                aboutButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == soundButton)
                soundButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!holdButtons && screen.hitActor != null) {
            if (screen.hitActor == levelsButton) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.transition.setZIndex(5);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevels())));
                holdButtons = true;
            }
            else if(screen.hitActor == cMButton){
                if(screen.settings.getBoolean("level_5_Unlocked", false)){
                    if(screen.musicOn) {
                        if(screen.menuMusic.isPlaying())
                            screen.menuMusic.stop();
                        screen.buttonClick.play();
                    }
                    screen.transition.setZIndex(5);
                    screen.transition.addAction(Actions.alpha(0f));
                    screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToChallengeScreen())));
                    holdButtons = true;
                }
                else screen.toastService.makeToast("Level 5 is required to unlock this mode!");
            }
            else if(screen.hitActor == rateButton){
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.playServices.rateGame();
            }
            else if(screen.hitActor == aboutButton){
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.transition.setZIndex(5);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToGamesScreen())));
                holdButtons = true;
            }
            else if (screen.hitActor == soundButton){
                if(screen.musicOn)
                    soundButton.setDrawable(spriteDrawableOff);
                if(!screen.musicOn)
                    soundButton.setDrawable(spriteDrawableOn);

                screen.musicOn = !screen.musicOn;

                if(screen.musicOn) {
                    screen.buttonClick.play();
                    if(!screen.menuMusic.isPlaying())
                        screen.menuMusic.play();

                }
                if(!screen.musicOn){
                    if(screen.menuMusic.isPlaying())
                        screen.menuMusic.stop();
                }
            }

            levelsButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            cMButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            rateButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            aboutButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            soundButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
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
