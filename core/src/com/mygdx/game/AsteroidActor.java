package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by rohilsheth on 6/10/18.
 */



public class AsteroidActor extends Actor {
    TextureAtlas atlas, clickedatlas;
    Sprite sprite, clickedsprite;
    Animation<TextureRegion> animation,clickedanimation;
    TextureRegion region,clickedregion;
    float time=0;
    float explodelength;
    float xpos,ypos;
    boolean falling;
    boolean hit;
    boolean clicked;
    boolean start;
    Rectangle rectangle;
    MoveToAction move;

    public AsteroidActor(){
        xpos = (float)(Math.random()*800);
        start=false;
        falling=false;
        atlas=new TextureAtlas(Gdx.files.internal("ast-packed/pack.atlas"));
        clickedatlas = new TextureAtlas(Gdx.files.internal("explodedast-packed/pack.atlas"));
        animation =  new Animation(1/8f,atlas.getRegions());
        clickedanimation = new Animation(1/12f,clickedatlas.getRegions());
        region = animation.getKeyFrame(0,true);
        clickedregion = clickedanimation.getKeyFrame(0,true);
        sprite = new Sprite(region);
        setBounds(xpos,getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setX(xpos);
        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clicked = true;
                MyGdxGame.score++;
                return true;
            }
        });
        rectangle = new Rectangle(getX(),getY(),getWidth(),getHeight());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        fall();

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!clicked) {
            time += Gdx.graphics.getDeltaTime();
            batch.draw(animation.getKeyFrame(time, true), sprite.getX(), sprite.getY());
        }
        else if(clicked){
            explodelength +=Gdx.graphics.getDeltaTime();
            batch.draw(clickedanimation.getKeyFrame(explodelength, true), sprite.getX(), sprite.getY());
            if(clickedanimation.isAnimationFinished(explodelength)){
                addAction(Actions.removeActor());
            }
        }



    }
    public Rectangle getColliderActor(){
        return this.rectangle;
    }

    public void fall(){
        if(!falling) {
            SequenceAction action = new SequenceAction();
            move = new MoveToAction();
            move.setPosition(xpos,2400f);
            move.setDuration(5f);
            action.addAction(move);
            action.addAction(Actions.removeActor());
            addAction(action);
            falling=true;
        }
        sprite.setPosition(getX(),getY());
    }
    public Rectangle getRectangle(){
        return sprite.getBoundingRectangle();
    }


}
