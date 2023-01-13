/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.ui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author Arash
 */
public class UIInGameScreen {

    private Nifty nifty;
    private CustomUIController uiController;
    private Screen screen;

    public UIInGameScreen(Nifty nifty, CustomUIController uiController) {
        this.nifty = nifty;
        this.uiController = uiController;
    }

    public Screen getScreen() {
        return this.screen = new ScreenBuilder("inGame") {
            {
                controller(uiController);

                layer(new LayerBuilder("layer") {
                    {
                        childLayoutHorizontal();

                        panel(new PanelBuilder("panel") {
                            {
                                childLayoutHorizontal();
//                                image(new ImageBuilder() {
//                                    {
//                                        width("99%");
//                                        height("99%");
//                                        filename("Textures/background/pong-background-image2.png");
//                                    }
//                                });

                                control(new LabelBuilder("player1Score", "Player 1 Score : 0") {
                                    {
                                        marginTop("20%");
                                        width("20%");
                                        // height("10%");
                                        color("#FFFFFF");
                                    }
                                });

                                control(new LabelBuilder("player2Score", "Player 2 Score : 0") {
                                    {
                                        marginTop("20%");
                                        width("20%");
                                        //  height("50%");
                                        color("#FFFFFF");
                                        marginLeft("60%");
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
