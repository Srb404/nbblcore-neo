package me.Srb.nbblcoreNeo.command.challenge.manage;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.command.challenge.Subcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class NameSubcommand implements Subcommand {

    private final ChallengeStorage storage;

    public NameSubcommand(ChallengeStorage storage) {
        this.storage = storage;
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("name")
                .withArguments(new StringArgument("newChallengeName"))
                .executes((sender, args) -> {
                    String oldName = (String) args.get("challengeName");
                    String newName = (String) args.get("newChallengeName");

                    Challenge challenge = storage.read(oldName);
                    storage.update(oldName, Challenge.builder()
                            .name(newName)
                            .time(challenge.getTime())
                            .teams(challenge.getTeams())
                            .build()
                    );
                    sender.sendMessage("§aZmieniono nazwę wyzwania §7" + oldName + "§a na §f" + newName);
                });
    }
}
