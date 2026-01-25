package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class RemoveSubcommand extends Subcommand {

    public RemoveSubcommand(ChallengeStorage storage) {
        super(storage);
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("remove")
                .withArguments(new StringArgument("name"))
                .executes((sender, args) -> {
                    String name = (String) args.get("name");

                    Challenge challenge = getOrFail(sender, name);
                    if (challenge == null) return;

                    boolean success = storage.delete(challenge);
                    sendResult(sender, success, "§aUsunięto wyzwanie: §f" + name);
                });
    }
}
