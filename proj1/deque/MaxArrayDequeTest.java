package deque;

import net.sf.saxon.z.IntComplementSet;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {

    @Test
    public void test1(){
        MaxArrayDeque<Integer> m = new MaxArrayDeque<>(new IntComparator());

        for (int i = 0; i < 5; i++) {
            m.addLast(i);
        }

        assertEquals((Integer) 4, m.max());
    }

    @Test
    public void test2(){
        MaxArrayDeque<String> m = new MaxArrayDeque<>(new StringComparator());

        m.addLast("abc");
        m.addLast("def");

        assertEquals("def", m.max());
        assertEquals("abc", m.max(new StringLengthComparator()));
    }

    public static class IntComparator implements Comparator<Integer>{
        public int compare(Integer i1, Integer i2){
            return i1 - i2;
        }
    }

    public static class StringComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            int s1Length = s1.length();
            int s2Length = s2.length();

            for (int i = 0; i < Math.min(s1Length, s2Length); i++) {
                int c1 = s1.charAt(i);
                int c2 = s2.charAt(i);

                if(c1 != c2) return c1 - c2;
            }

            if(s1Length != s2Length) return s1Length - s2Length;

            return 0;
        }
    }

    public static class StringLengthComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            return s1.length() - s2.length();
        }
    }
}
