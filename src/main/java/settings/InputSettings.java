/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package settings;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

/**
 *
 * @author Arash
 */
public class InputSettings {

    private final InputManager inputManager;

    private boolean up, down, enter;

    public InputSettings(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void initInputs() {
        this.inputManager.addMapping("up", new KeyTrigger(KeyInput.KEY_W));
        this.inputManager.addMapping("down", new KeyTrigger(KeyInput.KEY_S));
        this.inputManager.addMapping("enter", new KeyTrigger(KeyInput.KEY_RETURN));

        this.inputManager.addListener(actionListener, "up", "down", "enter");
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String string, boolean bln, float f) {
            if (string.equals("up")) {
                up = bln;
            }
            if (string.equals("down")) {
                down = bln;
            }
            if (string.equals("enter")) {
                enter = bln;
            }
        }
    };

    public boolean isPressedUp() {
        return up;
    }

    public boolean isPressedDown() {
        return down;
    }

    public boolean isPressedEnter() {
        return enter;
    }
}
