package com.aliens.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cade on 4/6/17.
 */
public abstract class Missile {
    Texture missImg;
    //boolean exploded;
    int xLoc;
    int yLoc;

    int ogXLoc;
    int ogYLoc;

    int missWidth;

    Missile(){
        missImg = null;
        xLoc = 0;
        yLoc = 0;

        ogXLoc = xLoc;
        ogYLoc = yLoc;
    }


    Texture getImg(){
        return this.missImg;
    }
    int getXLoc(){ return this.xLoc; }
    int getYLoc(){ return this.yLoc; }
    void setXLoc(int newX){ this.xLoc = newX; }
    void setYLoc(int newY){ this.yLoc = newY; }

    void createCratr(){
        missImg = new Texture("Tower Defense (top-down)/PNG/Zombies!/towerDefense_tile021.png");
    }



    abstract void explode();     //may need to be void
    abstract void fly();            //abstract because big missile may fly slower than little



}
