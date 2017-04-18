package com.aliens.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/6/17.
 */
public abstract class Tower {
    Texture tower;
    int xLoc;
    int yLoc;


    public Tower(){
        this.tower = null;
        this.xLoc = 0;
        this.yLoc = 0;
    }


    public Tower(String imgLoc, int xLoc, int yLoc){
        this.tower = new Texture(imgLoc);
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    Texture getImg(){
        return this.tower;
    }

    int getXLoc(){
        return this.xLoc;
    }

    int getYLoc(){
        return this.yLoc;
    }

    void setxLoc(int newXLoc){
        this.xLoc = newXLoc;
    }

    void setyLoc(int newYLoc){
        this.yLoc = newYLoc;
    }

    abstract void towerShoot();
    abstract void towerDestroyed();








}
