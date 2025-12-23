package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.challenge.ChallengeManageGUI;
import me.Srb.nbblcoreNeo.command.challenge.manage.NameSubcommand;
import me.Srb.nbblcoreNeo.command.challenge.manage.TeamsSubcommand;
import me.Srb.nbblcoreNeo.command.challenge.manage.TimeSubcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class ManageSubcommand implements Subcommand {
    public ChallengeStorage storage;

    public ManageSubcommand(ChallengeStorage storage) {
        this.storage = storage;
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("manage")
                .withArguments(new StringArgument("challengeName"))
                .executesConsole((sender, args) -> {
                    sender.sendMessage("§cTa komenda wywołuje GUI. Musisz być w grze, aby jej użyć.");
                })
                .executesPlayer((sender, args) -> {
                    String challengeName = (String) args.get("challengeName");

                    Challenge challenge = storage.read(challengeName);
                    if (challenge == null) {
                        sender.sendMessage("§cNie znaleziono wyzwania: " + challengeName);
                        return;
                    }
                    new ChallengeManageGUI().open(challenge, sender);
                });
    }
}
