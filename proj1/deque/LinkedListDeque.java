package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private static class Node<T> {
        T item;
        Node prev;
        Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node<T>(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        Node<T> first = new Node<>(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.prev = first;
        this.sentinel.next = first;

        ++this.size;
    }

    @Override
    public void addLast(T item) {
        Node<T> last = new Node<>(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = last;
        this.sentinel.prev = last;

        ++this.size;
    }

    @Override
    public T removeFirst() {
        if (this.sentinel.next == sentinel) {
            return null;
        }

        Node<T> first = this.sentinel.next;

        this.sentinel.next = first.next;
        first.next.prev = this.sentinel;

        --this.size;

        return first.item;
    }

    @Override
    public T removeLast() {
        if (this.sentinel.prev == sentinel) {
            return null;
        }

        Node<T> last = this.sentinel.prev;

        this.sentinel.prev = last.prev;
        last.prev.next = this.sentinel;

        --this.size;

        return last.item;
    }

    @Override
    public void printDeque() {
        Node<T> p = this.sentinel.next;
        while (p != this.sentinel) {
            System.out.print(p.item);
            if (p.next != this.sentinel) {
                System.out.print(" ");
            }

            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int i = 0;
        Node<T> p = this.sentinel.next;
        while (p != this.sentinel) {
            if (i == index) {
                return p.item;
            }

            ++i;
            p = p.next;
        }

        return null;
    }

    private class LinkedListDequeIterator<T> implements Iterator<T> {

        private int index;
        private Node<T> current;

        public LinkedListDequeIterator() {
            index = 0;
            current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T next = (T) current.item;
            current = current.next;
            ++index;
            return next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator<>();
    }
}
