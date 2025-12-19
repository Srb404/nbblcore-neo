package me.Srb.nbblcoreNeo.storage.challenge;

import lombok.Getter;
import me.Srb.nbblcoreNeo.model.Challenge;

import java.util.List;

public class ChallengeStorage {

    private final ChallengeFileManager fileManager;
    @Getter
    private final List<Challenge> challenges;

    public ChallengeStorage() {
        fileManager = ChallengeFileManager.getInstance();
        challenges = fileManager.getChallenges();
    }

    public boolean addChallenge(Challenge challenge) {
        challenges.add(challenge);
        fileManager.setChallenges(challenges);
        return fileManager.saveFile();
    }

    public boolean updateChallenge(String oldChallenge, Challenge newChallenge) {
        for (Challenge challenge : challenges) {
            if (challenge.getChallengeName().equals(oldChallenge)) {
                challenges.remove(challenge);
                challenges.add(newChallenge);
                return fileManager.saveFile();
            }
        }
        return false;
    }

    public boolean removeChallenge(Challenge challenge) {
        challenges.remove(challenge);
        fileManager.setChallenges(challenges);
        return fileManager.saveFile();
    }

    public Challenge getChallenge(String name) {
        for (Challenge challenge : challenges) {
            if (challenge.getChallengeName().equalsIgnoreCase(name)) return challenge;
        }
        return Challenge.builder().challengeName("not-found").build();
    }

}
