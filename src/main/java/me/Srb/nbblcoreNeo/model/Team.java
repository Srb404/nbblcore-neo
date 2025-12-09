package me.Srb.nbblcoreNeo.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.UUID;

public record Team (SerializableLocation teamStart, SerializableLocation teamEnd, ArrayList<UUID> players) {

    public static Team getDummyData() {
        World world = Bukkit.getWorld("world");
        SerializableLocation start = SerializableLocation.from(new Location(world, 0, 0, 0));
        SerializableLocation end = SerializableLocation.from(new Location(world, 0, 0, 0));
        ArrayList<UUID> players = new ArrayList<>();
        players.add(UUID.randomUUID());
        players.add(UUID.randomUUID());
        players.add(UUID.randomUUID());
        return new Team(start, end, players);
    }
}
