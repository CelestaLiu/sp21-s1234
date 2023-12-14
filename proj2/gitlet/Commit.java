package gitlet;

// TODO: any imports you need here

import org.w3c.dom.UserDataHandler;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import static gitlet.MyUtils.getObjectFile;
import static gitlet.MyUtils.saveObjectFile;
import static gitlet.Utils.readObject;
import static gitlet.Utils.sha1;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private final Date date;
    private final List<String> parents;
    private final Map<String, String> tracked;
    private final String id;
    private final File file;


    public Commit() {
        date = new Date(0);
        message = "initial commit";
        parents = new ArrayList<>();
        tracked = new HashMap<>();
        id = generateId();
        file = getObjectFile(id);
    }

    private String generateId() {
        return sha1(getTimeStamp(), message, parents.toString(), tracked.toString());
    }

    private String getTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public void save() {
        saveObjectFile(file, this);
    }

    public static Commit fromFile(String id) {
        return readObject(getObjectFile(id), Commit.class);
    }

    public Map<String, String> getTracked() {
        return tracked;
    }

    /* TODO: fill in the rest of this class. */
}
