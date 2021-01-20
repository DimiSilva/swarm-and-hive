package com.eijproject.swarmandhive.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.entities.AreaInScreen;
import com.eijproject.swarmandhive.utils.TextUtils;

public class MainMenuState extends State {

    private AreaInScreen playArea;
    private AreaInScreen deckArea;
    private AreaInScreen exitArea;

    public MainMenuState(GameStateManager gsm) {
        super(gsm);
        setupScreenAreas();
    }

    private void setupScreenAreas() {
        playArea = TextUtils.getTextCenterXAreaInScreen("Jogar", 48, 1, (float) 400);
        deckArea = TextUtils.getTextCenterXAreaInScreen("Deck", 48, 1, (float) 300);
        exitArea = TextUtils.getTextCenterXAreaInScreen("Sair", 48, 1, (float) 200);
    }


    @Override
    public void handleInput() {
        setupOnTouchHandlers();
    }

    private void setupOnTouchHandlers() {
        if (Gdx.input.justTouched()) {
            playAreaTouchHandler();
            deckAreaTouchHandler();
            exitAreaTouchHandler();
        }
    }

    private void playAreaTouchHandler() {
        if (playArea.checkIfInArea(Gdx.input.getX(), Gdx.input.getY()))
            System.out.println("Jogar");
    }

    private void deckAreaTouchHandler() {
        if (deckArea.checkIfInArea(Gdx.input.getX(), Gdx.input.getY()))
            System.out.println("Deck");
    }

    private void exitAreaTouchHandler() {
        if (exitArea.checkIfInArea(Gdx.input.getX(), Gdx.input.getY()))
            System.out.println("Sair");
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
        TextUtils.drawTextCenterX(spriteBatch, "Swarm and Hive", 96, 1, Color.valueOf("fddb3a"), Color.valueOf("52575d"), (float)SwarmAndHive.getHeight() - 150);
    }

    private void renderActionButtons(SpriteBatch spriteBatch) {
        TextUtils.drawTextCenterX(spriteBatch, "Jogar", 48, 1, Color.valueOf("fddb3a"), Color.valueOf("52575d"), (float) 400);
        TextUtils.drawTextCenterX(spriteBatch, "Deck", 48, 1, Color.valueOf("fddb3a"), Color.valueOf("52575d"), (float) 300);
        TextUtils.drawTextCenterX(spriteBatch, "Sair", 48, 1, Color.valueOf("fddb3a"), Color.valueOf("52575d"), (float) 200);
    }

    @Override
    public void dispose() {

    }
}
