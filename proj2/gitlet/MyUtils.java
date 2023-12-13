package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;

public class MyUtils {
    public static void exit(String message, Object... args) {
        message(message, args);
        System.exit(0);
    }

    public static void mkdir(File dir) {
        if (!dir.mkdir()) {
            throw new IllegalArgumentException(String.format("mkdir: %s: Failed to create.", dir.getPath()));
        }
    }

    public static File getObjectFile(String id) {
        String dirName = getObjectDirName(id);
        String fileName = getObjectFileName(id);
        return join(Repository.OBJECTS_DIR, dirName, fileName);
    }

    public static String getObjectDirName(String id) {
        return id.substring(0, 2);
    }

    public static String getObjectFileName(String id) {
        return id.substring(2);
    }

    public static void saveObjectFile(File file, Serializable obj) {
        File dir = file.getParentFile();
        if (!dir.exists()) {
            mkdir(dir);
        }
        writeObject(file, obj);
    }

}
