package com.kalgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.kalgames.com.kalgames.assets.Storage;
import com.kalgames.objects.Ball;
import com.kalgames.untouchable.Untouchable;

import java.util.Random;

public class ChallengeScreen implements Screen, InputProcessor{

    private final Untouchable screen;

    private Vector2 posPlayer, velocityPlayer, vectorDirection;
    private final float playerRadius, arenaRadius;
    private float theta;
    private boolean thetaLeft, thetaRight;
    private int pointerLeft, pointerRight;

    private float timedState;
    public static int score;
    public static boolean newRecord;

    private boolean gameStarted, gameFinished;

    private boolean backCalled;

    private Array<Ball> balls;
    private Sprite backGroundSprite, hudSprite, playerSprite, arenaSprite, leftSprite, rightSprite;

    private Random random;

    private float timeForDeathAnimation;
    private boolean setLastContent;

    private Image ballSpawnAnimation, ballSpawnAnimation2, ballSpawnAnimation3, ballSpawnAnimation4,
            ballSpawnAnimation5, ballSpawnAnimation6, ballSpawnAnimation7, ballSpawnAnimation8;

    private Sprite objectBall_1, objectBall_2, objectBall_3, objectBall_4, objectBall_5, objectBall_6,  objectBall_7, objectBall_8;

    private boolean drawObjectBall_1, drawObjectBall_2, drawRestBalls, atiremAvontade, iGiveUp, doAfterDeath;

    public ChallengeScreen(final Untouchable screen){
        this.screen = screen;

        this.posPlayer = new Vector2();
        this.velocityPlayer = new Vector2();
        this.vectorDirection = new Vector2();

        this.playerRadius = 0.02f*screen.camera.viewportWidth;
        this.arenaRadius = 0.9f*screen.camera.viewportWidth/2;

        this.random = new Random();

        this.balls = new Array<Ball>();

        this.backGroundSprite = new Sprite(Storage.backGroundTex);
        this.hudSprite = new Sprite(Storage.hud2Tex);
        this.playerSprite = new Sprite(Storage.playerBallTex);
        this.arenaSprite = new Sprite(Storage.arenaTex);
        this.leftSprite = new Sprite(Storage.leftTex);
        this.rightSprite = new Sprite(Storage.rightTex);
        leftSprite.setAlpha(0.55f);
        rightSprite.setAlpha(0.55f);

        ballSpawnAnimation = new Image(Storage.staticBallTex);
        ballSpawnAnimation.setOrigin(0.5f*ballSpawnAnimation.getWidth(), 0.5f*ballSpawnAnimation.getHeight());
        ballSpawnAnimation.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation.getWidth(),
                0.62f*screen.camera.viewportHeight + 0.5f*arenaRadius - 0.5f* ballSpawnAnimation.getHeight());

        ballSpawnAnimation2 = new Image(Storage.staticBallTex);
        ballSpawnAnimation2.setOrigin(0.5f*ballSpawnAnimation2.getWidth(), 0.5f*ballSpawnAnimation2.getHeight());
        ballSpawnAnimation2.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation2.getWidth(),
                0.62f*screen.camera.viewportHeight - 0.5f*arenaRadius - 0.5f* ballSpawnAnimation2.getHeight());

        ballSpawnAnimation3 = new Image(Storage.staticBallTex);
        ballSpawnAnimation3.setOrigin(0.5f*ballSpawnAnimation3.getWidth(), 0.5f*ballSpawnAnimation3.getHeight());
        ballSpawnAnimation3.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation3.getWidth() - 2*playerRadius,
                0.62f*screen.camera.viewportHeight + 0.5f*arenaRadius - 0.5f* ballSpawnAnimation3.getHeight());

        ballSpawnAnimation4 = new Image(Storage.staticBallTex);
        ballSpawnAnimation4.setOrigin(0.5f*ballSpawnAnimation4.getWidth(), 0.5f*ballSpawnAnimation4.getHeight());
        ballSpawnAnimation4.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation4.getWidth() + 2*playerRadius,
                0.62f*screen.camera.viewportHeight + 0.5f*arenaRadius - 0.5f* ballSpawnAnimation4.getHeight());

        ballSpawnAnimation5 = new Image(Storage.staticBallTex);
        ballSpawnAnimation5.setOrigin(0.5f*ballSpawnAnimation2.getWidth(), 0.5f*ballSpawnAnimation5.getHeight());
        ballSpawnAnimation5.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation5.getWidth() - 2*playerRadius,
                0.62f*screen.camera.viewportHeight - 0.5f*arenaRadius - 0.5f* ballSpawnAnimation5.getHeight());

        ballSpawnAnimation6 = new Image(Storage.staticBallTex);
        ballSpawnAnimation6.setOrigin(0.5f*ballSpawnAnimation2.getWidth(), 0.5f*ballSpawnAnimation6.getHeight());
        ballSpawnAnimation6.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation6.getWidth() + 2*playerRadius,
                0.62f*screen.camera.viewportHeight - 0.5f*arenaRadius - 0.5f* ballSpawnAnimation6.getHeight());

        ballSpawnAnimation7 = new Image(Storage.staticBallTex);
        ballSpawnAnimation7.setOrigin(0.5f*ballSpawnAnimation7.getWidth(), 0.5f*ballSpawnAnimation7.getHeight());
        ballSpawnAnimation7.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation7.getWidth() - 0.5f*arenaRadius,
                0.62f*screen.camera.viewportHeight - 0.5f* ballSpawnAnimation7.getHeight());

        ballSpawnAnimation8 = new Image(Storage.staticBallTex);
        ballSpawnAnimation8.setOrigin(0.5f*ballSpawnAnimation8.getWidth(), 0.5f*ballSpawnAnimation8.getHeight());
        ballSpawnAnimation8.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* ballSpawnAnimation8.getWidth() + 0.5f*arenaRadius,
                0.62f*screen.camera.viewportHeight - 0.5f* ballSpawnAnimation8.getHeight());

        this.objectBall_1 = new Sprite(Storage.staticBallTex);
        this.objectBall_3 = new Sprite(Storage.staticBallTex);
        this.objectBall_4 = new Sprite(Storage.staticBallTex);
        objectBall_1.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_1.getWidth(),
                0.62f*screen.camera.viewportHeight + 0.5f*arenaRadius - 0.5f* objectBall_1.getHeight());
        objectBall_3.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_1.getWidth() - 2*playerRadius,
                0.62f*screen.camera.viewportHeight + 0.5f*arenaRadius - 0.5f* objectBall_1.getHeight());
        objectBall_4.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_1.getWidth() + 2*playerRadius,
                0.62f*screen.camera.viewportHeight + 0.5f*arenaRadius - 0.5f* objectBall_1.getHeight());

        this.objectBall_2 = new Sprite(Storage.staticBallTex);
        this.objectBall_5 = new Sprite(Storage.staticBallTex);
        this.objectBall_6 = new Sprite(Storage.staticBallTex);
        objectBall_2.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_2.getWidth(),
                0.62f*screen.camera.viewportHeight - 0.5f*arenaRadius - 0.5f* objectBall_2.getHeight());
        objectBall_5.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_2.getWidth() - 2*playerRadius,
                0.62f*screen.camera.viewportHeight - 0.5f*arenaRadius - 0.5f* objectBall_2.getHeight());
        objectBall_6.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_2.getWidth() + 2*playerRadius,
                0.62f*screen.camera.viewportHeight - 0.5f*arenaRadius - 0.5f* objectBall_2.getHeight());

        this.objectBall_7 = new Sprite(Storage.staticBallTex);
        this.objectBall_8 = new Sprite(Storage.staticBallTex);
        objectBall_7.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_7.getWidth() - 0.5f*arenaRadius,
                0.62f*screen.camera.viewportHeight - 0.5f* objectBall_7.getHeight());
        objectBall_8.setPosition(0.5f*screen.camera.viewportWidth - 0.5f* objectBall_8.getWidth() + 0.5f*arenaRadius,
                0.62f*screen.camera.viewportHeight - 0.5f* objectBall_8.getHeight());
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

        score = 0;
        timedState = 0;
        newRecord = false;

        thetaLeft = false;
        thetaRight = false;

        gameStarted = false;
        gameFinished = false;

        screen.challengeMusic.setVolume(0.3f);

        backCalled = false;

        timeForDeathAnimation = 0;

        doAfterDeath = true;

        drawObjectBall_1 = false;
        drawObjectBall_2 = false;
        drawRestBalls = false;
        atiremAvontade = false;
        iGiveUp = false;

        setLastContent = false;

        balls.clear();
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());

        screen.stage.clear();

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
        else
            gameLoseLoop(delta);

        screen.stage.act(delta);
        screen.stage.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            if(!backCalled) {
                if(screen.challengeMusic.isPlaying())
                    screen.challengeMusic.stop();
                Timer.instance().clear();
                screen.transition.setZIndex(1);
                screen.transition.addAction(Actions.alpha(0f));
                screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToChallengeOverScreen())));
                backCalled = true;
            }
        }
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
        switch(score) {
            case(15):
                switch(random.nextInt(4)){
                    case(0):
                        balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,1,1);
                        break;
                    case(1):
                        balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,1);
                        break;
                    case(2):
                        balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                        break;
                    case(3):
                        balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,5,1);
                        break;
                }
                break;

            case(40):
                switch(random.nextInt(3)){
                    case(0):
                        balls.get(1).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                        break;
                    case(1):
                        balls.get(1).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,4);
                        break;
                    case(2):
                        balls.get(1).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,1);
                        break;
                }
                break;

            case(55):
                switch(random.nextInt(3)){
                    case(0):
                        balls.get(2).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                        break;
                    case(1):
                        balls.get(2).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,4);
                        break;
                    case(2):
                        balls.get(2).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,1);
                        break;
                }
                break;

            case(70):
                switch(random.nextInt(3)){
                    case(0):
                        balls.get(3).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                        balls.get(4).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                        balls.get(5).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
                        break;
                    case(1):
                        balls.get(3).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                        balls.get(4).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                        balls.get(5).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                        break;
                    case(2):
                        balls.get(3).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,3);
                        balls.get(4).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,3);
                        break;
                }
                break;

            case(85):
                balls.get(6).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                break;

            case(95):
                balls.get(7).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                break;

            case(105):
                balls.get(8).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                break;

            case(120):
                balls.get(9).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                balls.get(10).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                balls.get(11).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,4,1);
                break;

            case(135):
                spawnChainBall(balls.get(12), balls.get(13), balls.get(14));
                break;

            case(150):
                balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                balls.get(1).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                break;
            case(154):
                balls.get(2).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                balls.get(15).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                break;

            case(155):
                ballSpawnAnimation.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.5f), Actions.run(screen.runnables.stageRemove()))));
                screen.stage.addActor(ballSpawnAnimation);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        drawObjectBall_1 = true;
                    }
                }, 0.4f);
                break;

            case(156):
                balls.get(3).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                balls.get(4).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                balls.get(5).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                break;
            case(162):
                balls.get(16).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                balls.get(17).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,6,1);
                break;

            case(165):
                ballSpawnAnimation2.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.5f), Actions.run(screen.runnables.stageRemove()))));
                screen.stage.addActor(ballSpawnAnimation2);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        drawObjectBall_2 = true;
                    }
                }, 0.4f);
                break;

            case(185):
                balls.get(6).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,1);
                break;
            case(195):
                balls.get(7).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,1);
                break;
            case(205):
                balls.get(8).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,1);
                break;
            case(230):
                balls.get(9).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,1);
                break;
            case(240):
                balls.get(10).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,1);
                break;
            case(250):
                balls.get(11).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,1);
                break;

            case(270):
                spawnChainBall(balls.get(12), balls.get(13), balls.get(14));
                spawnChainBall(balls.get(6), balls.get(7), balls.get(8));
                break;

            case(300):
                ballSpawnAnimation3.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.6f), Actions.run(screen.runnables.stageRemove()))));
                ballSpawnAnimation4.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.6f), Actions.run(screen.runnables.stageRemove()))));
                ballSpawnAnimation5.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.6f), Actions.run(screen.runnables.stageRemove()))));
                ballSpawnAnimation6.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.6f), Actions.run(screen.runnables.stageRemove()))));
                ballSpawnAnimation7.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.6f), Actions.run(screen.runnables.stageRemove()))));
                ballSpawnAnimation8.addAction(Actions.parallel(Actions.scaleTo(4f, 4f), Actions.sequence(Actions.scaleTo(1f, 1f, 0.6f), Actions.run(screen.runnables.stageRemove()))));
                screen.stage.addActor(ballSpawnAnimation3);
                screen.stage.addActor(ballSpawnAnimation4);
                screen.stage.addActor(ballSpawnAnimation5);
                screen.stage.addActor(ballSpawnAnimation6);
                screen.stage.addActor(ballSpawnAnimation7);
                screen.stage.addActor(ballSpawnAnimation8);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        drawRestBalls = true;
                    }
                }, 0.5f);
                break;

            case(305):
                balls.get(9).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,3);
                balls.get(10).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,3);
                break;

            case(315):
                balls.get(11).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,3);
                balls.get(12).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,3);
                break;

            case(330):
                balls.get(13).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,1);
                break;

            case(340):
                atiremAvontade = true;
                break;

            case(355):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;

            case(360):
                balls.removeIndex(17);
                balls.removeIndex(16);
                balls.removeIndex(15);
                balls.removeIndex(14);
                balls.removeIndex(13);
                balls.removeIndex(12);
                balls.removeIndex(11);
                balls.removeIndex(10);
                balls.removeIndex(9);
                balls.removeIndex(8);
                balls.removeIndex(7);
                balls.removeIndex(6);
                break;

            case(405):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;

            case(455):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;

            case(505):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(555):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(605):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(655):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(705):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(755):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(805):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(855):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(905):
                spawnChainBall(balls.get(3), balls.get(4), balls.get(5));
                break;
            case(955):
                iGiveUp = true;
                balls.removeIndex(5);
                break;
        }
    }

    public void gameLoop(float delta){
        if(timedState >= 0.1f) {
            timedState = 0;
            score += 1;
            spawnBallOnScore();
        }

        handlingInput(delta);
        positionUpdate(delta);

        screen.batch.begin();

        backGroundSprite.draw(screen.batch);
        arenaSprite.draw(screen.batch);
        if(drawObjectBall_1)
            objectBall_1.draw(screen.batch);
        if(drawObjectBall_2)
            objectBall_2.draw(screen.batch);
        if(drawRestBalls){
            objectBall_3.draw(screen.batch);
            objectBall_4.draw(screen.batch);
            objectBall_5.draw(screen.batch);
            objectBall_6.draw(screen.batch);
            objectBall_7.draw(screen.batch);
            objectBall_8.draw(screen.batch);
        }
        for (Ball ball : balls) {
            ball.getBallSprite().setSize(2*ball.getRadius(), 2*ball.getRadius());
            ball.getBallSprite().draw(screen.batch);
        }
        hudSprite.draw(screen.batch);
        leftSprite.draw(screen.batch);
        rightSprite.draw(screen.batch);
        playerSprite.draw(screen.batch);
        screen.glyphLayout.setText(screen.scoreFont, "Score: " + String.valueOf(score));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.32f*screen.camera.viewportWidth, 0.984f*screen.camera.viewportHeight);

        screen.batch.end();

        if(drawObjectBall_1) {
            if (objectBall1Collided())
                gameFinished = true;
        }
        if(drawObjectBall_2) {
            if (objectBall2Collided())
                gameFinished = true;
        }

        if(drawRestBalls){
            if(restBallsCollided())
                gameFinished = true;
        }

        for (Ball ball : balls) {
            if(ball.isBallCollided(posPlayer, playerRadius))
                gameFinished = true;
        }

        if(isPlayerOutArena())
            gameFinished = true;

        if(atiremAvontade){
            if(balls.get(0).isBallAway(screen.camera.viewportWidth, screen.camera.viewportHeight))
                balls.get(0).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,2,1);
            if(balls.get(1).isBallAway(screen.camera.viewportWidth, screen.camera.viewportHeight))
                balls.get(1).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,3,2);
            if(balls.get(2).isBallAway(screen.camera.viewportWidth, screen.camera.viewportHeight))
                balls.get(2).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,7,1);
            if(iGiveUp){
                if(balls.get(3).isBallAway(screen.camera.viewportWidth, screen.camera.viewportHeight))
                    balls.get(3).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,2,1);
                if(balls.get(4).isBallAway(screen.camera.viewportWidth, screen.camera.viewportHeight))
                    balls.get(4).setBallAttributes(screen.camera.viewportWidth, screen.camera.viewportHeight, posPlayer,2,1);
            }
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
        screen.glyphLayout.setText(screen.scoreFont, "Score: " + String.valueOf(score));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.32f*screen.camera.viewportWidth, 0.984f*screen.camera.viewportHeight);
        screen.glyphLayout.setText(screen.scoreFont, "Tap to start!");
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.5f*screen.camera.viewportWidth - 0.5f*screen.glyphLayout.width, 0.75f*screen.camera.viewportHeight);

        screen.batch.end();

        if(Gdx.input.justTouched()) {
            gameStarted = true;
            if(screen.musicOn)
                if(!screen.challengeMusic.isPlaying())
                    screen.challengeMusic.play();
        }
    }

    public void gameLoseLoop(float delta){
        if(doAfterDeath){
            if(screen.challengeMusic.isPlaying()) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        screen.challengeMusic.setVolume(screen.challengeMusic.getVolume() - 0.03f);
                    }
                }, 0f, 0.1f, 9);
            }

            if(screen.musicOn)
                screen.deathSound.play(0.3f);
            doAfterDeath = false;

            screen.deathCount += 1;
            if(screen.deathCount == 8){
                if(screen.adsController.isWifiConnected() == 1) {
                    Timer .schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            if(screen.challengeMusic.isPlaying())
                                screen.challengeMusic.stop();
                            screen.adsController.showInterstitialAd(screen.runnables.changeToChallengeOverScreen());
                            Timer.instance().clear();
                        }
                    }, 1.2f);
                }
                screen.deathCount = 0;
            }
        }

        updateAfterDeath(delta);
        screen.batch.begin();

        backGroundSprite.draw(screen.batch);
        arenaSprite.draw(screen.batch);
        if(drawObjectBall_1)
            objectBall_1.draw(screen.batch);
        if(drawObjectBall_2)
            objectBall_2.draw(screen.batch);
        if(drawRestBalls){
            objectBall_3.draw(screen.batch);
            objectBall_4.draw(screen.batch);
            objectBall_5.draw(screen.batch);
            objectBall_6.draw(screen.batch);
            objectBall_7.draw(screen.batch);
            objectBall_8.draw(screen.batch);
        }
        for (Ball ball : balls) {
            ball.getBallSprite().setSize(2*ball.getRadius(), 2*ball.getRadius());
            ball.getBallSprite().draw(screen.batch);
        }
        hudSprite.draw(screen.batch);
        leftSprite.draw(screen.batch);
        rightSprite.draw(screen.batch);
        screen.batch.draw(screen.deathAnimation.getKeyFrame(timeForDeathAnimation, false), posPlayer.x - 18.5f, posPlayer.y - 18.5f);
        screen.glyphLayout.setText(screen.scoreFont, "Score: " + String.valueOf(score));
        screen.scoreFont.draw(screen.batch, screen.glyphLayout, 0.32f*screen.camera.viewportWidth, 0.984f*screen.camera.viewportHeight);

        screen.batch.end();

        if(!setLastContent){
            if(score > screen.settings.getInteger("high_score", 0)){
                if(!(screen.settings.getInteger("high_score", 0) == 0))
                    newRecord = true;
                screen.settings.putInteger("high_score", score);
                screen.settings.flush();
                screen.playServices.submitScore(score);
            }

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(screen.challengeMusic.isPlaying())
                        screen.challengeMusic.stop();
                    screen.transition.setZIndex(1);
                    screen.transition.addAction(Actions.alpha(0f));
                    screen.transition.addAction(Actions.sequence(Actions.fadeIn(0.4f), Actions.run(screen.runnables.changeToChallengeOverScreen())));
                }
            }, 1.5f);
            setLastContent = true;
        }

        timeForDeathAnimation += delta;
    }

    public boolean objectBall1Collided(){
        return((Math.pow(posPlayer.x - (objectBall_1.getX() + 0.5f*objectBall_1.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_1.getY() + 0.5f*objectBall_1.getHeight()), 2)) < Math.pow(2*playerRadius, 2));
    }

    public boolean objectBall2Collided(){
        return((Math.pow(posPlayer.x - (objectBall_2.getX() + 0.5f*objectBall_2.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_2.getY() + 0.5f*objectBall_2.getHeight()), 2)) < Math.pow(2*playerRadius, 2));
    }

    public boolean restBallsCollided(){
        return((Math.pow(posPlayer.x - (objectBall_3.getX() + 0.5f*objectBall_1.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_3.getY() + 0.5f*objectBall_1.getHeight()), 2)) < Math.pow(2*playerRadius, 2) ||
                (Math.pow(posPlayer.x - (objectBall_4.getX() + 0.5f*objectBall_1.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_4.getY() + 0.5f*objectBall_1.getHeight()), 2)) < Math.pow(2*playerRadius, 2) ||
                (Math.pow(posPlayer.x - (objectBall_5.getX() + 0.5f*objectBall_2.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_5.getY() + 0.5f*objectBall_2.getHeight()), 2)) < Math.pow(2*playerRadius, 2) ||
                (Math.pow(posPlayer.x - (objectBall_6.getX() + 0.5f*objectBall_2.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_6.getY() + 0.5f*objectBall_2.getHeight()), 2)) < Math.pow(2*playerRadius, 2) ||
                (Math.pow(posPlayer.x - (objectBall_7.getX() + 0.5f*objectBall_2.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_7.getY() + 0.5f*objectBall_2.getHeight()), 2)) < Math.pow(2*playerRadius, 2) ||
                (Math.pow(posPlayer.x - (objectBall_8.getX() + 0.5f*objectBall_2.getWidth()), 2) + Math.pow(posPlayer.y - (objectBall_8.getY() + 0.5f*objectBall_2.getHeight()), 2)) < Math.pow(2*playerRadius, 2));
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

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer == pointerLeft)
            thetaLeft = false;
        if(pointer == pointerRight)
            thetaRight = false;
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

    public void spawnChainBall(Ball ball_1, Ball ball_2, Ball ball_3){
        ball_1.setRandomRadius(screen.camera.viewportWidth, 1);
        ball_2.setRandomRadius(screen.camera.viewportWidth, 1);
        ball_3.setRandomRadius(screen.camera.viewportWidth, 1);
        switch(random.nextInt(4)) {
            case(0):
                ball_1.getPosBall().set(0.5f*screen.camera.viewportWidth, 0.25f*screen.camera.viewportHeight);
                ball_2.getPosBall().set(ball_1.getPosBall().x + 2 * ball_2.getRadius(), ball_1.getPosBall().y);
                ball_3.getPosBall().set(ball_1.getPosBall().x - 2 * ball_3.getRadius(), ball_1.getPosBall().y);
                ball_1.getVelocityBall().set(0, 250);
                ball_2.getVelocityBall().set(0, 250);
                ball_3.getVelocityBall().set(0, 250);
                break;
            case(1):
                ball_1.getPosBall().set(0.5f*screen.camera.viewportWidth, 0.95f*screen.camera.viewportHeight);
                ball_2.getPosBall().set(ball_1.getPosBall().x + 2 * ball_2.getRadius(), ball_1.getPosBall().y);
                ball_3.getPosBall().set(ball_1.getPosBall().x - 2 * ball_3.getRadius(), ball_1.getPosBall().y);
                ball_1.getVelocityBall().set(0,-250);
                ball_2.getVelocityBall().set(0,-250);
                ball_3.getVelocityBall().set(0,-250);
                break;
            case(2):
                ball_1.getPosBall().set(-0.1f*screen.camera.viewportWidth, 0.62f*screen.camera.viewportHeight);
                ball_2.getPosBall().set(ball_1.getPosBall().x, ball_1.getPosBall().y + 2 * ball_2.getRadius());
                ball_3.getPosBall().set(ball_1.getPosBall().x, ball_1.getPosBall().y - 2 * ball_3.getRadius());
                ball_1.getVelocityBall().set(250, 0);
                ball_2.getVelocityBall().set(250, 0);
                ball_3.getVelocityBall().set(250, 0);
                break;
            case(3):
                ball_1.getPosBall().set(1.1f*screen.camera.viewportWidth, 0.62f*screen.camera.viewportHeight);
                ball_2.getPosBall().set(ball_1.getPosBall().x, ball_1.getPosBall().y + 2 * ball_2.getRadius());
                ball_3.getPosBall().set(ball_1.getPosBall().x, ball_1.getPosBall().y - 2 * ball_3.getRadius());
                ball_1.getVelocityBall().set(-250, 0);
                ball_2.getVelocityBall().set(-250, 0);
                ball_3.getVelocityBall().set(-250, 0);
                break;
        }
    }
}
