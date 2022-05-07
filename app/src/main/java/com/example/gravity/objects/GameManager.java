package com.example.gravity.objects;

import com.example.gravity.generator.GeneratorBackground;
import com.example.gravity.generator.GeneratorEnemy;
import com.example.gravity.generator.GeneratorGifs;
import com.example.gravity.utilits.UtilResourse;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.utilits.CollisionDetect;

public class GameManager {
    //region Fields
    public final static double SPEED_ANIMATION = 3;
    private int mPassedDistance;
    public static boolean gameOver;

    private MainPlayer mMainPlayer;
    private GeneratorBackground mGeneratorBackground;
    private GeneratorEnemy mGeneratorEnemy;
    private GeneratorGifs mGeneratorGifs;
    private HUD mHud;
    //endregion

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        init(coreFW, sceneWidth, sceneHeight);
    }

    private void init(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        mHud = new HUD(coreFW);
        int mMinScreenY = mHud.getHEIGHT_HUD();
        mMainPlayer = new MainPlayer(coreFW, sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorBackground = new GeneratorBackground(sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorGifs = new GeneratorGifs(sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, mMinScreenY);
        gameOver = false;
    }

    public void update() {

        mGeneratorBackground.update(mMainPlayer.getSpeedPlayer());
        mMainPlayer.update();
        mGeneratorEnemy.update(mMainPlayer.getSpeedPlayer());
        mGeneratorGifs.update(mMainPlayer.getSpeedPlayer());
        mPassedDistance += mMainPlayer.getSpeedPlayer();
        int mCurrentSpeedPlayer = (int) mMainPlayer.getSpeedPlayer() * 60;
        int mCurrentShieldsPlayer = mMainPlayer.getShieldsPlayer();
        mHud.update(mPassedDistance, mCurrentSpeedPlayer, mCurrentShieldsPlayer);
        checkHit();

    }

    private void checkHit() {
        for (int i = 0; i < mGeneratorEnemy.enemyArrayList.size(); i++) {
            if (CollisionDetect.collisionDetect(mMainPlayer, mGeneratorEnemy.enemyArrayList.get(i))) {
                UtilResourse.sHit.play(1);
                mMainPlayer.hitEnemy();
                mGeneratorEnemy.hitPlayer(mGeneratorEnemy.enemyArrayList.get(i));
            }
        }
        if (CollisionDetect.collisionDetect(mMainPlayer, mGeneratorGifs.getProtector())) {
            hitPlayerWithProtector();
        }
    }

    private void hitPlayerWithProtector() {
        mMainPlayer.hitProtector();
        mGeneratorGifs.hitProtectorWithPlayers();
    }

    public void drawing(GraphicsFW graphicsFW) {
        mMainPlayer.drawing(graphicsFW);
        mGeneratorBackground.drawing(graphicsFW);
        mGeneratorGifs.drawing(graphicsFW);
        mGeneratorEnemy.drawing(graphicsFW);
        mHud.drawing(graphicsFW);
    }

    public int getPassedDistance() {
        return mPassedDistance;
    }
}
