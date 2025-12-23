package me.Srb.nbblcoreNeo.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import me.Srb.nbblcoreNeo.challenge.ChallengeValidator;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.model.Team;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

import java.util.List;

public class ChallengeCommand {

    private final ChallengeStorage challengeStorage = new ChallengeStorage();
    private Challenge challenge;

    public void register() {
        new CommandAPICommand("challenge")
                .withSubcommands(
                        new CreateSubcommand(challengeStorage).command(),
                        new RemoveSubcommand(challengeStorage).command(),
                        new ListSubcommand(challengeStorage).command()
                )
                .executes((sender, args) -> {
                    sender.sendMessage("Poprawne użycie: /challenge <create|remove|list>");
                }).register();
    }
}
