/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.entity;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.bounding.BoundingSphere;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import java.util.Random;
import settings.GameStateManager;

/**
 *
 * @author Arash
 */
public class Ball extends Node implements Actor {

    private AssetManager assetManager;
    private Node actorsNode;
    private Geometry geom;
    private BoundingSphere bSphere;
    private AppSettings appSettings;

    private Camera cam;

    private float movementX, movementY;
    private float speed = 0.06f;

    private float top;
    private float bottom;

    private Actor lastHitActor;
    private Actor currentHitActor;

    //Sounds
    private AudioNode hitActorSound;
    private AudioNode hitBorderSound;
    private AudioNode scoreSound;

    public Ball(AssetManager assteManager, Node actorsNode, Camera cam, AppSettings appSettings) {
        this.assetManager = assteManager;
        this.actorsNode = actorsNode;
        this.cam = cam;
        this.appSettings = appSettings;
    }

    private void init() {
        Sphere sphere = new Sphere(30, 30, 0.3f);
        this.bSphere = new BoundingSphere(0.3f, Vector3f.ZERO);
        this.geom = new Geometry("ball", sphere);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.White);
        geom.setMaterial(mat);

        this.attachChild(geom);

        this.movementX = -1;
        this.movementY = 1;

        top = appSettings.getHeight() - 10;
        bottom = appSettings.getMinHeight() + 10;

        //sounds
        hitActorSound = new AudioNode(assetManager, "Sounds/ball_hit_actor_sound.wav", DataType.Buffer);
        hitActorSound.setPositional(false);

        hitBorderSound = new AudioNode(assetManager, "Sounds/ball_hit_border_sound.wav", DataType.Buffer);
        hitBorderSound.setPositional(false);

        scoreSound = new AudioNode(assetManager, "Sounds/ball_score_sound.wav", DataType.Buffer);
        scoreSound.setPositional(false);
    }

    @Override
    public void start() {
    }

    @Override
    public void spawn(Vector3f spawnPoint) {
        init();

        this.setLocalTranslation(spawnPoint);
    }

    @Override
    public void update() {
        updateMovement();

        checkCollision();

        checkBorders();

    }

    private void updateMovement() {
        Vector3f currentTranslation = this.getLocalTranslation();
        this.setLocalTranslation(currentTranslation.x + movementX * speed, currentTranslation.y + movementY * speed, 0);
    }

    private void checkCollision() {
        bSphere.setCenter(this.getLocalTranslation());

        if (!this.getChildren().isEmpty()) {
            if (actorsNode != null && !actorsNode.getChildren().isEmpty()) {
                CollisionResults results = new CollisionResults();
                this.bSphere.collideWith(actorsNode, results);

                if (results.size() > 0) {
                    this.currentHitActor = (Actor) results.getCollision(0).getGeometry().getParent();
                    if (currentHitActor != lastHitActor) {
                        movementX *= -1;
//                    movementY *= -1;
                        this.speed *= 1.1f;
                        hitActorSound.playInstance();
                        this.lastHitActor = this.currentHitActor;
                    }
                }
            }
        }
    }

    private void checkBorders() {

        //top / bottom borders
        if (this.getScreenPosition().y > top) {
            movementY *= -1;
            hitBorderSound.playInstance();
        } else if (this.getScreenPosition().y < bottom) {
            movementY *= -1;
            hitBorderSound.playInstance();
        }

        //Score check
        if (this.getScreenPosition().x < appSettings.getMinWidth()) {
            this.setLocalTranslation(Vector3f.ZERO);
            speed = 0.06f;
            GameStateManager.getInstance().addPlayer2Score();
            scoreSound.playInstance();
        } else if (this.getScreenPosition().x > appSettings.getWidth()) {
            this.setLocalTranslation(Vector3f.ZERO);
            speed = 0.06f;
            GameStateManager.getInstance().addPlayer1Score();
            scoreSound.playInstance();
        }
    }

    private Vector3f getScreenPosition() {
        return this.cam.getScreenCoordinates(this.getLocalTranslation());
    }

    private int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
}
