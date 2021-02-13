package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    @Test
    public void testComparatorFromConstructor() {
        MaxArrayDeque<String> d = new MaxArrayDeque<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null) {
                    return -1;
                }

                if (o2 == null) {
                    return 1;
                }

                return o1.length() - o2.length();
            }
        });
        d.addLast("aaa");
        d.addLast("bb");
        d.addLast("c");

        assertEquals("aaa", d.max());
    }

    @Test
    public void testComparatorPassedToMax() {
        MaxArrayDeque<String> d = new MaxArrayDeque<>(null);
        d.addLast("aaa");
        d.addLast("bb");
        d.addLast("c");

        Comparator<String> minComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null) {
                    return -1;
                }

                if (o2 == null) {
                    return 1;
                }

                return o2.length() - o1.length();
            }
        };

        assertEquals("c", d.max(minComparator));
    }
}
