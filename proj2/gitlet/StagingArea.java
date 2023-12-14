package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
}
