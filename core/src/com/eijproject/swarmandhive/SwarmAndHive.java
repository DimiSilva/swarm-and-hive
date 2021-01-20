package com.eijproject.swarmandhive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eijproject.swarmandhive.states.GameStateManager;
import com.eijproject.swarmandhive.states.LoginState;

public class SwarmAndHive extends ApplicationAdapter {

	private static int width;
	private static int height;

	private GameStateManager gsm;
	public SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		gsm = new GameStateManager();

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new LoginState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(spriteBatch, shapeRenderer);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
}
