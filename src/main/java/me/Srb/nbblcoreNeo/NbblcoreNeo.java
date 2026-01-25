package me.Srb.nbblcoreNeo;

import lombok.Getter;
import me.Srb.nbblcoreNeo.command.challenge.ChallengeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class NbblcoreNeo extends JavaPlugin {

    @Getter
    private static NbblcoreNeo plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new ChallengeCommand().register();
    }
}
