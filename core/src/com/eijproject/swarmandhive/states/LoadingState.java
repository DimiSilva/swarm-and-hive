package com.eijproject.swarmandhive.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.enums.Font;
import com.eijproject.swarmandhive.lib.AreaInScreen;
import com.eijproject.swarmandhive.lib.TextUtils;
import com.eijproject.swarmandhive.services.CardService;

public class LoadingState extends State {


    public LoadingState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        renderBackground(shapeRenderer);

        spriteBatch.begin();
        renderLoadingMessage(spriteBatch);
        spriteBatch.end();
    }


    private void renderBackground(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.2f);
        shapeRenderer.rect(0, 0, SwarmAndHive.getWidth(), SwarmAndHive.getHeight());
        shapeRenderer.end();
    }

    private void renderLoadingMessage(SpriteBatch spriteBatch) {
        TextUtils.drawTextCenter(spriteBatch, "Carregando", Font.TITLE);
    }

    @Override
    public void dispose() {

    }
}
