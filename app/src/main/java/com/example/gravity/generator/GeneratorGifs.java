package com.example.gravity.generator;

import com.example.gravity.objects.MainPlayer;
import com.example.gravity.objects.Protector;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.utilits.UtilTimerDelay;

public class GeneratorGifs {

    private Protector mProtector;
    private UtilTimerDelay timerProtector;

    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

    public GeneratorGifs(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);

    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        mProtector = new Protector(maxScreenX, maxScreenY, minScreenY);
        timerProtector = new UtilTimerDelay();
        timerProtector.startTimer();
    }

    public void drawing(GraphicsFW graphicsFW) {
        mProtector.drawing(graphicsFW);
    }

    public void update(double speedPlayer) {
        if (timerProtector.timerDelay(8) && (!MainPlayer.isShieldsOn())) {
            mProtector.update(speedPlayer);
            if (mProtector.getX() < minScreenX) {
                mProtector = null;
                mProtector = new Protector(maxScreenX, maxScreenY, minScreenY);
                timerProtector.startTimer();
            }
        }
    }

    public Protector getProtector() {
        return mProtector;
    }

    public void hitProtectorWithPlayers() {
       mProtector = null;
       mProtector = new Protector(maxScreenX, maxScreenY, minScreenY);
       timerProtector.startTimer();
    }
}
