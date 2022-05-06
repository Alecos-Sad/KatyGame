package com.example.gravity.generator;

import com.example.gravity.objects.MainPlayer;
import com.example.gravity.objects.Protector;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.utilits.UtilTimerDelay;

public class GeneratorGifs {

    Protector protector;
    UtilTimerDelay timerProtector;

    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

    public GeneratorGifs(int sceneWidth, int sceneHeight, int minScreenY) {

        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        protector = new Protector(maxScreenX, maxScreenY, minScreenY);
        timerProtector = new UtilTimerDelay();
        timerProtector.startTimer();

    }

    public void drawing(GraphicsFW graphicsFW) {
        protector.drawing(graphicsFW);
    }

    public void update(double speedPlayer) {
        if (timerProtector.timerDelay(8) && (!MainPlayer.isShieldsOn())) {
            protector.update(speedPlayer);
            if (protector.getX() < minScreenX) {
                protector = null;
                protector = new Protector(maxScreenX, maxScreenY, minScreenY);
                timerProtector.startTimer();
            }
        }
    }

    public Protector getProtector() {
        return protector;
    }

    public void hitProtectorWithPlayers() {
       protector = null;
       protector = new Protector(maxScreenX, maxScreenY, minScreenY);
       timerProtector.startTimer();
    }
}
