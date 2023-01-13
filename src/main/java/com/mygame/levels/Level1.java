/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.levels;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.mygame.entity.AIPlayer;
import com.mygame.entity.Ball;
import com.mygame.entity.Player;
import com.mygame.ui.UIManager;
import settings.GameStateManager;
import settings.InputSettings;

/**
 *
 * @author Arash
 */
public class Level1 implements Level {

    private final AssetManager assetManager;
    private final InputManager inputManager;
    private InputSettings inputSettings;
    private final Node rootNode;
    private final AppSettings appSettings;

    private final Camera cam;
    private Player player;
    private Ball ball;
    private AIPlayer aiPlayer;
    private final Node actorsNode = new Node();

    private boolean isStarted;

    private UIManager uiManager;

    public Level1(AssetManager assetManager, InputManager inputManager, Node rootNode, Camera cam, AppSettings appSettings, UIManager uiManager) {
        this.assetManager = assetManager;
        this.inputManager = inputManager;
        this.rootNode = rootNode;
        this.cam = cam;
        this.appSettings = appSettings;
        this.uiManager = uiManager;
    }

    @Override
    public void load() {
        inputSettings = new InputSettings(this.inputManager);
        inputSettings.initInputs();

        rootNode.attachChild(actorsNode);

    }

    private void spawnPlayer() {
        this.player = new Player(assetManager, inputSettings, cam, appSettings);
        actorsNode.attachChild(player);

        Vector3f spawnPosition = cam.getWorldCoordinates(new Vector2f(50, appSettings.getHeight() / 2), 0.9f);
        player.spawn(spawnPosition);
    }

    private void spawnAIPlayer() {
        this.aiPlayer = new AIPlayer(assetManager, inputSettings, ball);
        actorsNode.attachChild(aiPlayer);

        Vector3f spawnPosition = cam.getWorldCoordinates(new Vector2f(appSettings.getWidth() - 40, appSettings.getHeight() / 2), 0.9f);
        aiPlayer.spawn(spawnPosition);
    }

    private void spawnBall() {
        this.ball = new Ball(this.assetManager, actorsNode, cam, appSettings);
        rootNode.attachChild(ball);

        ball.spawn(new Vector3f(0, 0, 0));
    }

    @Override
    public void update() {

        start();

        if (isStarted) {
            this.player.update();

            this.ball.update();

            aiPlayer.update();

            if (GameStateManager.getInstance().shouldUpdateUI) {
                uiManager.updateInGameScores();
            }
        }
    }

    public void start() {
        if (!isStarted && inputSettings.isPressedEnter()) {
            spawnPlayer();

            spawnBall();

            spawnAIPlayer();

            isStarted = true;

            uiManager.loadInGameScreen();
        }
    }

}
