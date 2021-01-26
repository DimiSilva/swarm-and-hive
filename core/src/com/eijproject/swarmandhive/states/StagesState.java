package com.eijproject.swarmandhive.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eijproject.swarmandhive.SwarmAndHive;
import com.eijproject.swarmandhive.entities.Card;
import com.eijproject.swarmandhive.entities.Enemy;
import com.eijproject.swarmandhive.entities.Stage;
import com.eijproject.swarmandhive.entities.Wave;
import com.eijproject.swarmandhive.enums.Font;
import com.eijproject.swarmandhive.lib.AreaInScreen;
import com.eijproject.swarmandhive.lib.TextUtils;
import com.eijproject.swarmandhive.services.CardService;
import com.eijproject.swarmandhive.services.Services;
import com.eijproject.swarmandhive.services.StageService;

import java.util.ArrayList;
import java.util.List;

public class StagesState extends State {

    private AreaInScreen backArea;
    private List<Stage> stages;

    public StagesState(GameStateManager gsm) {
        super(gsm);
        setupScreenAreas();
        stages = Services.getInstance().getStages();
        for(Stage stage: stages) {
            System.out.println(stage.getName());

            for(Wave wave: stage.getWaves()) {
                System.out.println(wave.getId());

                for(Enemy enemy: wave.getEnemies()) {
                    System.out.println(enemy.getName());
                }
            }
        }
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

        shapeRenderer.end();

        spriteBatch.begin();

        renderActionButtons(spriteBatch);

        spriteBatch.end();
    }

    private void renderBackground(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.valueOf("41444B"));
        shapeRenderer.rect(0, 0, SwarmAndHive.getWidth() / 2, SwarmAndHive.getHeight());
        shapeRenderer.setColor(Color.valueOf("52575D"));
        shapeRenderer.rect(SwarmAndHive.getWidth() / 2, 0, SwarmAndHive.getWidth() / 2, SwarmAndHive.getHeight());
    }

    private void renderActionButtons(SpriteBatch spriteBatch) {
        TextUtils.drawTextXYCentered(spriteBatch, "Voltar", Font.SMALL, (float) SwarmAndHive.getWidth() / 12, (float) SwarmAndHive.getHeight() - 50);
    }

    @Override
    public void dispose() {

    }
}
