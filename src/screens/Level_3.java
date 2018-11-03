package com.kalgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.kalgames.com.kalgames.assets.Storage;
import com.kalgames.objects.Ball;
import com.kalgames.untouchable.Untouchable;

public class Level_3 implements Screen, InputProcessor{

    private final Untouchable screen;

    private Vector2 posPlayer, velocityPlayer, vectorDirection;
    private final float playerRadius, arenaRadius;
    private float theta;
    private boolean thetaLeft, thetaRight;
    private int pointerLeft, pointerRight;

    private float timedState;
    private int timeLeft;

    private boolean gameStarted, gameFinished, gameWon;
    private boolean setStageContent;
    private boolean holdButtons;

    private boolean backCalled;

    private Array<Ball> balls;
    private Sprite backGroundSprite, hudSprite, playerSprite, arenaSprite, leftSprite, rightSprite;

    private float timeForWinAnimation;
    private float timeForDeathAnimation;

    private boolean doAfterDeath;

    public Level_3(final Untouchable screen){
        this.screen = screen;

        this.posPlayer = new Vector2();
        this.velocityPlayer = new Vector2();
        this.vectorDirection = new Vector2();

        this.playerRadius = 0.02f*screen.camera.viewportWidth;
        this.arenaRadius = 0.9f*screen.camera.viewportWidth/2;

        this.balls = new Array<Ball>();

        this.backGroundSprite = new Sprite(Storage.backGroundTex);
        this.hudSprite = new Sprite(Storage.hudTex);
        this.playerSprite = new Sprite(Storage.playerBallTex);
        this.arenaSprite = new Sprite(Storage.arenaTex);
        this.leftSprite = new Sprite(Storage.leftTex);
        this.rightSprite = new Sprite(Storage.rightTex);
        leftSprite.setAlpha(0.55f);
        rightSprite.setAlpha(0.55f);
    }

    @Override
    public void show() {
        theta = 0;
        posPlayer.set(0.5f*screen.camera.viewportWidth, 0.62f*screen.camera.viewportHeight);
        playerSprite.setPosition(posPlayer.x - 0.5f*playerSprite.getWidth(), posPlayer.y - 0.5f*playerSprite.getHeight());

        backGroundSprite.setPosition(-1,-1);
        hudSprite.setPosition(-1,-1);
        arenaSprite.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*arenaSprite.getWidth(), 0.62f*screen.camera.viewportHeight - 0.5f*arenaSprite.getHeight());
        leftSprite.setPosition(0.2f*screen.camera.viewportWidth - 0.5f*leftSprite.getWidth(), 0.15f*screen.camera.viewportHeight - 0.5f*leftSprite.getHeight());
        rightSprite.setPosition(0.8f*screen.camera.viewportWidth - 0.5f*rightSprite.getWidth(), 0.15f*screen.camera.viewportHeight - 0.5f*rightSprite.getHeight());

        timeLeft = 200;
        timedState = 0;

        thetaLeft = false;
        thetaRight = false;

        gameStarted = false;
        gameFinished = false;
        gameWon = false;

        backCalled = false;

        timeForWinAnimation = 0;
        timeForDeathAnimation = 0;

        doAfterDeath = true;

        if(screen.musicOn){
            if(!screen.levelsMusic.isPlaying()){
                screen.levelsMusic.play();
            }
        }

        balls.clear();

        screen.stage.clear();
        screen.hud.clearTheActions();
        screen.hud.setPosition(0.5f*screen.camera.viewportWidth - 0.5f*screen.hud.getWidth(), 0.23f*screen.camera.viewportHeight);
        screen.hud.setTheAlpha();
        setStageContent = false;
        holdButtons = true;

        screen.stage.addActor(screen.transition);
        screen.transition.addAction(Actions.alpha(1f));
        screen.transition.addAction(Actions.fadeOut(0.4f));

        screen.inputMultiplexer.clear();
        screen.inputMultiplexer.addProcessor(this);
        screen.inputMultiplexer.addProcessor(screen.stage);
        Gdx.input.setInputProcessor(screen.inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        timedState += delta;

        screen.camera.update();
        screen.batch.setProjectionMatrix(screen.camera.combined);

        if(!gameFinished)
            if(!gameStarted)
                gameTipLoop();
            else
                gameLoop(delta);
        else{
            if(gameWon)
                gameWonLoop(delta);
            else
                gameLoseLoop(delta);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            if(!backCalled) {
                if(screen.levelsMusic.isPlaying())
                    screen.levelsMusic.stop();
                Timer.instance().clear();
                screen.transition.setZIndex(1);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevels())));
                backCalled = true;
            }
        }

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

    public void handlingInput(float delta){
        if(thetaLeft)
            theta += 3.5f * delta;
        if(thetaRight)
            theta -= 3.5f * delta;
        vectorDirection.set((float) Math.cos(theta), (float) Math.sin(theta));
        velocityPlayer.set(150*vectorDirection.x, 150*vectorDirection.y);
    }

    public void positionUpdate(float delta){
        posPlayer.add(velocityPlayer.x * delta, velocityPlayer.y * delta);
        playerSprite.setPosition(posPlayer.x - 0.5f*playerSprite.getWidth(), posPlayer.y - 0.5f*playerSprite.getHeight());

        for (Ball ball : balls) {
            ball.getPosBall().add(ball.getVelocityBall().x * delta, ball.getVelocityBall().y * delta);
            ball.getBallSprite().setPosition(ball.getPosBall().x - 0.5f*ball.getBallSprite().getWidth(), ball.getPosBall().y - 0.5f*ball.getBallSprite().getHeight());
        }
    }

    public void updateAfterDeath(float delta){
        velocityPlayer.set(50*vectorDirection.x, 50*vectorDirection.y);
        posPlayer.add(velocityPlayer.x * delta, velocityPlayer.y * delta);
        playerSprite.setPosition(posPlayer.x - 0.5f*playerSprite.getWidth(), posPlayer.y - 0.5f*playerSprite.getHeight());
    }

    public boolean isPlayerOutArena(){
        return((Math.pow(0.5f*screen.camera.viewportWidth - posPlayer.x, 2) + Math.pow(0.62f*screen.camera.viewportHeight - posPlayer.y, 2))>= Math.pow((playerRadius + arenaRadius), 2));
    }

    public void spawnBallOnScore(){
        switch(timeLeft){
            case(185):
                balls.add(new Ball());
                balls.peek().setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
            case(165):
                balls.add(new Ball());
                balls.peek().setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
            case(145):
                balls.add(new Ball());
                balls.peek().setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
            case(125):
                balls.add(new Ball());
                balls.peek().setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                balls.add(new Ball());
                balls.peek().setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
            case(105):
                balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                balls.get(1).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
            case(95):
                balls.get(2).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
            case(70):
                balls.get(3).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;

            case(45):
                balls.get(4).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                balls.get(1).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
            case(25):
                balls.get(2).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                break;
        }
    }

    public void gameLoop(float delta){
        if(timedState >= 0.1f) {
            timedState = 0;
            timeLeft -= 1;
            spawnBallOnScore();
        }

        handlingInput(delta);
        positionUpdate(delta);

        screen.batch.begin();

        backGroundSprite.draw(screen.batch);
        arenaSprite.draw(screen.batch);
        for (Ball ball : balls) {
            ball.getBallSprite().setSize(2*ball.getRadius(), 2*ball.getRadius());
            ball.getBallSprite().draw(screen.batch);
        }
        hudSprite.draw(screen.batch);
        leftSprite.draw(screen.batch);
        rightSprite.draw(screen.batch);
        playerSprite.draw(screen.batch);
        screen.glyphLayout.setText(screen.scoreFont, "Time: " + String.valueOf(timeLeft));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.085f*screen.camera.viewportWidth, 0.984f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont, "Level: 3");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.75f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.984f*screen.camera.viewportHeight);

        screen.batch.end();


        for (Ball ball : balls) {
            if(ball.isBallCollided(posPlayer, playerRadius))
                gameFinished = true;

        }

        if(isPlayerOutArena())
            gameFinished = true;

        if(timeLeft == 0) {
            gameFinished = true;
            gameWon = true;
            screen.settings.putBoolean("level_3_Unlocked", true);
            screen.settings.flush();
        }
    }

    public void gameTipLoop(){

        screen.batch.begin();

        backGroundSprite.draw(screen.batch);
        arenaSprite.draw(screen.batch);
        for (Ball ball : balls) {
            ball.getBallSprite().setSize(2*ball.getRadius(), 2*ball.getRadius());
            ball.getBallSprite().draw(screen.batch);
        }
        hudSprite.draw(screen.batch);
        leftSprite.draw(screen.batch);
        rightSprite.draw(screen.batch);
        playerSprite.draw(screen.batch);
        screen.glyphLayout.setText(screen.scoreFont, "Time: " + String.valueOf(timeLeft));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.085f*screen.camera.viewportWidth, 0.984f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont, "Level: 3");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.75f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.984f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont, "Tap to start!");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.5f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.75f*screen.camera.viewportHeight);

        screen.batch.end();

        if(Gdx.input.justTouched())
            gameStarted = true;
    }

    public void gameWonLoop(float delta){
        screen.batch.begin();

        backGroundSprite.draw(screen.batch);
        arenaSprite.draw(screen.batch);
        for (Ball ball : balls) {
            ball.getBallSprite().setSize(2*ball.getRadius(), 2*ball.getRadius());
            ball.getBallSprite().draw(screen.batch);
        }
        hudSprite.draw(screen.batch);
        leftSprite.draw(screen.batch);
        rightSprite.draw(screen.batch);
        screen.batch.draw(screen.winAnimation.getKeyFrame(timeForWinAnimation, true), posPlayer.x - 18.5f, posPlayer.y - 18.5f);
        screen.glyphLayout.setText(screen.scoreFont, "Time: " + String.valueOf(timeLeft));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.085f*screen.camera.viewportWidth, 0.984f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont, "Level: 3");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.75f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.984f*screen.camera.viewportHeight);

        screen.batch.end();

        if(!setStageContent){
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    screen.hud.setTheWinPosition();
                    screen.hud.setTheWinAnimation();
                    screen.stage.addActor(screen.hud);
                    holdButtons = false;
                }
            }, 1.4f);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(screen.musicOn)
                        screen.showButtonSound.play(0.05f);
                }
            }, 1.4f, 0.2f, 3);
            setStageContent = true;
        }

        timeForWinAnimation += delta;
    }

    public void gameLoseLoop(float delta){
        if(doAfterDeath){
            if(screen.musicOn)
                screen.deathSound.play(0.3f);
            doAfterDeath = false;

            screen.deathCount += 1;
            if(screen.deathCount == 8){
                if(screen.adsController.isWifiConnected() == 1) {
                    Timer .schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            screen.adsController.showInterstitialAd(screen.runnables.changeToLevel_3());
                            Timer.instance().clear();
                        }
                    }, 1f);
                }
                screen.deathCount = 0;
            }
        }

        updateAfterDeath(delta);
        screen.batch.begin();

        backGroundSprite.draw(screen.batch);
        arenaSprite.draw(screen.batch);
        for (Ball ball : balls) {
            ball.getBallSprite().setSize(2*ball.getRadius(), 2*ball.getRadius());
            ball.getBallSprite().draw(screen.batch);
        }
        hudSprite.draw(screen.batch);
        leftSprite.draw(screen.batch);
        rightSprite.draw(screen.batch);
        screen.batch.draw(screen.deathAnimation.getKeyFrame(timeForDeathAnimation, false), posPlayer.x - 18.5f, posPlayer.y - 18.5f);
        screen.glyphLayout.setText(screen.scoreFont, "Time: " + String.valueOf(timeLeft));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.085f*screen.camera.viewportWidth, 0.984f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont, "Level: 3");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.75f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.984f*screen.camera.viewportHeight);

        screen.batch.end();

        if(!setStageContent){
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    screen.hud.setTheLosePosition();

                    if(screen.settings.getBoolean("level_3_Unlocked", false))
                        screen.hud.setTheLoseAnimation();
                    else
                        screen.hud.setTheLoseAnimation2();

                    screen.stage.addActor(screen.hud);
                    holdButtons = false;
                }
            }, 1.4f);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(screen.musicOn)
                        screen.showButtonSound.play(0.05f);
                }
            }, 1.4f, 0.2f, 3);
            setStageContent = true;
        }

        timeForDeathAnimation += delta;
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
        screen.touchPos.set(screenX, screenY);
        screen.viewport.unproject(screen.touchPos);

        if((screen.touchPos.x < 0.5f*screen.camera.viewportWidth) && screen.touchPos.y < 0.3f*screen.camera.viewportHeight){
            thetaLeft = true;
            pointerLeft = pointer;
        }
        if((screen.touchPos.x > 0.5f*screen.camera.viewportWidth) && screen.touchPos.y < 0.3f*screen.camera.viewportHeight){
            thetaRight = true;
            pointerRight = pointer;
        }

        screen.stageCoords.set((float) screenX, (float) screenY);
        screen.touchPos = screen.stage.screenToStageCoordinates(screen.stageCoords);
        screen.hitActor = screen.stage.hit(screen.touchPos.x, screen.touchPos.y, false);

        if(screen.hitActor != null){
            if(screen.hitActor == screen.hud.getChildren().get(0))
                screen.hud.getChildren().get(0).addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));

            if(screen.hitActor == screen.hud.getChildren().get(1))
                if(screen.settings.getBoolean("level_3_Unlocked", false))
                    screen.hud.getChildren().get(1).addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));

            if(screen.hitActor == screen.hud.getChildren().get(2))
                screen.hud.getChildren().get(2).addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));

            if(screen.hitActor == screen.hud.getChildren().get(3))
                screen.hud.getChildren().get(3).addAction(Actions.scaleTo(0.85f, 0.85f, 0.15f));
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer == pointerLeft)
            thetaLeft = false;
        if(pointer == pointerRight)
            thetaRight = false;

        if(!holdButtons && screen.hitActor != null) {
            if (screen.hitActor == screen.hud.getChildren().get(0)) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.transition.setZIndex(1);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_3())));
                holdButtons = true;
            }
            else if (screen.hitActor == screen.hud.getChildren().get(1)) {
                if(screen.settings.getBoolean("level_3_Unlocked", false)){
                    if(screen.musicOn)
                        screen.buttonClick.play();
                    screen.transition.setZIndex(1);
                    screen.transition.addAction(Actions.alpha(0f));
                    screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevel_4())));
                    holdButtons = true;
                }
            }
            if(screen.hitActor == screen.hud.getChildren().get(2)) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                screen.transition.setZIndex(1);
                screen.transition.addAction(Actions.alpha(0f));
                if(screen.levelsMusic.isPlaying())
                    screen.levelsMusic.stop();
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToLevels())));
                holdButtons = true;
            }
            else if (screen.hitActor == screen.hud.getChildren().get(3)) {
                if(screen.musicOn)
                    screen.buttonClick.play();
                if(screen.levelsMusic.isPlaying())
                    screen.levelsMusic.stop();
                screen.transition.setZIndex(1);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToMenu())));
                holdButtons = true;
            }

            screen.hud.getChildren().get(0).addAction(Actions.scaleTo(1f, 1f, 0.15f));
            screen.hud.getChildren().get(1).addAction(Actions.scaleTo(1f, 1f, 0.15f));
            screen.hud.getChildren().get(2).addAction(Actions.scaleTo(1f, 1f, 0.15f));
            screen.hud.getChildren().get(3).addAction(Actions.scaleTo(1f, 1f, 0.15f));
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
