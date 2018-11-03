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

public class Levels implements Screen, InputProcessor{

    private final Untouchable screen;

    private Sprite menuBackGround, levelsTitle, blankSpace;

    private Image level_1, level_2, level_3, level_4, level_5, level_6, level_7, level_8, level_9, level_10,
    cMButton, homeButton;

    private boolean holdButtons;

    public Levels(final Untouchable screen){
        this.screen = screen;

        this.menuBackGround = new Sprite(Storage.backGroundTex);
        this.levelsTitle = new Sprite(Storage.levelsTitleTex);
        blankSpace = new Sprite(Storage.blankSpaceTex);
        blankSpace.setScale(0.69f, 0.69f);

        this.level_1 = new Image(Storage.level_1_ButtonTex);
        this.level_2 = new Image(Storage.level_2_ButtonTex);
        this.level_3 = new Image(Storage.level_3_ButtonTex);
        this.level_4 = new Image(Storage.level_4_ButtonTex);
        this.level_5 = new Image(Storage.level_5_ButtonTex);
        this.level_6 = new Image(Storage.level_6_ButtonTex);
        this.level_7 = new Image(Storage.level_7_ButtonTex);
        this.level_8 = new Image(Storage.level_8_ButtonTex);
        this.level_9 = new Image(Storage.level_9_ButtonTex);
        this.level_10 = new Image(Storage.level_10_ButtonTex);
        this.cMButton = new Image(Storage.cMTex);
        this.homeButton = new Image(Storage.homeTex);

        level_1.setScale(0.85f, 0.85f);
        level_2.setScale(0.85f, 0.85f);
        level_3.setScale(0.85f, 0.85f);
        level_4.setScale(0.85f, 0.85f);
        level_5.setScale(0.85f, 0.85f);
        level_6.setScale(0.85f, 0.85f);
        level_7.setScale(0.85f, 0.85f);
        level_8.setScale(0.85f, 0.85f);
        level_9.setScale(0.85f, 0.85f);
        level_10.setScale(0.85f, 0.85f);
        level_1.setOrigin(0.5f*level_1.getWidth(), 0.5f*level_1.getHeight());
        level_2.setOrigin(0.5f*level_2.getWidth(), 0.5f*level_2.getHeight());
        level_3.setOrigin(0.5f*level_3.getWidth(), 0.5f*level_3.getHeight());
        level_4.setOrigin(0.5f*level_4.getWidth(), 0.5f*level_4.getHeight());
        level_5.setOrigin(0.5f*level_5.getWidth(), 0.5f*level_5.getHeight());
        level_6.setOrigin(0.5f*level_6.getWidth(), 0.5f*level_6.getHeight());
        level_7.setOrigin(0.5f*level_7.getWidth(), 0.5f*level_7.getHeight());
        level_8.setOrigin(0.5f*level_8.getWidth(), 0.5f*level_8.getHeight());
        level_9.setOrigin(0.5f*level_9.getWidth(), 0.5f*level_9.getHeight());
        level_10.setOrigin(0.5f*level_10.getWidth(), 0.5f*level_10.getHeight());
        level_1.setPosition(0.23f*screen.camera.viewportWidth - 0.5f*level_2.getWidth(), 0.79f*screen.camera.viewportHeight - 0.5f*level_2.getHeight());
        level_2.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_2.getWidth(), 0.79f*screen.camera.viewportHeight - 0.5f*level_2.getHeight());
        level_3.setPosition(0.77f*screen.camera.viewportWidth - 0.5f*level_3.getWidth(), 0.79f*screen.camera.viewportHeight - 0.5f*level_3.getHeight());
        level_4.setPosition(0.23f*screen.camera.viewportWidth - 0.5f*level_4.getWidth(), 0.64f*screen.camera.viewportHeight - 0.5f*level_4.getHeight());
        level_5.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_5.getWidth(), 0.64f*screen.camera.viewportHeight - 0.5f*level_5.getHeight());
        level_6.setPosition(0.77f*screen.camera.viewportWidth - 0.5f*level_6.getWidth(), 0.64f*screen.camera.viewportHeight - 0.5f*level_6.getHeight());
        level_7.setPosition(0.23f*screen.camera.viewportWidth - 0.5f*level_7.getWidth(), 0.49f*screen.camera.viewportHeight - 0.5f*level_7.getHeight());
        level_8.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_8.getWidth(), 0.49f*screen.camera.viewportHeight - 0.5f*level_8.getHeight());
        level_9.setPosition(0.77f*screen.camera.viewportWidth - 0.5f*level_9.getWidth(), 0.49f*screen.camera.viewportHeight - 0.5f*level_9.getHeight());
        level_10.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_10.getWidth(), 0.34f*screen.camera.viewportHeight - 0.5f*level_10.getHeight());

        cMButton.setOrigin(0.5f*cMButton.getWidth(), 0.5f*cMButton.getHeight());
        homeButton.setOrigin(0.5f*homeButton.getWidth(), 0.5f*homeButton.getHeight());
        cMButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*cMButton.getWidth(), 0.2f*screen.camera.viewportHeight - 0.5f*cMButton.getHeight());
        homeButton.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*homeButton.getWidth(), 0.08f*screen.camera.viewportHeight - 0.5f*homeButton.getHeight());
        menuBackGround.setPosition(-1,-1);
        levelsTitle.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*levelsTitle.getWidth() + 1, 0.925f*screen.camera.viewportHeight - 0.5f*levelsTitle.getHeight());
    }

    @Override
    public void show() {
        screen.stage.clear();

        screen.stage.addActor(level_1);
        if(screen.settings.getBoolean("level_1_Unlocked", false))
            screen.stage.addActor(level_2);
        if(screen.settings.getBoolean("level_2_Unlocked", false))
            screen.stage.addActor(level_3);
        if(screen.settings.getBoolean("level_3_Unlocked", false))
            screen.stage.addActor(level_4);
        if(screen.settings.getBoolean("level_4_Unlocked", false))
            screen.stage.addActor(level_5);
        if(screen.settings.getBoolean("level_5_Unlocked", false))
            screen.stage.addActor(level_6);
        if(screen.settings.getBoolean("level_6_Unlocked", false))
            screen.stage.addActor(level_7);
        if(screen.settings.getBoolean("level_7_Unlocked", false))
            screen.stage.addActor(level_8);
        if(screen.settings.getBoolean("level_8_Unlocked", false))
            screen.stage.addActor(level_9);
        if(screen.settings.getBoolean("level_9_Unlocked", false))
            screen.stage.addActor(level_10);


        screen.stage.addActor(cMButton);
        screen.stage.addActor(homeButton);

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
        menuBackGround.draw(screen.batch);
        blankSpace.setPosition(0.23f*screen.camera.viewportWidth - 0.5f*level_2.getWidth(), 0.79f*screen.camera.viewportHeight - 0.5f*level_2.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_2.getWidth(), 0.79f*screen.camera.viewportHeight - 0.5f*level_2.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.77f*screen.camera.viewportWidth - 0.5f*level_3.getWidth(), 0.79f*screen.camera.viewportHeight - 0.5f*level_3.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.23f*screen.camera.viewportWidth - 0.5f*level_4.getWidth(), 0.64f*screen.camera.viewportHeight - 0.5f*level_4.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_5.getWidth(), 0.64f*screen.camera.viewportHeight - 0.5f*level_5.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.77f*screen.camera.viewportWidth - 0.5f*level_6.getWidth(), 0.64f*screen.camera.viewportHeight - 0.5f*level_6.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.23f*screen.camera.viewportWidth - 0.5f*level_7.getWidth(), 0.49f*screen.camera.viewportHeight - 0.5f*level_7.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_8.getWidth(), 0.49f*screen.camera.viewportHeight - 0.5f*level_8.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.77f*screen.camera.viewportWidth - 0.5f*level_9.getWidth(), 0.49f*screen.camera.viewportHeight - 0.5f*level_9.getHeight());
        blankSpace.draw(screen.batch);
        blankSpace.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*level_10.getWidth(), 0.34f*screen.camera.viewportHeight - 0.5f*level_10.getHeight());
        blankSpace.draw(screen.batch);
        levelsTitle.draw(screen.batch);
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
            if(screen.hitActor == level_1)
                level_1.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_2)
                level_2.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_3)
                level_3.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_4)
                level_4.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_5)
                level_5.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_6)
                level_6.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_7)
                level_7.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_8)
                level_8.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_9)
                level_9.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == level_10)
                level_10.addAction(Actions.scaleTo(0.70f, 0.70f, 0.15f));
            if(screen.hitActor == cMButton)
                cMButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            if(screen.hitActor == homeButton)
                homeButton.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!holdButtons && screen.hitActor != null) {
            if (screen.hitActor == level_1) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_1())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_2) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_2())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_3) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_3())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_4) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_4())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_5) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_5())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_6) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_6())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_7) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_7())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_8) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_8())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_9) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_9())));
                holdButtons = true;
            }
            else if (screen.hitActor == level_10) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.menuMusic.isPlaying())
                    screen.menuMusic.stop();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_10())));
                holdButtons = true;
            }
            else if (screen.hitActor == cMButton) {
                if(screen.settings.getBoolean("level_5_Unlocked", false)) {
                    if(screen.musicOn)
                        screen.buttonClick.play();
                    if(screen.menuMusic.isPlaying())
                        screen.menuMusic.stop();
                    screen.transition.setZIndex(12);
                    screen.transition.addAction(Actions.alpha(0f));
                    screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToChallengeScreen())));
                    holdButtons = true;
                }
                else screen.toastService.makeToast("Level 5 is required to unlock this mode!");
            }
            else if (screen.hitActor == homeButton) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.transition.setZIndex(12);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToMenu())));
                holdButtons = true;
            }

            level_1.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_2.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_3.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_4.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_5.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_6.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_7.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_8.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_9.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            level_10.addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
            cMButton.addAction(Actions.scaleTo(1f, 1f, 0.15f));
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
