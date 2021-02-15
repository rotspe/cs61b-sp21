package deque;

import java.util.Iterator;

public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);

    static boolean equals(Object o1, Object o2) {
        if (!(o1 instanceof Deque) || !(o2 instanceof Deque)) {
            return false;
        }

        Deque d1 = (Deque) o1;
        Deque d2 = (Deque) o2;

        if (d1.size() != d2.size()) {
            return false;
        }

        for (int i = 0; i < d1.size(); ++i) {
            if (!d1.get(i).equals(d2.get(i))) {
                return false;
            }
        }

        return true;
    }

}
