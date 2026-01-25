package me.Srb.nbblcoreNeo.validation;

import lombok.Getter;

@Getter
public enum ChallengeRequirement {
    NAME_SET("Brak nazwy"),
    TIME_SET("Nieprawidłowy czas"),
    TEAMS_DEFINED("Brak drużyn"),
    TEAMS_HAS_PLAYERS("Drużyna bez graczy"),
    TEAMS_HAS_START("Brak lokalizacji startowej"),
    TEAMS_HAS_END("Brak lokalizacji końcowej");

    private final String description;

    ChallengeRequirement(String description) {
        this.description = description;
    }
}
