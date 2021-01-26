package com.eijproject.swarmandhive.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.JsonReader;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.entities.Card;
import com.eijproject.swarmandhive.enums.Font;
import com.eijproject.swarmandhive.lib.AreaInScreen;
import com.eijproject.swarmandhive.lib.TextUtils;
import com.eijproject.swarmandhive.services.CardService;
import com.eijproject.swarmandhive.services.Services;

import java.util.ArrayList;
import java.util.List;

public class DeckState extends State {

    private AreaInScreen backArea;
    private List<Card> deckCards;

    public DeckState(GameStateManager gsm) {
        super(gsm);
        setupScreenAreas();
        deckCards = Services.getInstance().getDeckCards();
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
            gsm.set(new MainMenuState(gsm));
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
            int maxSize = (SwarmAndHive.getWidth() / 2) / 250;

            if((250 * i) + 250 < (SwarmAndHive.getWidth() / 2)){
                shapeRenderer.rect((250 * i) + 50, (float) SwarmAndHive.getHeight() - 500, 200, 200);

            }else{
                shapeRenderer.rect((250 * (i % maxSize)) + 50, (float) SwarmAndHive.getHeight() - 740, 200, 200);
            }
            System.out.println(deckCards.get(i).getName());
        }
    }

    @Override
    public void dispose() {

    }
}
