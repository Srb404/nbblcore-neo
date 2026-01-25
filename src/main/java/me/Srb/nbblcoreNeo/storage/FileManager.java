package me.Srb.nbblcoreNeo.storage;

import me.Srb.nbblcoreNeo.NbblcoreNeo;

import java.io.File;
import java.util.logging.Logger;

public abstract class FileManager {
    protected final Logger LOGGER;
    protected final File file;
    protected String path;

    public FileManager(String fileName) {
        this.LOGGER = NbblcoreNeo.getPlugin().getLogger();
        this.path = NbblcoreNeo.getPlugin().getDataFolder().getAbsolutePath() + "/" + fileName;
        this.file = new File(path);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
    }

    abstract public boolean readFile();
    abstract public boolean saveFile();
}
