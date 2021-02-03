package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> list1 = new AListNoResizing();
        BuggyAList<Integer> list2 = new BuggyAList<>();

        list1.addLast(4);
        list2.addLast(4);

        list1.addLast(5);
        list2.addLast(5);

        list1.addLast(6);
        list2.addLast(6);

        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

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
                int lastM = broken.getLast();
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
