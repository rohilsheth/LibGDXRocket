package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends ApplicationAdapter {
	BitmapFont font;
	SpriteBatch batch;
	Stage stage;
	public static int score;


	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);


		RocketActor rocket = new RocketActor();
		stage.addActor(rocket);
		/*
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("sonicpics-packed/pack.atlas"));
		stillAnimation = new Animation(1 / 10f, textureAtlas.getRegions());
		*/
		AsteroidActor asteroid = new AsteroidActor();
		stage.addActor(asteroid);
		AsteroidActor asteroid2 = new AsteroidActor();
		stage.addActor(asteroid2);
		AsteroidActor asteroid3 = new AsteroidActor();
		stage.addActor(asteroid3);
		font = new BitmapFont();
		batch = new SpriteBatch();
		font.setColor(Color.RED);
		font.getData().setScale(4f);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*
		batch.begin();

		timeForStill += Gdx.graphics.getDeltaTime();
		//Keeps track of the amount of total time that has passed
		batch.draw(stillAnimation.getKeyFrame(timeForStill, true), 0, 0);
		//this is automatically change the frames based on the time that has passed

		batch.end();
		*/
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		if(stage.getActors().size < 4) {
			AsteroidActor asteroid = new AsteroidActor();
			stage.addActor(asteroid);
		}
		batch.begin();
		font.draw(batch,"Score: "+score,50,50);
		batch.end();


	}

	@Override
	public void dispose() {
		//batch.dispose();
		//img.dispose();
		stage.dispose();
	}

}

