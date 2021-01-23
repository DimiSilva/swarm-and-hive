package com.eijproject.swarmandhive.enums;

public enum CardTypes {
    ATTACK_TOWER("attack_tower"),
    BUFF_TOWER("buff_tower"),
    UNIT("unit"),
    MAGIC("magic");

    private String value;

    private CardTypes(String value) {
        this.value = value;
    }

    public String getValue() { return value; }

    public static CardTypes valueOf_(String value) {
        for (CardTypes type : CardTypes.values()) {
            if(type.getValue() == value) return type;
        }
        throw new IllegalArgumentException("Invalid  CardTypes value");
    }
}
