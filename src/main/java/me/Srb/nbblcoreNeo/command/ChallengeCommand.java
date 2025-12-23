package me.Srb.nbblcoreNeo.command;

import dev.jorel.commandapi.CommandAPICommand;
import me.Srb.nbblcoreNeo.command.challenge.CreateSubcommand;
import me.Srb.nbblcoreNeo.command.challenge.ListSubcommand;
import me.Srb.nbblcoreNeo.command.challenge.ManageSubcommand;
import me.Srb.nbblcoreNeo.command.challenge.RemoveSubcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class ChallengeCommand {

    private final ChallengeStorage challengeStorage = new ChallengeStorage();
    private Challenge challenge;

    public void register() {
        new CommandAPICommand("challenge")
                .withSubcommands(
                        new CreateSubcommand(challengeStorage).command(),
                        new RemoveSubcommand(challengeStorage).command(),
                        new ListSubcommand(challengeStorage).command(),
                        new ManageSubcommand(challengeStorage).command()
                )
                .executes((sender, args) -> {
                    sender.sendMessage("§cPoprawne użycie: /challenge <create|remove|list|manage>");
                }).register();
    }
}
