package me.Srb.nbblcoreNeo;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ChallengeStorageUtil {
    // CRUD - Create, Read, Update, Delete
    private static ArrayList<Challenge> challenges = new ArrayList<>();

    // CREATE
    public static Challenge createChallenge(String challengeName, int time, ArrayList<Team> teams) {
        Challenge challenge = new Challenge();
        challenge.setChallengeName(challengeName);
        challenge.setTime(time);
        challenge.setTeams(teams);

        challenges.add(challenge);
        return challenge;
    }

    // READ
    public static ArrayList<Challenge> getAllChallenges() {
        return challenges;
    }

    // READ
    public static Challenge getChallenge(String challengeName) {
        for (Challenge challenge : challenges) {
            if (challenge.getChallengeName().equalsIgnoreCase(challengeName)) {
                return challenge;
            }
        }
        return null;
    }

    // UPDATE
    public static boolean updateChallenge(String challengeName, int newTime, ArrayList<Team> newTeams) {
        Challenge challenge = getChallenge(challengeName);
        if (challenge == null) return false;

        challenge.setTime(newTime);
        challenge.setTeams(newTeams);
        saveChallenges();
        return true;
    }

    // DELETE
    public static boolean deleteChallenge(String challengeName) {
        Challenge challenge = getChallenge(challengeName);
        if (challenge == null) return false;

        challenges.remove(challenge);
        saveChallenges();
        return true;
    }

    public static void saveChallenges() {
        Gson gson = new Gson();
        File file = new File(nbblcoreNeo.getPlugin().getDataFolder(), "challenges.json");
        file.getParentFile().mkdir();
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            gson.toJson(challenges, fileWriter);
            fileWriter.close();
            System.out.println("Challenges have been saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
