package com.kalgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kalgames.com.kalgames.assets.Storage;
import com.kalgames.untouchable.Untouchable;

public class SplashScreen implements Screen{

    private final Untouchable screen;

    private Image logoImage;
    private Sprite backGround;

    public SplashScreen(final Untouchable screen){
        this.screen = screen;

        logoImage = new Image(Storage.kmiLogoTex);
        logoImage.setOrigin(0.5f*logoImage.getWidth(), 0.5f*logoImage.getHeight());
        logoImage.scaleBy(0.1f);
        logoImage.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*logoImage.getWidth(), 0.5f*screen.camera.viewportHeight - 0.5f*logoImage.getHeight());
        backGround = new Sprite(Storage.backGroundTex);
        backGround.setPosition(-1,-1);
    }

    @Override
    public void show() {
        screen.transition.addAction(Actions.alpha(0f));
        screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToMenu())));
        logoImage.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(2f), Actions.delay(2f), Actions.fadeOut(1f), Actions.run(screen.runnables.addTransition())));
        screen.stage.addActor(logoImage);
        screen.logoSound.play(0.5f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screen.camera.update();
        screen.batch.setProjectionMatrix(screen.camera.combined);

        screen.batch.begin();
        backGround.draw(screen.batch);
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
}
