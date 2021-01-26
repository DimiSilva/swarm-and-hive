package com.eijproject.swarmandhive.entities;

import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Stage {
    private Long id;
    private Integer position;
    private String name;
    private Difficulty difficulty;
    private Card rewardCard;
    private List<Wave> waves;

    public Stage(Long id, Integer position, String name, Difficulty difficulty, Card rewardCard) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.difficulty = difficulty;
        this.rewardCard = rewardCard;

        waves = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Card getRewardCard() {
        return rewardCard;
    }

    public List<Wave> getWaves() {
        return waves;
    }

    public static Stage fromJson(JsonValue json) {

        Stage stage = new Stage(
                json.getLong("id"),
                json.getInt("position"),
                json.getString("name"),
                Difficulty.fromJson(json.get("difficulty")),
                !json.get("rewardCard").isNull() ? Card.fromJson(json.get("rewardCard")) : null
        );

        for (JsonValue jsonWave : json.get("waves")) {
            stage.waves.add(Wave.fromJson(jsonWave));
        }

        return stage;
    }
}
