package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;

/**
 * Created by rohilsheth on 6/10/18.
 */

public class RocketActor extends Actor {
    TextureAtlas atlas,hitatlas;
    Sprite sprite;
    Animation<TextureRegion> animation,hitanimation;
    TextureRegion region,hitregion;
    float time=0;
    Boolean hit=false;
    float hitlength=0;
    Rectangle rectangle;

    public RocketActor(){
        atlas=new TextureAtlas(Gdx.files.internal("rocket-packed/pack.atlas"));
        hitatlas=new TextureAtlas(Gdx.files.internal("hitrocket-packed/pack.atlas"));
        animation =  new Animation(1/3f,atlas.getRegions());
        hitanimation =  new Animation(1/2f,hitatlas.getRegions());
        region = animation.getKeyFrame(0,true);
        hitregion = hitanimation.getKeyFrame(0,true);
        sprite = new Sprite(region);
        sprite.setPosition(400f,1200);
        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        rectangle = new Rectangle(getX(),getY(),getWidth(),getHeight());
    }
    public Rectangle getRectangle(){
        return sprite.getBoundingRectangle();
    }
    public Rectangle getColliderActor(){
        return this.rectangle;
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println(getStage());
        try{
            if (getStage() != null) {
            System.out.println(getStage().getActors());
            Array<Actor> actors = getStage().getActors();
            Array<Actor> asteroidactor = new Array<Actor>();
            asteroidactor.add(actors.get(1));
            asteroidactor.add(actors.get(2));
            asteroidactor.add(actors.get(3));
            for (Actor a : asteroidactor) {
                if ( sprite.getBoundingRectangle().overlaps(((AsteroidActor)a).getRectangle()) ){
                    hit=true;
                }
            }
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!hit) {
            time += Gdx.graphics.getDeltaTime();
            batch.draw(animation.getKeyFrame(time, true), sprite.getX(), sprite.getY());
        }
        else if(hit){
            while (hit) {
                hitlength += Gdx.graphics.getDeltaTime();
                batch.draw(hitanimation.getKeyFrame(hitlength, true), sprite.getX(), sprite.getY());
                hit=false;
            }
        }
    }



}
