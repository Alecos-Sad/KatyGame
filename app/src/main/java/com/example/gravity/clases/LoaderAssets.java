package com.example.gravity.clases;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.gravity.utilits.UtilResourse;

import java.util.ArrayList;

public class LoaderAssets {

    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
        loadSpriteEnemy(graphicsFW);
        loadOther(graphicsFW);
        loadAudio(coreFW);
        loadSpritePlayerShieldsOn(graphicsFW);
        loadGifts(graphicsFW);
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        UtilResourse.spriteProtector = new ArrayList<>();
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 256, 192, 32, 32));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 288, 192, 32, 32));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 320, 192, 32, 32));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 352, 192, 32, 32));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {
        UtilResourse.spritePlayerShieldsOn = new ArrayList<>();
        UtilResourse.spritePlayerShieldsOnBoost = new ArrayList<>();
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 0, 128, 64, 64));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 64, 128, 64, 64));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 128, 128, 64, 64));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 192, 128, 64, 64));

        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 0, 192, 64, 64));
        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 64, 192, 64, 64));
        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 128, 192, 64, 64));
        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 192, 192, 64, 64));
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResourse.gameMusic = coreFW.getAudioFW().newMusic("music.mp3");
        UtilResourse.hit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResourse.explode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResourse.touch = coreFW.getAudioFW().newSound("touch.ogg");

    }

    private void loadOther(GraphicsFW graphicsFW) {
        UtilResourse.shieldHitEnemy = graphicsFW.newSprite(UtilResourse.textureAtlas,0,128,64,64);
    }

    private void loadSpriteEnemy(GraphicsFW graphicsFW) {

        UtilResourse.spriteEnemy = new ArrayList<>();
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 256, 0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 320, 0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 384, 0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 448, 0, 64, 64));
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {

        UtilResourse.spritePlayer = new ArrayList<>();
        UtilResourse.spritePlayerBoost = new ArrayList<>();
        UtilResourse.spriteExplosinPlayer = new ArrayList<>();

        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 256, 256, 64, 64));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 320, 256, 64, 64));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 384, 256, 64, 64));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 448, 256, 64, 64));

        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 0, 0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 64, 0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 128, 0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 192, 0, 64, 64));

        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 0, 64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 64, 64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 128, 64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas, 192, 64, 64, 64));
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResourse.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }

}
