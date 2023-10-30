package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> N = new AList<>();
        AList<Double> time = new AList<>();
        AList<Integer> ops = new AList<>();

        int tableLength = 1000;
        for (int i = 0; i < 8; i++) {
            if(i > 0) tableLength *= 2;
            N.addLast(tableLength);
            ops.addLast(tableLength);

            Stopwatch sw = new Stopwatch();
            AList<Integer> testList = new AList<>();
            for (int j = 0; j < tableLength; j++) {
                testList.addLast(1);
            }
            double timeInSeconds = sw.elapsedTime();

            time.addLast(timeInSeconds);
        }

        printTimingTable(N, time, ops);

        /*
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int testAListLength = 1000;
        for (int p = 0; p < 8; p++) {
            if (p > 0) {
                testAListLength *= 2;
            }
            Ns.addLast(testAListLength);
            AList<Integer> testAList = new AList<>();
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < testAListLength; i++) {
                testAList.addLast(1);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            opCounts.addLast(testAListLength);
        }
        printTimingTable(Ns, times, opCounts);
        */
    }

}
