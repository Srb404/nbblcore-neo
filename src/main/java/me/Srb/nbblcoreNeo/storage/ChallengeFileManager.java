package me.Srb.nbblcoreNeo.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import me.Srb.nbblcoreNeo.model.Challenge;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChallengeFileManager extends FileManager {
    @Getter
    @Setter
    private List<Challenge> challenges = new ArrayList<>();
    private final Type typeToken = new TypeToken<ArrayList<Challenge>>() {}.getType();

    private static ChallengeFileManager instance;

    private ChallengeFileManager() {
        super("challenges.json");
    }

    public static ChallengeFileManager getInstance() {
        return (instance == null) ? instance = new ChallengeFileManager() : instance;
    }

    @Override
    public boolean readFile() {
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            challenges = gson.fromJson(reader, typeToken);

            if (challenges == null) challenges = new ArrayList<>();
            LOGGER.info("File read");
            return true;
        } catch (IOException e) {
            LOGGER.severe("Failed to read file: " + path);
            LOGGER.severe("Creating new one...");
            if (!saveFile()) {
                LOGGER.severe("Failed to save file!");
                return false;
            }
            LOGGER.severe("Done!");
            return true;
        }
    }

    @Override
    public boolean saveFile() {
        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(challenges, writer);
            LOGGER.info("File saved");
            return true;
        } catch (IOException e) {
            LOGGER.severe("Failed to save file: " + path);
            return false;
        }
    }
}
