package org.spring.rest.api.example.enums;

public enum ModificationType {

    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String externalName;

    ModificationType(final String externalName) {
        this.externalName = externalName;
    }

    public static ModificationType getModificationByExternalName(final String externalName) {
        for (final ModificationType modificationType : values()) {
            if (modificationType.externalName.equals(externalName)) {
                return modificationType;
            }
        }
        throw new IllegalArgumentException(String.valueOf(externalName));
    }

}
