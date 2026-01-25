package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.ui.ChallengeManageGUI;
import me.Srb.nbblcoreNeo.command.challenge.manage.NameSubcommand;
import me.Srb.nbblcoreNeo.command.challenge.manage.TeamSubcommand;
import me.Srb.nbblcoreNeo.command.challenge.manage.TimeSubcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class ManageSubcommand extends Subcommand {

    public ManageSubcommand(ChallengeStorage storage) {
        super(storage);
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("manage")
                .withArguments(new StringArgument("challengeName"))
                .withSubcommand(new NameSubcommand(storage).command())
                .withSubcommand(new TimeSubcommand(storage).command())
                .withSubcommand(new TeamSubcommand(storage).command())
                .executesConsole((sender, args) -> {
                    sender.sendMessage("§cTa komenda wywołuje GUI. Musisz być w grze, aby jej użyć.");
                })
                .executesPlayer((sender, args) -> {
                    String challengeName = (String) args.get("challengeName");

                    Challenge challenge = getOrFail(sender, challengeName);
                    if (challenge == null) return;

                    new ChallengeManageGUI().open(challenge, sender);
                });
    }
}
