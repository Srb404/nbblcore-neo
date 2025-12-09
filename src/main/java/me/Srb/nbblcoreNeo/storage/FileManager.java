package me.Srb.nbblcoreNeo.storage;

import me.Srb.nbblcoreNeo.nbblcoreNeo;

import java.io.File;
import java.util.logging.Logger;

public abstract class FileManager {
    protected final Logger LOGGER;
    protected final File file;
    protected String path;

    protected FileManager(String fileName) {
        this.LOGGER = nbblcoreNeo.getPlugin().getLogger();
        this.path = nbblcoreNeo.getPlugin().getDataFolder().getAbsolutePath() + "/" + fileName;
        this.file = new File(path);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
    }

    abstract public boolean readFile();
    abstract public boolean saveFile();
}
