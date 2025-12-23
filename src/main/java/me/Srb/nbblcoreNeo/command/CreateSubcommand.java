package me.Srb.nbblcoreNeo.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class CreateSubcommand {
    public ChallengeStorage storage;

    public CreateSubcommand(ChallengeStorage storage) {
        this.storage = storage;
    }

    public CommandAPICommand command() {
        return new CommandAPICommand("create")
                .withArguments(new StringArgument("name"))
                .executes((sender, args) -> {
                    String name = (String) args.get("name");

                    Challenge challenge = Challenge.builder()
                            .name(name)
                            .build();

                    storage.create(challenge);
                    sender.sendMessage("Utworzono wyzwanie: " + name);
                });
    }
}
