package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import me.Srb.nbblcoreNeo.command.Subcommand;
import me.Srb.nbblcoreNeo.validation.ChallengeValidator;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.model.Team;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ChallengeListSub extends Subcommand {

    public ChallengeListSub(ChallengeStorage storage) {
        super(storage);
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("list")
                .executes((sender, args) -> {
                    List<Challenge> challenges = storage.getChallenges();

                    sender.sendMessage("§8§m======§r §6§lWyzwania§r §8§m======");

                    if (challenges.isEmpty()) {
                        sender.sendMessage("§7Brak dostępnych wyzwań.");
                        return;
                    }

                    for (Challenge challenge : challenges) {
                        printChallenge(sender, challenge);
                    }
                });
    }

    private void printChallenge(CommandSender sender, Challenge challenge) {
        sender.sendMessage("");
        sender.sendMessage("§e▶ §fWyzwanie: §b" + challenge.getName());

        String time = challenge.getTime() == 0
                ? "§7bez limitu"
                : "§f" + challenge.getTime() + "s";

        sender.sendMessage(" §8• §7Czas trwania: " + time);

        if (ChallengeValidator.isReady(challenge)) {
            sender.sendMessage(" §8• §7Status: §aGOTOWE");
        } else {
            sender.sendMessage(" §8• §7Status: §cNIEGOTOWE");
            String problems = ChallengeValidator.formatMissing(ChallengeValidator.validate(challenge));
            sender.sendMessage(" §8• §7Problemy: §c" + problems);
        }

        List<Team> teams = challenge.getTeams();
        if (teams == null) {
            return;
        }

        int index = 1;
        for (Team team : teams) {
            if (team == null) {
                index++;
                continue;
            }
            printTeam(sender, team, index++);
        }
    }

    private void printTeam(CommandSender sender, Team team, int index) {
        sender.sendMessage("   §8└ §6Drużyna #" + index);
        sender.sendMessage("     §8• §7Gracze: §f" + team.getPlayers());
        sender.sendMessage("     §8• §7Start: §f" + team.getStartLocation());
        sender.sendMessage("     §8• §7Meta: §f" + team.getEndLocation());
    }
}
