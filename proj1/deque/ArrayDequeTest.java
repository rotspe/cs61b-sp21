package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());

        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    public void iterator() {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);

        Iterator<Integer> it = d.iterator();
        assertEquals(1, (long) it.next());
        assertEquals(2, (long) it.next());
        assertEquals(3, (long) it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void resize() {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        d.addLast(0);
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);
        d.addLast(4);
        d.addLast(5);
        d.addLast(6);
        d.addLast(7);
        d.addLast(8);

        assertEquals(0, (long) d.get(0));
        assertEquals(1, (long) d.get(1));
        assertEquals(2, (long) d.get(2));
        assertEquals(3, (long) d.get(3));
        assertEquals(4, (long) d.get(4));
        assertEquals(5, (long) d.get(5));
        assertEquals(6, (long) d.get(6));
        assertEquals(7, (long) d.get(7));
        assertEquals(8, (long) d.get(8));
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        ArrayDeque<Integer> broken = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = correct.size();
                int sizeM = broken.size();
                assertEquals(sizeL, sizeM);
            } else if (operationNumber == 2) {
                // getLast
                if (correct.size() == 0) {
                    continue;
                }

                int lastL = correct.getLast();
                int lastM = broken.get(broken.size() - 1);
                assertEquals(lastL, lastM);
            } else if (operationNumber == 3) {
                // removeLast
                if (correct.size() == 0) {
                    continue;
                }

                int lastL = correct.removeLast();
                int lastM = broken.removeLast();
                assertEquals(lastL, lastM);
            }
        }
    }
}
