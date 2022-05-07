package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.utilits.UtilResourse;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilTimerDelay;

public class MainPlayer extends ObjectFW {

    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;
    AnimationFW animMainPlayer;
    AnimationFW animMainPlayerBoost;
    AnimationFW animExplosionPlayer;
    AnimationFW animPlayerShieldsOn;
    AnimationFW animPlayerShieldsOnBoost;
    CoreFW coreFW;
    UtilTimerDelay timerOnShieldHit;
    UtilTimerDelay timerOnGameOver;
    UtilTimerDelay timerShieldsOn;

    boolean boosting;
    private int shieldsPlayer;
    boolean hitEnemy;
    boolean isGameOver;
    static boolean shieldsOn;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {

        shieldsOn = false;
        x = 20;
        y = 200;
        speed = GameManager.SPEED_ANIMATION;
        shieldsPlayer = 3; // количество жизней
        boosting = false;
        hitEnemy = false;
        isGameOver = false;

        radius = UtilResourse.sSpritePlayer.get(0).getWidth() / 4;

        timerOnShieldHit = new UtilTimerDelay();
        timerOnGameOver = new UtilTimerDelay();
        timerShieldsOn = new UtilTimerDelay();

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.sSpritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
        initAnimation();

    }

    private void initAnimation() {

        animMainPlayer = new AnimationFW(speed, UtilResourse.sSpritePlayer.get(0),
                UtilResourse.sSpritePlayer.get(1),
                UtilResourse.sSpritePlayer.get(2),
                UtilResourse.sSpritePlayer.get(3));
        animMainPlayerBoost = new AnimationFW(speed, UtilResourse.sSpritePlayerBoost.get(0),
                UtilResourse.sSpritePlayerBoost.get(1),
                UtilResourse.sSpritePlayerBoost.get(2),
                UtilResourse.sSpritePlayerBoost.get(3));
        animExplosionPlayer = new AnimationFW(speed, UtilResourse.sSpriteExplosionPlayer.get(0),
                UtilResourse.sSpriteExplosionPlayer.get(1),
                UtilResourse.sSpriteExplosionPlayer.get(2),
                UtilResourse.sSpriteExplosionPlayer.get(3));
        animPlayerShieldsOn = new AnimationFW(speed, UtilResourse.sSpritePlayerShieldsOn.get(0),
                UtilResourse.sSpritePlayerShieldsOn.get(1),
                UtilResourse.sSpritePlayerShieldsOn.get(2),
                UtilResourse.sSpritePlayerShieldsOn.get(3));
        animPlayerShieldsOnBoost = new AnimationFW(speed, UtilResourse.sSpritePlayerShieldsOnBoost.get(0),
                UtilResourse.sSpritePlayerShieldsOnBoost.get(1),
                UtilResourse.sSpritePlayerShieldsOnBoost.get(2),
                UtilResourse.sSpritePlayerShieldsOnBoost.get(3));

    }

    public void update() {

        if (coreFW.getTouchListenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }
        if (coreFW.getTouchListenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }
        if (timerShieldsOn.timerDelay(5)) {
            shieldsOn = false;
        }

        updateBoosting();

        hitBox = new Rect(x, y,
                UtilResourse.sSpritePlayer.get(0).getWidth(),
                UtilResourse.sSpritePlayer.get(0).getHeight());
        if (isGameOver) {
            animExplosionPlayer.runAnimation();
        }
    }

    private void updateBoosting() {
        if (boosting) {
            speed += 0.1;
        } else speed -= 3;

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY;
        if (y < minScreenY) {
            y = minScreenY;
        }
        if (y > maxScreenY) {
            y = maxScreenY;
        }

        if (boosting) {
            if (shieldsOn) {
                animPlayerShieldsOnBoost.runAnimation();
            } else animMainPlayerBoost.runAnimation();
        } else if (shieldsOn) {
            animPlayerShieldsOn.runAnimation();
        } else animMainPlayer.runAnimation();
    }

    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {

        if (!isGameOver) {
            if (!hitEnemy) {
                if (boosting) {
                    if (shieldsOn) {
                        animPlayerShieldsOnBoost.drawingAnimation(graphicsFW, x, y);
                    } else animMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
                } else if (shieldsOn) {
                    animPlayerShieldsOn.drawingAnimation(graphicsFW, x, y);
                } else animMainPlayer.drawingAnimation(graphicsFW, x, y);
            } else {
                graphicsFW.drawTexture(UtilResourse.sShieldHitEnemy, x, y);
                //анимация защиты
                hitEnemy = !timerOnShieldHit.timerDelay(2);
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsFW, x, y);
            if (timerOnGameOver.timerDelay(0.5)) {
                GameManager.gameOver = true;
            }
        }
    }

    public double getSpeedPlayer() {
        return speed;
    }

    public int getShieldsPlayer() {
        return shieldsPlayer;
    }

    public void hitEnemy() {

        if (!shieldsOn) {
            shieldsPlayer--;
            if (shieldsPlayer < 0) {
                UtilResourse.sExplode.play(1);
                isGameOver = true;
                timerOnGameOver.startTimer();
            }
            hitEnemy = true;
            timerOnShieldHit.startTimer();
        }
    }

    public static boolean isShieldsOn() {
        return shieldsOn;
    }

    public void hitProtector() {
        shieldsOn = true;
        timerShieldsOn.startTimer();
    }

}
