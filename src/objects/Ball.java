package com.kalgames.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.kalgames.com.kalgames.assets.Storage;

import java.util.Random;

public class Ball {

    private Vector2 posBall, velocityBall, vectorChase;
    private float radius;

    float minWidth = -0.1f, maxWidth = 1.1f, finalWidth;
    float minHeight = 0.25f, maxHeight = 0.95f, finalHeight;
    private Random random = new Random();

    float moduleVector;

    private Sprite ballSprite;

    public Ball(){
        this.posBall = new Vector2();
        this.velocityBall = new Vector2();
        this.vectorChase = new Vector2();

        this.ballSprite = new Sprite(Storage.blackBallTex);
    }

    public Sprite getBallSprite(){
        return ballSprite;
    }

    public void setPosBall(float viewportWidth, float viewportHeight){
        switch(random.nextInt(4)){
            case 0:
                finalHeight = random.nextFloat() * (maxHeight - minHeight) + minHeight;
                posBall.set(minWidth*viewportWidth - radius, finalHeight * viewportHeight);
                break;
            case 1:
                finalHeight = random.nextFloat() * (maxHeight - minHeight) + minHeight;
                posBall.set(maxWidth*viewportWidth + radius, finalHeight * viewportHeight);
                break;
            case 2:
                finalWidth = random.nextFloat() * (maxWidth - minWidth) + minWidth;
                posBall.set(finalWidth * viewportWidth, minHeight*viewportHeight - radius);
                break;
            case 3:
                finalWidth = random.nextFloat() * (maxWidth - minWidth) + minWidth;
                posBall.set(finalWidth * viewportWidth, maxHeight*viewportHeight + radius);
                break;
        }
    }

    public Vector2 getPosBall(){
        return posBall;
    }

    public void setRandomRadius(float viewportWidth, int level_radius){
        switch(level_radius) {
            case 1:
                this.radius = 0.02f * viewportWidth;
                break;
            case 2:
                switch (random.nextInt(6)) {
                    case 5:
                        this.radius = 0.055f * viewportWidth;
                        break;
                    case 4:
                        this.radius = 0.04f * viewportWidth;
                        break;
                    case 3:
                        this.radius = 0.03f * viewportWidth;
                        break;
                    case 2:
                        this.radius = 0.008f * viewportWidth;
                        break;
                    default:
                        this.radius = 0.02f * viewportWidth;
                        break;
                }
                break;
            case 3:
                this.radius = 0.008f * viewportWidth;
                break;
            case 4:
                this.radius = 0.04f * viewportWidth;
                break;
        }
    }

    public float getRadius(){
        return radius;
    }

    public void setVelocityBall(Vector2 vectorChase, int level_vel){
        switch(level_vel) {
            case 1:
                velocityBall.set(180f * vectorChase.x, 180f * vectorChase.y);
                break;
            case 2:
                switch (random.nextInt(6)) {
                    case 5:
                        velocityBall.set(300f * vectorChase.x, 300f * vectorChase.y);
                        break;
                    case 4:
                        velocityBall.set(280f * vectorChase.x, 280f * vectorChase.y);
                        break;
                    case 3:
                        velocityBall.set(250f * vectorChase.x, 250f * vectorChase.y);
                        break;
                    case 2:
                        velocityBall.set(220f * vectorChase.x, 220f * vectorChase.y);
                        break;
                    default:
                        velocityBall.set(200f * vectorChase.x, 200f * vectorChase.y);
                        break;
                }
            case 3:
                velocityBall.set(220f * vectorChase.x, 220f * vectorChase.y);
                break;
            case 4:
                velocityBall.set(330f * vectorChase.x, 330f * vectorChase.y);
                break;
            case 5:
                velocityBall.set(280f * vectorChase.x, 280f * vectorChase.y);
                break;
            case 6:
                velocityBall.set(50f * vectorChase.x, 50f * vectorChase.y);
                break;
            case 7:
                velocityBall.set(380f * vectorChase.x, 380f * vectorChase.y);
                break;
        }
    }

    public Vector2 getVelocityBall(){
        return velocityBall;
    }

    public void setBallAttributes(float viewportWidth, float viewportHeight, Vector2 posPlayer, int level_vel, int level_radius){
        setRandomRadius(viewportWidth, level_radius);
        setPosBall(viewportWidth, viewportHeight);

        moduleVector = (float) Math.sqrt(Math.pow(posPlayer.x - posBall.x,2) + Math.pow(posPlayer.y - posBall.y, 2));
        vectorChase.set((posPlayer.x - posBall.x)/moduleVector,(posPlayer.y - posBall.y)/moduleVector);

        setVelocityBall(vectorChase, level_vel);
    }

    public boolean isBallCollided(Vector2 posPlayer, float playerRadius){
        return ((Math.pow(posPlayer.x - posBall.x, 2) + Math.pow(posPlayer.y - posBall.y, 2)) < Math.pow(playerRadius + radius, 2));
    }

    public boolean isBallAway(float viewportWidth, float viewportHeight){
        return(posBall.x > 1.2f*viewportWidth || posBall.x < -0.2f*viewportWidth ||
        posBall.y > viewportHeight || posBall.y < 0.2f*viewportHeight);
    }
}
