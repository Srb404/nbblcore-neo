package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.command.Subcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class ChallengeManageNameSub extends Subcommand {

    public ChallengeManageNameSub(ChallengeStorage storage) {
        super(storage);
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("name")
                .withArguments(new StringArgument("newChallengeName"))
                .executes((sender, args) -> {
                    String oldName = (String) args.get("challengeName");
                    String newName = (String) args.get("newChallengeName");

                    Challenge challenge = getOrFail(sender, oldName);
                    if (challenge == null) return;

                    boolean success = storage.update(oldName, Challenge.builder()
                            .name(newName)
                            .time(challenge.getTime())
                            .teams(challenge.getTeams())
                            .build()
                    );
                    sendResult(sender, success, "§aZmieniono nazwę wyzwania §7" + oldName + "§a na §f" + newName);
                });
    }
}
