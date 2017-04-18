package com.aliens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class FatZombie extends Zombie {
    private final int DEAD_FRAME_NUM = 7;
    private final int ATTACK_FRAME_NUM = 7;
    private int currentDeadFrame = 0;
    private int currentAttackFrame = 0;
    private float lastElapsedTime = 0f;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean dead = false;
    private boolean attack = false;
    private Sprite[] deadSprites;
    private Sprite[] attackSprites;
    public FatZombie(){
        //Default time duration of fat zombie is 1/7f
        //Default zombie movement is to walk to the right
        setSprites("die", DEAD_FRAME_NUM,"android/assets/fatzombie-data/fatzom-texture-die.atlas");
        setSprites("right-attack", ATTACK_FRAME_NUM,"android/assets/fatzombie-data/fatzom-texture-attack-right.atlas");
        setDuration(1/7f);
        moveRight();
    }
    public FatZombie(float duration){
        setSprites("die", DEAD_FRAME_NUM,"android/assets/fatzombie-data/fatzom-texture-die.atlas");
        setSprites("right-attack", ATTACK_FRAME_NUM,"android/assets/fatzombie-data/fatzom-texture-attack-right.atlas");
        setDuration(duration);
        moveRight();
    }
    private void setSprites(String action, int maxFrameNum, String filePath){
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal(filePath));
        Sprite[] copySprite = null;
        if(action.equals("die")) {
            deadSprites = new Sprite[maxFrameNum];
            copySprite = deadSprites;
        }else if(action.equals("right-attack")) {
            attackSprites = new Sprite[maxFrameNum];
            copySprite = attackSprites;
        }
        for(int i = 0; i < maxFrameNum; i++)
            copySprite[i] = new Sprite(textureAtlas.findRegion("00" + Integer.toString(i + 1)));
    }
    public void draw(SpriteBatch batch, float elapsedTime){
        if(!dead && !attack)
            batch.draw(getAnimation().getKeyFrame(elapsedTime, true), getX(), getY());
        //draw the zombie die
        else if(dead){
            //the very first time draw() is called to draw zombie die
            if(lastElapsedTime == 0f) {
                lastElapsedTime = elapsedTime;
            }
            else if(elapsedTime - lastElapsedTime > getDuration()){
                if(currentDeadFrame != DEAD_FRAME_NUM - 1)
                    currentDeadFrame++;
                lastElapsedTime = elapsedTime;
            }
            deadSprites[currentDeadFrame].setPosition(getX(), getY());
            deadSprites[currentDeadFrame].draw(batch);
        }
        //draw the zombie attack right
        else if(attack){
            if(lastElapsedTime == 0f) {
                lastElapsedTime = elapsedTime;
            }
            else if(elapsedTime - lastElapsedTime > getDuration()){
                if(currentAttackFrame != ATTACK_FRAME_NUM - 1)
                    currentAttackFrame++;
                lastElapsedTime = elapsedTime;
            }
            attackSprites[currentAttackFrame].setPosition(getX(), getY());
            attackSprites[currentAttackFrame].draw(batch);
        }
    }
    public void appear(){
        setAtlasAnimation(new TextureAtlas(Gdx.files.internal("android/assets/fatzombie-data/fatzom-texture-appear.atlas")));
    }
    public void stop(){}
    public void moveRight() {
        if (!movingRight) {
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("android/assets/fatzombie-data/fatzom-texture-walk-right.atlas")));
            movingRight = true;
            movingDown = movingLeft = movingUp = false;
            resetActions();
        }
    }
    public void moveLeft(){
        if (!movingLeft) {
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("android/assets/fatzombie-data/fatzom-texture-walk-left.atlas")));
            movingLeft = true;
            movingDown = movingRight = movingUp = false;
            resetActions();
        }
    }
    public void moveUp(){
        if(!movingUp){
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("android/assets/fatzombie-data/fatzom-texture-walk-up.atlas")));
            movingUp = true;
            movingDown = movingRight = movingLeft = false;
            resetActions();
        }
    }
    public void moveDown() {
        if (!movingDown) {
            setAtlasAnimation(new TextureAtlas(Gdx.files.internal("android/assets/fatzombie-data/fatzom-texture-walk-down.atlas")));
            movingDown = true;
            movingUp = movingLeft = movingRight = false;
            resetActions();
        }
    }
    public void die(){
        if(attack)
            resetActions();
        dead = true;
        movingUp = movingLeft = movingRight = movingDown = false;
    }
    public void rightAttack(){
        if(dead)
            resetActions();
        attack = true;
        movingUp = movingLeft = movingRight = movingDown = false;
    }
    private void resetActions(){
        dead = false;
        attack = false;
        currentDeadFrame = 0;
        currentAttackFrame = 0;
        lastElapsedTime = 0f;
    }
}
