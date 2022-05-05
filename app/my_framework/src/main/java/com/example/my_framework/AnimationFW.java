package com.example.my_framework;

import android.graphics.Bitmap;

import com.example.my_framework.GraphicsFW;

public class AnimationFW {

    double speedAnimation;
    int delayIndex;
    int coutFrames;
    int frames;

    Bitmap sprite;
    Bitmap sprite1;
    Bitmap sprite2;
    Bitmap sprite3;
    Bitmap sprite4;

    public AnimationFW(double speedAnimation,
                       Bitmap sprite1,
                       Bitmap sprite2,
                       Bitmap sprite3,
                       Bitmap sprite4) {
        sprite = sprite1;
        this.sprite1 = sprite1;
        this.sprite2 = sprite2;
        this.sprite3 = sprite3;
        this.sprite4 = sprite4;
        this.speedAnimation = speedAnimation;
        frames = 4;
    }

    public void runAnimation() {
        delayIndex++;
        if (delayIndex > speedAnimation) {
            delayIndex = 0;
            nextFrame();
        }
    }

    private void nextFrame() {

        if (coutFrames == 0) {
            sprite = sprite1;
        }
        if (coutFrames == 1) {
            sprite = sprite2;
        }
        if (coutFrames == 2) {
            sprite = sprite3;
        }
        if (coutFrames == 3) {
            sprite = sprite4;
        }
        coutFrames++;
        if (coutFrames > frames) {
            coutFrames = 0;
        }
    }

    public void drawingAnimation(GraphicsFW graphicsFW, int x, int y) {
        graphicsFW.drawTexture(sprite, x, y);
    }
}
