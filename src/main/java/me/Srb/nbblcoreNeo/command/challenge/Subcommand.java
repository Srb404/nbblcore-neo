package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;
import org.bukkit.command.CommandSender;

public abstract class Subcommand {
    protected final ChallengeStorage storage;

    protected Subcommand(ChallengeStorage storage) {
        this.storage = storage;
    }

    public abstract CommandAPICommand command();

    protected Challenge getOrFail(CommandSender sender, String name) {
        Challenge challenge = storage.read(name);
        if (challenge == null) {
            sender.sendMessage("§cNie znaleziono wyzwania: §f" + name);
        }
        return challenge;
    }

    protected void sendResult(CommandSender sender, boolean success, String successMsg) {
        if (success) {
            sender.sendMessage(successMsg);
        } else {
            sender.sendMessage("§cWystąpił błąd podczas zapisu danych.");
        }
    }
}
