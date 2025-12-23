package me.Srb.nbblcoreNeo.storage.challenge;

import lombok.Getter;
import me.Srb.nbblcoreNeo.model.Challenge;

import java.util.List;

public class ChallengeStorage {

    @Getter
    private final List<Challenge> challenges;
    private final ChallengeFileManager fileManager;

    public ChallengeStorage() {
        fileManager = ChallengeFileManager.getInstance();
        challenges = fileManager.getChallenges();
    }

    public boolean create(Challenge challenge) {
        challenges.add(challenge);
        fileManager.setChallenges(challenges);
        return fileManager.saveFile();
    }

    public Challenge read(String name) {
        for (Challenge challenge : challenges) {
            if (challenge.getChallengeName().equalsIgnoreCase(name)) return challenge;
        }
        return Challenge.builder().challengeName("not-found").build();
    }

    public boolean update(String oldChallenge, Challenge newChallenge) {
        for (Challenge challenge : challenges) {
            if (challenge.getChallengeName().equals(oldChallenge)) {
                challenges.remove(challenge);
                challenges.add(newChallenge);
                return fileManager.saveFile();
            }
        }
        return false;
    }

    public boolean delete(Challenge challenge) {
        challenges.remove(challenge);
        fileManager.setChallenges(challenges);
        return fileManager.saveFile();
    }
}
