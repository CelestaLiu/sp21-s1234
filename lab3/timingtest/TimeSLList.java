package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {

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
        timeGetLast();
    }

    public static void timeGetLast() {
        int tableLength = 1000;
        AList<Integer> N = new AList<>();
        AList<Double> time = new AList<>();
        AList<Integer> ops = new AList<>();


        SLList<Integer> sl = new SLList<>();
        for (int i = 0; i < 8; i++) {
            if(i != 0) tableLength *= 2;
            N.addLast(tableLength);

            for (int j = 0; j < tableLength; j++) {
                sl.addLast(1);
            }

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < 10000; j++) {
                sl.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            time.addLast(timeInSeconds);
            ops.addLast(10000);
            printTimingTable(N, time, ops);
        }
    }

}
