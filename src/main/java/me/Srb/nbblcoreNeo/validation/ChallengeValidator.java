package me.Srb.nbblcoreNeo.validation;

import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.model.Team;

import java.util.EnumSet;

public class ChallengeValidator {

    public EnumSet<ChallengeRequirement> validate(Challenge challenge) {
        EnumSet<ChallengeRequirement> missing = EnumSet.noneOf(ChallengeRequirement.class);
        if (challenge.getName() == null || challenge.getName().isEmpty())
            missing.add(ChallengeRequirement.NAME_SET);
        if (challenge.getTime() < 0)
            missing.add(ChallengeRequirement.TIME_SET);
        if (challenge.getTeams() == null || challenge.getTeams().isEmpty())
            missing.add(ChallengeRequirement.TEAMS_DEFINED);
        else {
            for (Team team : challenge.getTeams()) {
                if (team.getPlayers() == null || team.getPlayers().isEmpty()) missing.add(ChallengeRequirement.TEAMS_HAS_PLAYERS);
                if (team.getStartLocation() == null) missing.add(ChallengeRequirement.TEAMS_HAS_START);
                if (team.getEndLocation() == null) missing.add(ChallengeRequirement.TEAMS_HAS_END);
            }
        }
        return missing;
    }

    public boolean isReady(Challenge challenge) {
        return validate(challenge).isEmpty();
    }
}
