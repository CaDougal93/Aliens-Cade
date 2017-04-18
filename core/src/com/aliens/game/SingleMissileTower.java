package com.aliens.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by cade on 4/5/17.
 */
public class SingleMissileTower extends Tower{

    SingleMissileTower(String imgLoc, int xLoc, int yLoc){
        this.tower = new Texture (imgLoc);
        this.xLoc = xLoc;
        this.yLoc = yLoc;

    }


    @Override
    void towerDestroyed() {

    }

    @Override
    void towerShoot() {

    }
}
