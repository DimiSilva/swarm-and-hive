package com.eijproject.swarmandhive.enums;

public enum Font {
    TITLE(1, "title"),
    NORMAL(2, "normal"),
    SMALL(3, "small");

    private int id;
    private String description;

    Font(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Font toEnum(Integer id) {
        if(id == null) return null;
        for(Font x : Font.values()) {
            if(id.equals(x.getId())) return x;
        }

        throw new IllegalArgumentException("invalid id: " + id);
    }
}
