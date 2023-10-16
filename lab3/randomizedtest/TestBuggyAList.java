package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;
import timingtest.SLList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */

public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> a1 = new AListNoResizing<>();
        BuggyAList<Integer> a2 = new BuggyAList<>();

        a1.addLast(4);
        a1.addLast(5);
        a1.addLast(6);
        a2.addLast(4);
        a2.addLast(5);
        a2.addLast(6);

        assertEquals(a1.size(), a2.size());
        assertEquals(a1.removeLast(), a2.removeLast());
        assertEquals(a1.removeLast(), a2.removeLast());
        assertEquals(a1.removeLast(), a2.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> a1 = new AListNoResizing<>();
        BuggyAList<Integer> a2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if(operationNumber == 0){
                int randVal = StdRandom.uniform(0, 100);
                a1.addLast(randVal);
                a2.addLast(randVal);
                assertEquals(a1.size(), a2.size());
                assertEquals(a1.getLast(), a2.getLast());
            } else if(operationNumber == 1){
                assertEquals(a1.size(), a2.size());
                if(a1.size() != 0) assertEquals(a1.getLast(), a2.getLast());
            } else if(operationNumber == 2){
                int size = a1.size();
                if(size == 0) continue;
                assertEquals(a1.size(), a2.size());
                if(a1.size() != 0) assertEquals(a1.getLast(), a2.getLast());
            } else if(operationNumber == 3){
                int size = a1.size();
                if(size == 0) continue;
                assertEquals(a1.size(), a2.size());
                assertEquals(a1.getLast(), a2.getLast());
            }
        }
    }
}


