package gitlet;

import java.io.File;
import java.nio.file.Paths;

import static gitlet.MyUtils.*;
import static gitlet.Utils.*;
import static gitlet.Commit.*;

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

    /** The index file. */
    public static final File INDEX = join(GITLET_DIR, "index");

    /** The default branch name: master. */
    public static final String DEFAULT_BRANCH_NAME = "master";

    /** HEAD ref prefix. */
    private static final String HEAD_BRANCH_REF_PREFIX = "ref: refs/heads/";

    /** Files in the current working directory. */
    private static final Lazy<File[]> currentFiles = lazy(() -> CWD.listFiles(File::isFile));

    /** The current branch name. */
    private final Lazy<String> currentBranch = lazy(() -> {
        String HEADFileContent = readContentsAsString(HEAD);
        return HEADFileContent.replace(HEAD_BRANCH_REF_PREFIX, "");
    });

    /** The commit that HEAD points to. */
    private final Lazy<Commit> HEADCommit = lazy(() -> getBranchHeadCommit(currentBranch.get()));

    private final Lazy<StagingArea> stagingArea = lazy(() -> {
        StagingArea s = INDEX.exists() ? StagingArea.fromFile() : new StagingArea();
        s.setTracked(HEADCommit.get().getTracked());
        return s;
    });

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


    /** Creates the first commit with no contents. */
    private static void createInitialCommit() {
        Commit initialCommit = new Commit();
        initialCommit.save();

    }

    /** Check if the command was sent in the right directory. */
    public static void checkWorkingDir() {
        if (!(GITLET_DIR.exists() && GITLET_DIR.isDirectory())) {
            exit("Not in an initialized Gitlet directory.");
        }
    }

    /** Add file to the staging area. */
    public void add(String fileName) {
        File file = getFileFromCWD(fileName);
        if (!file.exists()) {
            exit("File does not exist.");
        }
        if (stagingArea.get().add(file)) { //If there is any change to the staging area.
            stagingArea.get().save();
        }
    }


    /** Get the file from cwd by its name. */
    private static File getFileFromCWD(String fileName) {
        return Paths.get(fileName).isAbsolute() ? new File(fileName): join(CWD, fileName);
    }

    /** Get head commit of the branch. */
    private static Commit getBranchHeadCommit(String branchName) {
        File branchHeadFile = getBranchHeadFile(branchName);
        return getBranchHeadCommit(branchHeadFile);
    }

    /** Get branch head ref file in refs/heads folder. */
    private static File getBranchHeadFile(String branchName) {
        return join(BRANCH_HEADS_DIR, branchName);
    }

    /** Get head commit of the branch. */
    private static Commit getBranchHeadCommit(File branchHeadFile) {
        String HEADCommitId = readContentsAsString(branchHeadFile);
        return Commit.fromFile(HEADCommitId);
    }

}
