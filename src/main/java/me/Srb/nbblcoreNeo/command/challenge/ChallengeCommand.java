package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;

public class ChallengeCommand {

    private final ChallengeStorage challengeStorage = new ChallengeStorage();

    public void register() {
        new CommandAPICommand("challenge")
                .withSubcommands(
                        new ChallengeCreateSub(challengeStorage).command(),
                        new ChallengeRemoveSub(challengeStorage).command(),
                        new ChallengeListSub(challengeStorage).command(),
                        new ChallengeManageSub(challengeStorage).command()
                )
                .executes((sender, args) -> {
                    sender.sendMessage("§cPoprawne użycie: /challenge <create|remove|list|manage>");
                }).register();
    }
}
