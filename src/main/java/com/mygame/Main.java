package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import com.mygame.levels.Level;
import com.mygame.levels.Level1;
import com.mygame.ui.UIManager;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 */
public class Main extends SimpleApplication {

    private Level level;
    private UIManager uiManager;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);

        initUI();

        loadLevel();
    }

    @Override
    public void simpleUpdate(float tpf) {
        if (level != null) {
            level.update();
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void loadLevel() {
        level = new Level1(assetManager, inputManager, rootNode, cam, settings, uiManager);
        level.load();
    }

    private void initUI() {
        uiManager = new UIManager(assetManager, inputManager, audioRenderer, guiViewPort);
        uiManager.loadStartScreen();
    }
}
