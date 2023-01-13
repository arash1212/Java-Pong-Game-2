/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.entity;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import settings.InputSettings;

/**
 *
 * @author Arash
 */
public class Player extends Node implements Actor {

    private final AssetManager assetManager;
    private final InputSettings inputSettings;

    private final Camera cam;

    private final float speed = 0.3f;

    private final AppSettings appSettings;

    public Player(AssetManager assetManager, InputSettings inputSettings, Camera cam, AppSettings appSettings) {
        this.assetManager = assetManager;
        this.inputSettings = inputSettings;
        this.cam = cam;
        this.appSettings = appSettings;
    }

    private void init() {
        //Geometry
        Box box = new Box(0.2f, 1, 0.2f);
        Geometry geom = new Geometry("plyer", box);

        //Material
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.White);
        geom.setMaterial(material);

        this.attachChild(geom);
    }

    @Override
    public void spawn(Vector3f spawnPoint) {
        this.init();

        this.setLocalTranslation(spawnPoint);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        this.updateMovement();
    }

    private void updateMovement() {
        if (this.inputSettings.isPressedUp() && this.getScreenPosition().y < appSettings.getHeight() - 30) {
            Vector3f currentTranslation = this.getLocalTranslation();
            this.setLocalTranslation(currentTranslation.x, currentTranslation.y + speed, currentTranslation.z);
        } else if (this.inputSettings.isPressedDown() && this.getScreenPosition().y > appSettings.getMinHeight() + 30) {
            Vector3f currentTranslation = this.getLocalTranslation();
            this.setLocalTranslation(currentTranslation.x, currentTranslation.y - speed, currentTranslation.z);
        }
    }

    private Vector3f getScreenPosition() {
        return this.cam.getScreenCoordinates(this.getLocalTranslation());
    }

}
