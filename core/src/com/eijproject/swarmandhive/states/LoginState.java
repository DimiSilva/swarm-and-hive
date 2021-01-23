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

public class LoginState extends State {
    private AreaInScreen continueArea;
    private CardService cardService;

    public LoginState(GameStateManager gsm, CardService cardService) {
        super(gsm);
        setupScreenAreas();

        this.cardService = cardService;
    }

    private void setupScreenAreas() {
        continueArea = TextUtils.getTextCenterXAreaInScreen("Continuar", Font.NORMAL, (float)300);
    }

    @Override
    public void handleInput() {
        setupOnTouchHandlers();
    }

    private void setupOnTouchHandlers() {
        if(Gdx.input.justTouched()) {
            continueAreaTouchHandler();
        }
    }

    private void continueAreaTouchHandler() {
        if(continueArea.checkIfInArea(Gdx.input.getX(), Gdx.input.getY())) {
            gsm.set(new MainMenuState(gsm, cardService));
            dispose();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        renderBackground(shapeRenderer);

        spriteBatch.begin();

        renderTitle(spriteBatch);
        renderActionButtons(spriteBatch);

        spriteBatch.end();
    }

    private void renderBackground(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.valueOf("41444b"));
        shapeRenderer.rect(0, 0, SwarmAndHive.getWidth(), SwarmAndHive.getHeight());
        shapeRenderer.end();
    }

    private void renderTitle(SpriteBatch spriteBatch) {
        TextUtils.drawTextCenterX(spriteBatch, "Swarm and Hive", Font.TITLE, (float)SwarmAndHive.getHeight() - 150);
    }

    private void renderActionButtons(SpriteBatch spriteBatch) {
        TextUtils.drawTextCenterX(spriteBatch, "Continuar", Font.NORMAL, (float)300);
    }

    @Override
    public void dispose() {

    }
}
