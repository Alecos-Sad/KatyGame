package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.gravity.utilits.UtilResourse;
import com.example.my_framework.utilits.UtilTimerDelay;

public class MainPlayer extends ObjectFW {

    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;
    AnimationFW animMainPlayer;
    AnimationFW animMainPlayerBoost;
    AnimationFW animExplosionPlayer;
    CoreFW coreFW;
    UtilTimerDelay timerOnShieldHit;
    UtilTimerDelay timerOnGameOver;

    boolean boosting;
    private int shieldsPlayer;
    boolean hitEnemy;
    boolean isGameOver;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 3;
        shieldsPlayer = 3; // количество жизней
        boosting = false;
        hitEnemy = false;
        isGameOver = false;

        radius = UtilResourse.spritePlayer.get(0).getWidth() / 4;

        timerOnShieldHit = new UtilTimerDelay();
        timerOnGameOver = new UtilTimerDelay();

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
        animMainPlayer = new AnimationFW(speed, UtilResourse.spritePlayer.get(0),
                UtilResourse.spritePlayer.get(1),
                UtilResourse.spritePlayer.get(2),
                UtilResourse.spritePlayer.get(3));
        animMainPlayerBoost = new AnimationFW(speed, UtilResourse.spritePlayerBoost.get(0),
                UtilResourse.spritePlayerBoost.get(1),
                UtilResourse.spritePlayerBoost.get(2),
                UtilResourse.spritePlayerBoost.get(3));
        animExplosionPlayer = new AnimationFW(speed, UtilResourse.spriteExplosinPlayer.get(0),
                UtilResourse.spriteExplosinPlayer.get(1),
                UtilResourse.spriteExplosinPlayer.get(2),
                UtilResourse.spriteExplosinPlayer.get(3));

    }

    public void update() {

        if (coreFW.getTouchLictenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }
        if (coreFW.getTouchLictenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }
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
            animMainPlayerBoost.runAnimation();
        } else animMainPlayer.runAnimation();

        hitBox = new Rect(x, y,
                UtilResourse.spritePlayer.get(0).getWidth(),
                UtilResourse.spritePlayer.get(0).getHeight());
        if (isGameOver) {
            animExplosionPlayer.runAnimation();
        }
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
                    animMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
                } else animMainPlayer.drawingAnimation(graphicsFW, x, y);
            } else {
                graphicsFW.drawTexture(UtilResourse.shieldHitEnemy, x, y);
                if (timerOnShieldHit.timerDelay(2)) {                                //анимация защиты
                    hitEnemy = false;
                } else hitEnemy = true;
            }
        } else{
            animExplosionPlayer.drawingAnimation(graphicsFW, x, y);
            if (timerOnGameOver.timerDelay(0.5)){
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
        shieldsPlayer--;
        if (shieldsPlayer < 0) {
            isGameOver = true;
            timerOnGameOver.startTimer();
        }
        hitEnemy = true;
        timerOnShieldHit.startTimer();
    }
}
