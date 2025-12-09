package me.Srb.nbblcoreNeo;

import lombok.Getter;
import me.Srb.nbblcoreNeo.model.Challenge;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class nbblcoreNeo extends JavaPlugin {

    @Getter
    private static nbblcoreNeo plugin;
    @Override
    public void onEnable() {
        plugin = this;
    }
}
