package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomTest(){
        StudentArrayDeque<Integer> s = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> a = new ArrayDequeSolution<>();
        String str = "\n";

        while(true) {
            int opt = StdRandom.uniform(0, 4);
            if (opt == 0) {
                int num = StdRandom.uniform(0, 100);
                s.addFirst(num);
                a.addFirst(num);
                str += "addFirst(" + num + ")\n";
            } else if (opt == 1) {
                int num = StdRandom.uniform(0, 100);
                s.addLast(num);
                a.addLast(num);
                str += "addLast(" + num + ")\n";
            }

            if (s.isEmpty()) {
               continue;
            }

            if (opt == 2) {
                str += "removeFirst()\n";
                assertEquals(str, a.removeFirst(), s.removeFirst());
            } else if (opt == 3) {
                str += "removeLast()\n";
                assertEquals(str, a.removeLast(), s.removeLast());
            }

        }
    }
}
