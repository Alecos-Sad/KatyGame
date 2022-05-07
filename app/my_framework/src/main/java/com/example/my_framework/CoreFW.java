package com.example.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity {

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;

    private AudioFW audioFW;
    private SceneFW sceneFW;

    private boolean stateOnPause;
    private SharedPreferences sharedPreferences;

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String SETTINGS = "settings";
        sharedPreferences = getSharedPreferences(SETTINGS,MODE_PRIVATE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Point sizeDisplay = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);

        float FRAME_BUFFER_HEIGHT = 600;
        float FRAME_BUFFER_WIDTH = 800;
        Bitmap frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        float sceneWidth = FRAME_BUFFER_WIDTH / sizeDisplay.x;
        float sceneHeight = FRAME_BUFFER_HEIGHT / sizeDisplay.y;

        audioFW = new AudioFW(this);

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, sceneWidth, sceneHeight);

        sceneFW = getStartScene();
        stateOnPause = false;
        boolean stateOnResume = false;
        setContentView(loopFW);

    }

    public CoreFW() {

    }

    public AudioFW getAudioFW() {
        return audioFW;
    }

    public void onResume() {
        super.onResume();
        sceneFW.resume();
        loopFW.startGame();
    }

    public void onPause() {
        super.onPause();
        sceneFW.pause();
        loopFW.stopGame();
        stateOnPause = true;
        if (isFinishing()) {
            sceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW() {
        return graphicsFW;
    }
    public TouchListenerFW getTouchListenerFW(){
        return touchListenerFW;

    }

    public void setScene(SceneFW sceneFW) {
        if (sceneFW == null) {
            throw new IllegalArgumentException("Невозможно загрузить сцену: ");
        }
        this.sceneFW.pause();
        this.sceneFW.dispose();
        sceneFW.resume();
        sceneFW.update();
        this.sceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return sceneFW;
    }

    public SceneFW getStartScene() {
        return sceneFW;
    }

}
