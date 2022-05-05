package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.utilits.UtilResourse;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilRandomFW;

public class Enemy extends ObjectFW {

    AnimationFW animEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {

        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spriteEnemy.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResourse.spriteEnemy.get(0).getWidth() / 4;
        switch (enemyType) {
            case 1:
                speed = UtilRandomFW.getGap(1, 6);
                animEnemy = new AnimationFW(3,
                        UtilResourse.spriteEnemy.get(0),
                        UtilResourse.spriteEnemy.get(1),
                        UtilResourse.spriteEnemy.get(2),
                        UtilResourse.spriteEnemy.get(3));
                break;

            case 2:
                speed = UtilRandomFW.getGap(4, 9);
                break;
        }
    }

    public void update(double speedPlayer) {
        x -= speed;
        x -= speedPlayer;
        if (x < minScreenX) {
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        animEnemy.runAnimation();

        hitBox = new Rect(x, y,
                UtilResourse.spriteEnemy.get(0).getWidth(),
                UtilResourse.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        animEnemy.drawingAnimation(graphicsFW, x, y);
    }
}
