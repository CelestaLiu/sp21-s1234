package deque;

import org.junit.Test;

import java.util.ListIterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {


    @Test
    /** Adds a few things to the array, checking isEmpty() and size() are correct,
     * finally printing the results.
      */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        assertTrue(a1.isEmpty());

        a1.addFirst("front");
        assertEquals(1, a1.size());
        assertFalse(a1.isEmpty());

        a1.addLast("middle");
        assertEquals(2, a1.size());

        a1.addLast("back");
        assertEquals(3, a1.size());

        System.out.println("Printing out deque: ");
        a1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        assertTrue(a1.isEmpty());

        a1.addFirst(1);
        assertFalse(a1.isEmpty());

        a1.removeFirst();
        assertTrue(a1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        a1.addFirst(3);

        a1.removeLast();
        a1.removeFirst();
        a1.removeLast();
        a1.removeFirst();

        int size = a1.size();
        assertEquals(0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        ArrayDeque<Double> a2 = new ArrayDeque<>();
        ArrayDeque<Boolean> a3 = new ArrayDeque<>();

        a1.addFirst("string");
        a2.addFirst(3.14159);
        a3.addFirst(true);

        String s = a1.removeFirst();
        double d = a2.removeFirst();
        boolean b = a3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> a1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals(null, a1.removeFirst());
        assertEquals(null, a1.removeLast());


    }

    @Test
    /* Add and remove elements to see if the array can resize in a reasonable way. */
    public void resizeTest(){
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            a1.addFirst(i);
        }
        for (int i = 0; i < 8; i++) {
            a1.addLast(i);
        }
        for (int i = 0; i < 8; i++) {
            a1.removeFirst();
        }
        for (int i = 0; i < 8; i++) {
            a1.removeLast();
        }
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> a1 = new ArrayDeque<>();

        for (int i = 0; i < 1000000; i++) {
            a1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals(i, (double) a1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals(i, (double) a1.removeLast(), 0.0);
        }

    }

    /* Try to get certain items from a deque using get() or getRecursive(). */
    @Test
    public void getItemTest(){
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        a1.addLast(1);
        a1.addLast(2);

        int num = a1.get(0);
        assertEquals(1, num);

        num = a1.get(1);
        assertEquals(2, num);

    }

    @Test
    public void equalsTest(){
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        ArrayDeque<Integer> a2 = new ArrayDeque<>();

        assertEquals(true, a1.equals(a2));

        a1.addFirst(1);
        a2.addFirst(1);
        assertEquals(true, a2.equals(a1));

        a2.removeFirst();
        assertEquals(false, a2.equals(a1));

        a2.addFirst(2);
        a1.addLast(2);
        a2.addLast(1);
        assertEquals(false, a1.equals(a2));
    }

    @Test
    public void EqualTestWithVaryDeques(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        LinkedListDeque<Integer> l = new LinkedListDeque<>();

        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);

        a1.addFirst(1);

        l.addFirst(1);
        l.addFirst(2);
        l.addFirst(3);


        assertEquals(false, a.equals(a1));
    }

}
