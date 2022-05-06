package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.utilits.UtilResourse;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilRandomFW;

public class Protector extends ObjectFW {

    AnimationFW animProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {

        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResourse.spriteProtector.get(0).getWidth() / 2;
        hitBox = new Rect(x, y,
                UtilResourse.spriteProtector.get(0).getWidth(),
                UtilResourse.spriteProtector.get(0).getHeight());
        animProtector = new AnimationFW(GameManager.SPEED_ANIMATION,
                UtilResourse.spriteProtector.get(0),
                UtilResourse.spriteProtector.get(1),
                UtilResourse.spriteProtector.get(2),
                UtilResourse.spriteProtector.get(3));
    }

    public void update(double speedPlayer) {

        x -= speed;
        x -= speedPlayer;

        if (x < minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        animProtector.runAnimation();

        hitBox = new Rect(x, y,
                UtilResourse.spriteEnemy.get(0).getWidth(),
                UtilResourse.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        animProtector.drawingAnimation(graphicsFW, x, y);
    }
}
