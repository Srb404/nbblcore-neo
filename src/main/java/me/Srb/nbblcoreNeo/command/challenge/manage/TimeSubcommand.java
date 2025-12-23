package me.Srb.nbblcoreNeo.command.challenge.manage;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import me.Srb.nbblcoreNeo.command.challenge.Subcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class TimeSubcommand implements Subcommand {

    private final ChallengeStorage storage;

    public TimeSubcommand(ChallengeStorage storage) {
        this.storage = storage;
    }

    public CommandAPICommand command() {
        return new CommandAPICommand("time")
                .withArguments(new IntegerArgument("newTime", 1))
                .executes((sender, args) -> {
                    String name = (String) args.get("challengeName");
                    Integer newTime = (Integer) args.get("newTime");

                    Challenge challenge = storage.read(name);
                    storage.update(name, Challenge.builder()
                            .name(challenge.getName())
                            .time(newTime)
                            .teams(challenge.getTeams())
                            .build()
                    );
                    sender.sendMessage("§aZmieniono czas wyzwania na: §f" + newTime);
                });
    }
}
