package me.Srb.nbblcoreNeo.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
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
                        createSubcommand(),
                        removeSubcommand(),
                        listSubcommand()
                )
                .executes((sender, args) -> {
                    sender.sendMessage("Poprawne użycie: /challenge <create|remove|list>");
                }).register();
    }

    private CommandAPICommand createSubcommand() {
        return new CommandAPICommand("create")
                .withArguments(new StringArgument("name"))
                .executes((sender, args) -> {
                    String name = (String) args.get("name");
                    challenge = Challenge.builder()
                            .challengeName(name)
                            .build();
                    challengeStorage.addChallenge(challenge);
                    sender.sendMessage("Utworzono wyzwanie: " + name);
                });
    }

    private CommandAPICommand removeSubcommand() {
        return new CommandAPICommand("remove")
                .withArguments(new StringArgument("name"))
                .executes((sender, args) -> {
                    String name = (String) args.get("name");
                    challenge = challengeStorage.getChallenge(name);
                    challengeStorage.removeChallenge(challenge);
                });
    }

    private CommandAPICommand listSubcommand() {
        return new CommandAPICommand("list")
                .executes((sender, args) -> {
                    List<Challenge> challenges = challengeStorage.getChallenges();
                    sender.sendMessage("=== Challenges Info ===");
                    for (Challenge challenge : challenges) {
                        sender.sendMessage(challenge.getChallengeName());
                        int counter = 0;
                        for (Team team : challenge.getTeams()) {
                            sender.sendMessage("---");
                            sender.sendMessage("Drużyna " + ++counter);
                            sender.sendMessage("Gracze: " + team.getPlayers());
                            sender.sendMessage("Start: "  + team.getTeamStart());
                            sender.sendMessage("End: " + team.getTeamEnd());
                        }
                        sender.sendMessage("---");
                        sender.sendMessage("Czas trwania: " + challenge.getTime());
                    }
                });
    }
}
