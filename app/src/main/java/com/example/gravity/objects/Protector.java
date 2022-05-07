package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.utilits.UtilResourse;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilRandomFW;

public class Protector extends ObjectFW {

    private AnimationFW mAnimProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {

        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.sSpriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResourse.sSpriteProtector.get(0).getWidth() / 2;
        hitBox = new Rect(x, y,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getHeight());
        mAnimProtector = new AnimationFW(GameManager.SPEED_ANIMATION,
                UtilResourse.sSpriteProtector.get(0),
                UtilResourse.sSpriteProtector.get(1),
                UtilResourse.sSpriteProtector.get(2),
                UtilResourse.sSpriteProtector.get(3));
    }

    public void update(double speedPlayer) {

        x -= speed;
        x -= speedPlayer;

        if (x < minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        mAnimProtector.runAnimation();

        hitBox = new Rect(x, y,
                UtilResourse.sSpriteEnemy.get(0).getWidth(),
                UtilResourse.sSpriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        mAnimProtector.drawingAnimation(graphicsFW, x, y);
    }
}
