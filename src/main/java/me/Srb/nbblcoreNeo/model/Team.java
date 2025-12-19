package me.Srb.nbblcoreNeo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Team {

    @Singular
    private List<UUID> players;
    private SerializableLocation teamStart;
    private SerializableLocation teamEnd;

    public static Team getDummyData() {
        World world = Bukkit.getWorld("world");

        return Team.builder()
                .teamStart(SerializableLocation.from(new Location(world, 0, 0, 0)))
                .teamEnd(SerializableLocation.from(new Location(world, 0, 0, 0)))
                .player(UUID.randomUUID())
                .player(UUID.randomUUID())
                .player(UUID.randomUUID())
                .build();
    }
}
