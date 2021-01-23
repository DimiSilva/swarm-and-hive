package com.eijproject.swarmandhive.entities;

import com.badlogic.gdx.utils.JsonValue;
import com.eijproject.swarmandhive.enums.CardTypes;

public class Card {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Float attack;
    private Float defense;
    private Float speed;
    private Float attackModifier;
    private Float defenseModifier;
    private Float speedModifier;

    public Card(Long id, String name, String description, String type, Float attack, Float defense, Float speed, Float attackModifier, Float defenseModifier, Float speedModifier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
        this.speedModifier = speedModifier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Float getAttack() {
        return attack;
    }

    public Float getDefense() {
        return defense;
    }

    public Float getSpeed() {
        return speed;
    }

    public Float getAttackModifier() {
        return attackModifier;
    }

    public Float getDefenseModifier() {
        return defenseModifier;
    }

    public Float getSpeedModifier() {
        return speedModifier;
    }

    public static Card fromJson(JsonValue json) {
        return new Card(
                json.getLong("id"),
                json.getString("name"),
                json.getString("description"),
                json.getString("type"),
                !json.get("attack").isNull() ? json.getFloat("attack"): null ,
                !json.get("defense").isNull() ? json.getFloat("defense") : null,
                !json.get("speed").isNull() ? json.getFloat("speed") : null,
                !json.get("attackModifier").isNull() ? json.getFloat("attackModifier"): null ,
                !json.get("defenseModifier").isNull() ? json.getFloat("defenseModifier") : null,
                !json.get("speedModifier").isNull() ? json.getFloat("speedModifier") : null
        );
    }
}
