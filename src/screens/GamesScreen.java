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

public class GamesScreen implements Screen, InputProcessor{

    private final Untouchable screen;

    private Sprite backGround, itgap, itgapTitle;
    private Image homeButton, visitItgapButton;

    private boolean holdButtons;

    public GamesScreen(final Untouchable screen){
        this.screen = screen;

        this.backGround = new Sprite(Storage.backGroundTex);
        this.itgap = new Sprite(Storage.itgapIcon);
        this.itgapTitle = new Sprite(Storage.itgapTitle);
        backGround.setPosition(-1,-1);
        itgap.setPosition(0.25f*screen.camera.viewportWidth - 0.5f*itgap.getWidth(), 0.75f*screen.camera.viewportHeight - 0.5f*itgap.getHeight());
        itgapTitle.setPosition(0.25f*screen.camera.viewportWidth - 0.5f*itgapTitle.getWidth(), 0.91f*screen.camera.viewportHeight - 0.5f*itgapTitle.getHeight());

        this.homeButton = new Image(Storage.homeTex);
        this.visitItgapButton = new Image(Storage.visitTex);

        homeButton.setOrigin(0.5f*homeButton.getWidth(), 0.5f*homeButton.getHeight());
        homeButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*homeButton.getWidth(), 0.08f*screen.camera.viewportHeight - 0.5f*homeButton.getHeight());

        visitItgapButton.setOrigin(0.5f*visitItgapButton.getWidth(), 0.5f*visitItgapButton.getHeight());
        visitItgapButton.setPosition(0.25f*screen.camera.viewportWidth - 0.5f*visitItgapButton.getWidth(), 0.58f*screen.camera.viewportHeight - 0.5f*visitItgapButton.getHeight());
    }

    @Override
    public void show() {
        screen.stage.clear();

        screen.stage.addActor(homeButton);
        screen.stage.addActor(visitItgapButton);

        if(screen.musicOn) {
            if(!screen.menuMusic.isPlaying()) {
                screen.menuMusic.play();
            }
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
        backGround.draw(screen.batch);
        itgap.draw(screen.batch);
        itgapTitle.draw(screen.batch);
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
            if(screen.hitActor == homeButton)
                homeButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == visitItgapButton)
                visitItgapButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!holdButtons && screen.hitActor != null) {
            if (screen.hitActor == homeButton) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.transition.setZIndex(3);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToMenu())));
                holdButtons = true;
            }
            else if (screen.hitActor == visitItgapButton) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.playServices.visitItgap();
            }

            homeButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
            visitItgapButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
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
