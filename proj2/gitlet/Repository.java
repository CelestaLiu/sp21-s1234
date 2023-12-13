package gitlet;

import java.io.File;

import static gitlet.MyUtils.exit;
import static gitlet.MyUtils.mkdir;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  .gitlet/ -- top level folder for all persistent data
 *      -commit
 *      -blob
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /**
     * The structure of the whole .gitlet
     *  .gitlet
     *      objects
     *          commit and blob
     *      refs
     *          heads
     *      HEAD
     *
     * */


    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /** The refs directory. */
    public static final File REFS_DIR = join(GITLET_DIR, "refs");

    /** The heads directory. */
    public static final File BRANCH_HEADS_DIR = join(REFS_DIR, "heads");

    /** The objects directory. */
    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");

    /** The head directory. */
    public static final File HEAD = join(GITLET_DIR, "HEAD");

    /** The default branch name: master. */
    public static final String DEFAULT_BRANCH_NAME = "master";

    /** HEAD ref prefix. */
    private static final String HEAD_BRANCH_REF_PREFIX = "ref: refs/heads/";


    /* TODO: fill in the rest of this class. */

    public static void init() {
        if (GITLET_DIR.exists()) {
            exit("A Gitlet version-control system already exists in the current directory.");
        }
        mkdir(GITLET_DIR);
        mkdir(REFS_DIR);
        mkdir(BRANCH_HEADS_DIR);
        mkdir(OBJECTS_DIR);
        setCurrentBranch(DEFAULT_BRANCH_NAME);
        createInitialCommit();
    }

    private static void setCurrentBranch(String branchName) {
        writeContents(HEAD, HEAD_BRANCH_REF_PREFIX + branchName);
    }

    private static void createInitialCommit() {
        Commit initialCommit = new Commit();
        initialCommit.save();

    }
}
