package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.enums;

public enum Role {
    FULL("Повний доступ"),
    ADMIN("Адміністратор"),
    USER("USer"),
    READ_ONLY("Лише для читання");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
