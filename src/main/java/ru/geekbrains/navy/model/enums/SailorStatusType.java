package ru.geekbrains.navy.model.enums;

public enum SailorStatusType {

    IN_COMMAND, FREE;

    public static SailorStatusType getStatusType(String statusInString) {
        for (SailorStatusType type : SailorStatusType.values()) {
            if (type.name().equals(statusInString)) {
                return type;
            }
        }
        return null;
    }
}
