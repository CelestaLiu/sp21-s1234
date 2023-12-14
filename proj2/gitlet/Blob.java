package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.MyUtils.getObjectFile;
import static gitlet.MyUtils.saveObjectFile;
import static gitlet.Utils.*;

/**  The file object. */
public class Blob implements Serializable {
    /** The path of source file. */
    private final File source;

    /** The content of source file. */
    private final byte[] content;

    /** The sha id. */
    private final String id;

    /** The generated file named by sha id. */
    private final File file;

    public Blob(File sourceFile) {
        source = sourceFile;
        String filePath = sourceFile.getPath();
        content = readContents(source);
        id = sha1(filePath, content);
        file = getObjectFile(id);
    }

    /**  Get the sha id. */
    public String getId() {
        return id;
    }

    /** Get the file. */
    public File getFile() {
        return file;
    }


    /* Save this blob to file in objects folder. */
    public void save() {
        saveObjectFile(file, this);
    }
}
