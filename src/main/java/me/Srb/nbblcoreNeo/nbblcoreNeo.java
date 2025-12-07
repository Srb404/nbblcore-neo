package me.Srb.nbblcoreNeo;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class nbblcoreNeo extends JavaPlugin {

    @Getter
    private static nbblcoreNeo plugin;
    @Override
    public void onEnable() {
        plugin = this;
    }
}
