/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.entity;

import com.jme3.math.Vector3f;

/**
 *
 * @author Arash
 */
public interface Actor {

    void start();
    
    void spawn(Vector3f spawnPoint);

    void update();
}
