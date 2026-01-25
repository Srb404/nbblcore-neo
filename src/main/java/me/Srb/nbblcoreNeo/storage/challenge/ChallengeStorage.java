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
        if (exists(challenge.getName())) {
            return false;
        }
        challenges.add(challenge);
        return fileManager.saveFile();
    }

    public Challenge read(String name) {
        for (Challenge challenge : challenges) {
            if (challenge.getName().equalsIgnoreCase(name)) {
                return challenge;
            }
        }
        return null;
    }

    public boolean exists(String name) {
        return read(name) != null;
    }

    public boolean update(String oldName, Challenge newChallenge) {
        for (int i = 0; i < challenges.size(); i++) {
            if (challenges.get(i).getName().equalsIgnoreCase(oldName)) {
                challenges.set(i, newChallenge);
                return fileManager.saveFile();
            }
        }
        return false;
    }

    public boolean delete(Challenge challenge) {
        boolean removed = challenges.removeIf(c ->
                c.getName().equalsIgnoreCase(challenge.getName()));
        if (removed) {
            return fileManager.saveFile();
        }
        return false;
    }
}
