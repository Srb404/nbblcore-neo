package me.Srb.nbblcoreNeo.model;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public record SerializableLocation(
        String world,
        double x,
        double y,
        double z,
        float yaw,
        float pitch
) {
    public static SerializableLocation from(Location loc) {
        if (loc == null) {
            return null;
        }
        World w = loc.getWorld();
        String worldName = (w != null) ? w.getName() : "world";

        return new SerializableLocation(
                worldName,
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                loc.getYaw(),
                loc.getPitch()
        );
    }

    public Location toBukkit() {
        World w = Bukkit.getWorld(world);
        if (w == null) {
            return null;
        }
        return new Location(w, x, y, z, yaw, pitch);
    }
}
