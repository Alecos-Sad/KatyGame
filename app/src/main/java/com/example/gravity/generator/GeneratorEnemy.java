package com.example.gravity.generator;

import com.example.gravity.objects.Enemy;
import com.example.my_framework.GraphicsFW;

import java.util.ArrayList;

public class GeneratorEnemy {
    private int mMaxScreenY;
    private int mMaxScreenX;
    private int mMinScreenY;
    public ArrayList<Enemy> enemyArrayList;

    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        this.mMaxScreenX = sceneWidth;
        this.mMaxScreenY = sceneHeight;
        this.mMinScreenY = minScreenY;
        enemyArrayList = new ArrayList<>();
    }

    public void update(double speedPlayer) {
        if (enemyArrayList.size() < 3) {
            addEnemy( 3);
        }
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).update(speedPlayer);
        }
    }

    private void addEnemy(int amountEnemy) {
        for (int i = 0; i < amountEnemy; i++) {
            enemyArrayList.add(new Enemy(mMaxScreenX, mMaxScreenY, mMinScreenY, 1));
        }
    }

    public void drawing(GraphicsFW graphicsFW){
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).drawing(graphicsFW);
        }
    }

    public void hitPlayer(Enemy enemy) {
        for (int i = 0; i < enemyArrayList.size() ; i++) {
            enemyArrayList.remove(enemy);
        }
    }
}
