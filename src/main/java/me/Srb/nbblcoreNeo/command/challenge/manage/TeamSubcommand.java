package me.Srb.nbblcoreNeo.command.challenge.manage;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import me.Srb.nbblcoreNeo.command.challenge.Subcommand;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.model.Team;
import me.Srb.nbblcoreNeo.storage.challenge.ChallengeStorage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamSubcommand extends Subcommand {

    public TeamSubcommand(ChallengeStorage storage) {
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

                    List<Team> teams = challenge.getTeams();
                    if (teams == null) {
                        teams = new ArrayList<>();
                    }

                    while (teams.size() <= teamId) {
                        teams.add(null);
                    }

                    Team team = teams.get(teamId);
                    List<UUID> players;

                    if (team != null && team.getPlayers() != null) {
                        players = new ArrayList<>(team.getPlayers());
                    } else {
                        players = new ArrayList<>();
                    }

                    if (players.contains(player.getUniqueId())) {
                        sender.sendMessage("§cTen gracz już jest w tej drużynie.");
                        return;
                    }

                    players.add(player.getUniqueId());

                    Team newTeam = Team.builder()
                            .players(players)
                            .startLocation(team != null ? team.getStartLocation() : null)
                            .endLocation(team != null ? team.getEndLocation() : null)
                            .build();

                    teams.set(teamId, newTeam);

                    storage.update(challengeName, Challenge.builder()
                            .name(challenge.getName())
                            .time(challenge.getTime())
                            .teams(teams)
                            .build());

                    sender.sendMessage("§aDodano gracza §e" + player.getName() +
                            " §ado drużyny §e" + teamId);
                });
    }
}
