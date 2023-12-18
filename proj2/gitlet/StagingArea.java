package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static gitlet.MyUtils.rm;
import static gitlet.Utils.readObject;
import static gitlet.Utils.writeObject;

public class StagingArea implements Serializable {


    /** The tracked files Map with file path as key and its sha1 id as value. */
    private transient Map<String, String> tracked;

    public static StagingArea fromFile() {
        return readObject(Repository.INDEX, StagingArea.class);
    }

    public void setTracked(Map<String, String> filesMap) {
        tracked = filesMap;
    }

    /** Save this instance to the file INDEX. */
    public void save() {
        writeObject(Repository.INDEX, this);
    }

    /** The added filed Map with file path as key and sha1 id as value. */
    private final Map<String, String> added = new HashMap<>();

    /** The removed files Map with file path as key. */
    private final Set<String> removed = new HashSet<>();

    /** Add file to the staging area. */
    public boolean add(File file) {
        String filePath = file.getPath();

        Blob blob = new Blob(file);
        String blobId = blob.getId();

        String trackedBlobId = tracked.get(filePath);
        if (trackedBlobId != null) { // If there is a blob in current directory with same filePath.
            if (trackedBlobId.equals(blobId)) { //If the blob we are ganna add is the same as the current directory(no change at all).
                if (added.remove(filePath) != null) { //If this blob is already in the add stagingArea.
                    return true; //Remove it and save the change.
                } //This blob is already in the remove staging area.
                return removed.remove(filePath); // Remove it and save the change.
            }
        }

        String prevBlobId = added.put(filePath, blobId); // Returns null if filePath not exists(blob is new), returns the original value(id) if the filePath exists.
        if (prevBlobId != null && prevBlobId.equals(blobId)) { // The added blob is the same as the new blob we are gonna add. -> File added, (changed, hanged back), and added.
            return false; // No need to do anything.
        }

        if (!blob.getFile().exists()) { // If blob was not saved before.
            blob.save();
        }

        return true;
    }

    public boolean isClean() {
        if (added.isEmpty() && removed.isEmpty()) {
            return true;
        }
        return false;
    }

    /** Perform a commit. Return tracked files Map after commit. */
    public Map<String, String> commit() {
        /* Modify the current tracked files based on the staging area information. */
        tracked.putAll(added);
        for (String filePath : removed) {
            tracked.remove(filePath);
        }

        clear(); //clear the staging area

        return tracked;
    }

    /** Clear all the information in the stagingArea. */
    private void clear() {
        added.clear();
        removed.clear();
    }

    public boolean remove(File file) {
        String filePath = file.getPath();

        /* Check to see of the file is in the added staging area. */
        String addedBlobId = added.remove(filePath);
        if (addedBlobId != null) {
            return true;
        }

        /* Check to see if the file is in the current working directory. */
        if (tracked.get(filePath) != null) { //If the file has the same name.
            if (file.exists()) { //If the file has the same content.
                rm(file); //Remove the file from current working directory.
            }
            return removed.add(filePath); //Add the file to the removed staging area.
        }

        return false;
    }
}
