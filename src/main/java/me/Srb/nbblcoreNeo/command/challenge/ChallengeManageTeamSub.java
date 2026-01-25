package me.Srb.nbblcoreNeo.command.challenge;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import me.Srb.nbblcoreNeo.command.Subcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.model.Team;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChallengeManageTeamSub extends Subcommand {

    public ChallengeManageTeamSub(ChallengeStorage storage) {
        super(storage);
    }

    @Override
    public CommandAPICommand command() {
        return new CommandAPICommand("team")
                .withSubcommand(add());
    }

    private CommandAPICommand add() {
        return new CommandAPICommand("add")
                .withArguments(new IntegerArgument("teamId", 0, 100))
                .withArguments(new EntitySelectorArgument.OnePlayer("teamMember"))
                .executesPlayer((sender, args) -> {

                    String challengeName = (String) args.get("challengeName");
                    Integer teamId = (Integer) args.get("teamId");
                    Player player = (Player) args.get("teamMember");

                    Challenge challenge = getOrFail(sender, challengeName);
                    if (challenge == null) return;

                    List<Team> teams = new ArrayList<>(
                            challenge.getTeams() != null ? challenge.getTeams() : List.of()
                    );

                    while (teams.size() <= teamId) {
                        teams.add(null);
                    }

                    Team team = teams.get(teamId);
                    List<UUID> players = new ArrayList<>(
                            team != null && team.getPlayers() != null ? team.getPlayers() : List.of()
                    );

                    if (players.contains(player.getUniqueId())) {
                        sender.sendMessage("§cTen gracz już jest w tej drużynie.");
                        return;
                    }

                    players.add(player.getUniqueId());

                    Team newTeam = (team != null)
                            ? team.toBuilder().players(players).build()
                            : Team.builder().players(players).build();

                    teams.set(teamId, newTeam);

                    boolean success = storage.update(challengeName, challenge.toBuilder()
                            .teams(teams)
                            .build());

                    sendResult(sender, success, "§aDodano gracza §e" + player.getName() +
                            " §ado drużyny §e" + teamId);
                });
    }
}
