package gitlet;

import static gitlet.MyUtils.exit;
import static gitlet.Repository.*;


/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author yaoyao
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        /* If the args is empty. */
        if (args.length == 0) {
            exit("Please enter a command.");
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                checkNumOfArgs(args, 1);
                Repository.init();
                break;
            case "add":
                Repository.checkWorkingDir();
                checkNumOfArgs(args, 2);
                String fileName = args[1];
                new Repository().add(fileName);
                break;
            case "commit":
                Repository.checkWorkingDir();
                checkNumOfArgs(args, 2);
                String commitMessage = args[1];
                if (commitMessage.length() == 0) {
                    exit("Please enter a commit message.");
                }
                new Repository().commit(commitMessage);
                break;
            case "rm":
                Repository.checkWorkingDir();
                checkNumOfArgs(args, 2);
                String rmMessage = args[1];
                new Repository().rm(rmMessage);
                break;
            case "log":
                Repository.checkWorkingDir();
                checkNumOfArgs(args, 1);
                new Repository().log();
                break;
            case "global-log":
                Repository.checkWorkingDir();
                checkNumOfArgs(args, 1);
                Repository.global_log();
                break;
        }
    }


    private static void checkNumOfArgs(String[] args, int n) {
        if (args.length != n) {
            exit("Incorrect operands.");
        }
    }
}
