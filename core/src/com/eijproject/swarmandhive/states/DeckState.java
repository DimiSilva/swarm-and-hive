package com.eijproject.swarmandhive.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.entities.Card;
import com.eijproject.swarmandhive.enums.Font;
import com.eijproject.swarmandhive.lib.AreaInScreen;
import com.eijproject.swarmandhive.lib.TextUtils;
import com.eijproject.swarmandhive.services.CardService;

import java.util.ArrayList;

public class DeckState extends State {

    private CardService cardService;
    private AreaInScreen backArea;
    private ArrayList<Card> deckCards;

    public DeckState(GameStateManager gsm, CardService cardService) {
        super(gsm);
        setupScreenAreas();
        this.cardService = cardService;
        deckCards = this.cardService.getDeckCards();
    }

    private void setupScreenAreas() {
        backArea = TextUtils.getTextXYCenteredAreaInScreen("Voltar", Font.SMALL, (float) SwarmAndHive.getWidth() / 12, (float) SwarmAndHive.getHeight() - 50);
    }

    @Override
    public void handleInput() {
        setupOnTouchHandlers();
    }

    private void setupOnTouchHandlers() {
        if (Gdx.input.justTouched()) {
            backAreaTouchHandler();
        }
    }

    private void backAreaTouchHandler() {
        if (backArea.checkIfInArea(Gdx.input.getX(), Gdx.input.getY())) {
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
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        renderBackground(shapeRenderer);
        renderDeckCards(spriteBatch, shapeRenderer);

        shapeRenderer.end();

        spriteBatch.begin();

        renderIdentifiers(spriteBatch);
        renderActionButtons(spriteBatch);

        spriteBatch.end();
    }


    private void renderBackground(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.valueOf("41444B"));
        shapeRenderer.rect(0, 0, SwarmAndHive.getWidth() / 2, SwarmAndHive.getHeight());
        shapeRenderer.setColor(Color.valueOf("52575D"));
        shapeRenderer.rect(SwarmAndHive.getWidth() / 2, 0, SwarmAndHive.getWidth() / 2, SwarmAndHive.getHeight());
    }


    private void renderIdentifiers(SpriteBatch spriteBatch) {
        TextUtils.drawTextXYCentered(spriteBatch, "Deck", Font.NORMAL, (float) SwarmAndHive.getWidth() / 4, (float) SwarmAndHive.getHeight() - 100);
        TextUtils.drawTextXYCentered(spriteBatch, "Cartas", Font.NORMAL, (float) SwarmAndHive.getWidth() / 2 + SwarmAndHive.getWidth() / 4,(float) SwarmAndHive.getHeight() - 100);
    }

    private void renderActionButtons(SpriteBatch spriteBatch) {
        TextUtils.drawTextXYCentered(spriteBatch, "Voltar", Font.SMALL, (float) SwarmAndHive.getWidth() / 12, (float) SwarmAndHive.getHeight() - 50);
    }

    private void renderDeckCards(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        for(int i = 0; i < deckCards.size(); i++) {
            shapeRenderer.setColor(Color.valueOf("FDDB3A"));
            shapeRenderer.rect(100 * (i + 1), (float) SwarmAndHive.getHeight() - 250, 50, 50);
        }
    }

    @Override
    public void dispose() {

    }
}
