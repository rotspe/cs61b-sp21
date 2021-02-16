package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private Object[] items;
    private int capacity;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.capacity = 8;
        this.items = new Object[this.capacity];
        this.size = 0;
        this.nextFirst = this.capacity / 2;
        this.nextLast = this.nextFirst + 1;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int newSize) {
        Object[] itemsResized = new Object[newSize];

        int firstIndex = getPos(0);
        int firstIndexResized = (newSize - capacity) / 2;

        int numToCopy = capacity - firstIndex;
        System.arraycopy(items, firstIndex, itemsResized, firstIndexResized, numToCopy);

        int numToCopyRest = capacity - numToCopy;
        System.arraycopy(items, 0, itemsResized, firstIndexResized + numToCopy, numToCopyRest);

        nextFirst = firstIndexResized - 1;
        nextLast = firstIndexResized + capacity;

        capacity = newSize;
        items = itemsResized;
    }

    @Override
    public void addFirst(T item) {
        if (size == capacity) {
            resize(2 * capacity);
        }

        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = capacity - 1;
        } else {
            --nextFirst;
        }

        ++size;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity) {
            resize(2 * capacity);
        }

        items[nextLast] = item;
        if (nextLast == capacity - 1) {
            nextLast = 0;
        } else {
            ++nextLast;
        }

        ++size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int first = getPos(0);
        nextFirst = first;
        --size;

        Object firstItem = items[first];
        items[first] = null;
        return (T) firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int last = getPos(size() - 1);
        nextLast = last;
        --size;

        Object lastItem = items[last];
        items[last] = null;
        return (T) lastItem;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(items[nextFirst + 1 + i]);
            if (i != size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int i = getPos(index);
        return (T) items[i];
    }

    private int getPos(int index) {
        int first;
        if (nextFirst == capacity - 1) {
            first = 0;
        } else {
            first = nextFirst + 1;
        }

        int i;
        if (first + index >= capacity) {
            i = first + index - capacity;
        } else {
            i = first + index;
        }

        return i;
    }

    private class ArrayDequeIterator<T> implements Iterator<T> {

        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T next = (T) get(index);
            ++index;
            return next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator<>();
    }

    @Override
    public boolean equals(Object o) {
        if (!(this instanceof Deque) || !(o instanceof Deque)) {
            return false;
        }

        Deque d1 = (Deque) this;
        Deque d2 = (Deque) o;

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
