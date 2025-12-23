package me.Srb.nbblcoreNeo.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class RemoveSubcommand {
    public ChallengeStorage storage;

    public RemoveSubcommand(ChallengeStorage storage) {
        this.storage = storage;
    }

    public CommandAPICommand command() {
        return new CommandAPICommand("remove")
                .withArguments(new StringArgument("name"))
                .executes((sender, args) -> {
                    String name = (String) args.get("name");

                    Challenge challenge = storage.read(name);
                    if (challenge == null) {
                        sender.sendMessage("§cNie znaleziono wyzwania: " + name);
                        return;
                    }

                    storage.delete(challenge);
                    sender.sendMessage("§aUsunięto wyzwanie: §f" + name);
                });
    }
}
