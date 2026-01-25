package me.Srb.nbblcoreNeo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class Team {

    private SerializableLocation startLocation;
    private SerializableLocation endLocation;
    @Singular
    private List<UUID> players;
}
