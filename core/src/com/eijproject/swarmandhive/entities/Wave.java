package com.eijproject.swarmandhive.entities;

import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private Long id;
    private Integer position;
    private List<Enemy> enemies;

    public Wave(Long id, Integer position) {
        this.id = id;
        this.position = position;
        this.enemies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public static Wave fromJson(JsonValue json) {
        Wave wave = new Wave(
                json.getLong("id"),
                json.getInt("position")
        );

        for(JsonValue jsonEnemy: json.get("enemies")) {
           wave.enemies.add(Enemy.fromJson(jsonEnemy));
        }

        return wave;
    }
}
