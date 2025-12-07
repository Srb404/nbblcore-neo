package me.Srb.nbblcoreNeo;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class Team {
    private ArrayList<UUID> team;
    private Location teamStart;
    private Location teamEnd;
}
