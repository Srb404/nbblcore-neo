package me.Srb.nbblcoreNeo.storage;

import me.Srb.nbblcoreNeo.NbblcoreNeo;

import java.io.File;
import java.util.logging.Logger;

public abstract class FileManager {
    protected final Logger logger;
    protected final File file;
    protected final String path;

    public FileManager(String fileName) {
        this.logger = NbblcoreNeo.getPlugin().getLogger();
        this.path = NbblcoreNeo.getPlugin().getDataFolder().getAbsolutePath() + "/" + fileName;
        this.file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public abstract boolean readFile();
    public abstract boolean saveFile();
}
