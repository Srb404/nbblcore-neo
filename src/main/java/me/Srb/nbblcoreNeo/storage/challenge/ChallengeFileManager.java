package me.Srb.nbblcoreNeo.storage.challenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import me.Srb.nbblcoreNeo.model.Challenge;
import me.Srb.nbblcoreNeo.storage.FileManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChallengeFileManager extends FileManager {

    private static volatile ChallengeFileManager instance;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type typeToken = new TypeToken<ArrayList<Challenge>>() {}.getType();

    @Getter
    @Setter
    private List<Challenge> challenges = new ArrayList<>();

    private ChallengeFileManager() {
        super("challenges.json");
        readFile();
    }

    public static ChallengeFileManager getInstance() {
        if (instance == null) {
            synchronized (ChallengeFileManager.class) {
                if (instance == null) {
                    instance = new ChallengeFileManager();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean readFile() {
        try (FileReader reader = new FileReader(file)) {
            challenges = gson.fromJson(reader, typeToken);

            if (challenges == null) {
                challenges = new ArrayList<>();
            }
            logger.info("Challenges file loaded");
            return true;
        } catch (IOException e) {
            logger.warning("Failed to read file: " + path);
            logger.info("Creating new challenges file...");
            if (!saveFile()) {
                logger.severe("Failed to create challenges file!");
                return false;
            }
            logger.info("Challenges file created successfully");
            return true;
        }
    }

    @Override
    public boolean saveFile() {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(challenges, writer);
            logger.info("Challenges file saved");
            return true;
        } catch (IOException e) {
            logger.severe("Failed to save file: " + path);
            return false;
        }
    }
}
