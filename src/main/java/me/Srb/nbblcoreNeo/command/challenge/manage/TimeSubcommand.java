package me.Srb.nbblcoreNeo.command.challenge.manage;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import me.Srb.nbblcoreNeo.command.challenge.Subcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class TimeSubcommand extends Subcommand {

    public TimeSubcommand(ChallengeStorage storage) {
        super(storage);
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("time")
                .withArguments(new IntegerArgument("newTime", 1))
                .executes((sender, args) -> {
                    String name = (String) args.get("challengeName");
                    Integer newTime = (Integer) args.get("newTime");

                    Challenge challenge = getOrFail(sender, name);
                    if (challenge == null) return;

                    boolean success = storage.update(name, Challenge.builder()
                            .name(challenge.getName())
                            .time(newTime)
                            .teams(challenge.getTeams())
                            .build()
                    );
                    sendResult(sender, success, "§aZmieniono czas wyzwania na: §f" + newTime);
                });
    }
}
