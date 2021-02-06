package deque;

public class ArrayDeque<T> {

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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = capacity - 1;
        } else {
            --nextFirst;
        }

        ++size;
    }

    public void addLast(T item) {
        items[nextLast] = item;
        if (nextLast == capacity - 1) {
            nextLast = 0;
        } else {
            ++nextLast;
        }

        ++size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int first;
        if (nextFirst == capacity - 1) {
            first = 0;
        } else {
            first = nextFirst + 1;
        }

        ++nextFirst;
        --size;

        Object firstItem = items[first];
        return (T) firstItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int last;
        if (nextLast == 0) {
            last = capacity - 1;
        } else {
            last = nextLast - 1;
        }

        --nextLast;
        --size;

        Object lastItem = items[last];
        return (T) lastItem;
    }

    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(items[nextFirst + 1 + i]);
            if (i != size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int first;
        if (nextFirst == capacity - 1) {
            first = 0;
        } else {
            first = nextFirst + 1;
        }

        return (T) items[first + index];
    }
}
