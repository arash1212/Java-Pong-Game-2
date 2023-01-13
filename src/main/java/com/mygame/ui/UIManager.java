/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.ui;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.screen.Screen;
import settings.GameStateManager;

/**
 *
 * @author Arash
 */
public class UIManager {

    private AssetManager assetManager;
    private InputManager inputManager;
    private AudioRenderer audiorenderer;
    private ViewPort guiViewPort;

    private Nifty nifty;
    private CustomUIController customUIController;

    private UIStartScreen uiStartScreen;
    private UIInGameScreen uiInGameScreen;

    private Screen startScreen;
    private Screen inGameScreen;

    public UIManager(AssetManager assetManager, InputManager inputManager, AudioRenderer audiorenderer, ViewPort guiViewPort) {
        this.assetManager = assetManager;
        this.inputManager = inputManager;
        this.audiorenderer = audiorenderer;
        this.guiViewPort = guiViewPort;
        this.init();
    }

    public final void init() {
        NiftyJmeDisplay jmeDisplay = new NiftyJmeDisplay(this.assetManager, this.inputManager, this.audiorenderer, this.guiViewPort);
        this.nifty = jmeDisplay.getNifty();
        this.guiViewPort.addProcessor(jmeDisplay);

        nifty.loadControlFile("nifty-default-controls.xml");
        nifty.loadStyleFile("nifty-default-styles.xml");

        this.customUIController = new CustomUIController();

        this.uiStartScreen = new UIStartScreen(nifty, customUIController);
        this.uiInGameScreen = new UIInGameScreen(nifty, customUIController);

        this.startScreen = uiStartScreen.getScreen();
        this.inGameScreen = uiInGameScreen.getScreen();
    }

    public void loadStartScreen() {
        nifty.gotoScreen(startScreen.getScreenId());
    }

    public void loadInGameScreen() {
        nifty.gotoScreen(inGameScreen.getScreenId());
    }

    public void updateInGameScores() {
        nifty.getCurrentScreen().findNiftyControl("player1Score", Label.class).setText("player 1 Score : " + GameStateManager.getInstance().getPlayer1Score());
        nifty.getCurrentScreen().findNiftyControl("player2Score", Label.class).setText("player 2 Score : " + GameStateManager.getInstance().getPlayer2Score());
    }

}
