package com.eijproject.swarmandhive.entities;

import com.badlogic.gdx.utils.JsonValue;

public class Enemy {
    private Long id;
    private String name;
    private Float attack;
    private Float defense;
    private Float speed;
    private Float honeyReward;

    public Enemy(Long id, String name, Float attack, Float defense, Float speed, Float honeyReward) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.honeyReward = honeyReward;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Float getHoneyReward() {
        return honeyReward;
    }

    public static Enemy fromJson(JsonValue json) {
//        return new Enemy(
//                json.getLong("id"),
//                json.getString("name"),
//                json.getString("description"),
//                json.getString("type"),
//                !json.get("attack").isNull() ? json.getFloat("attack"): null ,
//                !json.get("defense").isNull() ? json.getFloat("defense") : null,
//                !json.get("speed").isNull() ? json.getFloat("speed") : null,
//                !json.get("attackModifier").isNull() ? json.getFloat("attackModifier"): null ,
//                !json.get("defenseModifier").isNull() ? json.getFloat("defenseModifier") : null,
//                !json.get("speedModifier").isNull() ? json.getFloat("speedModifier") : null
//        );
    }
}
