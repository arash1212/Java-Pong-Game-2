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
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author Arash
 */
public class UIStartScreen {

    private Nifty nifty;
    private CustomUIController uiController;
    private Screen screen;

    public UIStartScreen(Nifty nifty, CustomUIController uiController) {
        this.nifty = nifty;
        this.uiController = uiController;
    }

    public Screen getScreen() {
        return this.screen = new ScreenBuilder("start") {
            {
                controller(uiController);

                layer(new LayerBuilder("layer") {
                    {
                        childLayoutCenter();

                        panel(new PanelBuilder("panel") {
                            {
                                childLayoutCenter();

                                control(new LabelBuilder("label", "Press Enter To Start !") {
                                    {
                                        width("80%");
                                        height("50%");
                                        color("#FFFFFF");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.build(nifty);
    }
}
