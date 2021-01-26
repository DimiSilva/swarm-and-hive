package com.eijproject.swarmandhive.entities;

import com.badlogic.gdx.utils.JsonValue;

public class Difficulty {
    private Long id;
    private String name;
    private Float enemyAttackModifier;
    private Float enemyDefenseModifier;
    private Float enemySpeedModifier;
    private Float honeyModifier;
    private Float waveDelayModifier;
    private Float buyCardHoneyIncreaseModifier;

    public Difficulty(Long id, String name, Float enemyAttackModifier, Float enemyDefenseModifier, Float enemySpeedModifier, Float honeyModifier, Float waveDelayModifier, Float buyCardHoneyIncreaseModifier) {
        this.id = id;
        this.name = name;
        this.enemyAttackModifier = enemyAttackModifier;
        this.enemyDefenseModifier = enemyDefenseModifier;
        this.enemySpeedModifier = enemySpeedModifier;
        this.honeyModifier = honeyModifier;
        this.waveDelayModifier = waveDelayModifier;
        this.buyCardHoneyIncreaseModifier = buyCardHoneyIncreaseModifier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getEnemyAttackModifier() {
        return enemyAttackModifier;
    }

    public Float getEnemyDefenseModifier() {
        return enemyDefenseModifier;
    }

    public Float getEnemySpeedModifier() {
        return enemySpeedModifier;
    }

    public Float getHoneyModifier() {
        return honeyModifier;
    }

    public Float getWaveDelayModifier() {
        return waveDelayModifier;
    }

    public Float getBuyCardHoneyIncreaseModifier() {
        return buyCardHoneyIncreaseModifier;
    }

    public static Difficulty fromJson(JsonValue json) {
        return new Difficulty(
                json.getLong("id"),
                json.getString("name"),
                json.getFloat("enemyAttackModifier"),
                json.getFloat("enemyDefenseModifier"),
                json.getFloat("enemySpeedModifier"),
                json.getFloat("honeyModifier"),
                json.getFloat("waveDelayModifier"),
                json.getFloat("buyCardHoneyIncreaseModifier")
        );
    }
}
