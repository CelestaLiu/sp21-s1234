package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    private T[] items = (T[]) new Object[8];

    private class DequeIterator implements Iterator<T> {
        private int pos;

        DequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }

    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private void resize(int newCapacity) {
        T[] t = (T[]) new Object[newCapacity];
        int realFirst = (nextFirst + 1) % capacity;
        int realLast = (nextLast - 1 + capacity) % capacity;

        int newNextFirst = newCapacity / 2;

        if (realFirst <= realLast) {
            //System.arraycopy(items, realFirst, t, newNextFirst, size);
            for (int i = 0; i < size; i++) {
                t[newNextFirst++] = items[realFirst++];
                if (newNextFirst == newCapacity) {
                    newNextFirst = 0;
                }
                if (realFirst == capacity) {
                    realFirst = 0;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                t[newNextFirst++] = items[realFirst++];
                if (newNextFirst == newCapacity) {
                    newNextFirst = 0;
                }
                if (realFirst == capacity) {
                    realFirst = 0;
                }
            }
        }

        items = t;
        capacity = newCapacity;
        nextLast = newNextFirst;
        nextFirst = (newCapacity / 2 + capacity - 1) % capacity;
    }

    public ArrayDeque() {
        size = 0;
        nextFirst = 3;
        nextLast = 4;
        capacity = 8;
    }

    @Override
    public void addFirst(T item) {
        size++;
        items[nextFirst] = item;
        nextFirst = (nextFirst + capacity - 1) % capacity;
        if (size == capacity) {
            resize(size * 2);
        }
    }

    @Override
    public void addLast(T item) {
        size++;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        if (size == capacity) {
            resize(size * 2);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        nextFirst = (nextFirst + 1) % capacity;
        T firstItem = items[nextFirst];
        items[nextFirst] = null;
        if ((size * 2 < capacity) && (size != 8)) {
            resize(capacity / 2);
        }
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        nextLast = (nextLast + capacity - 1) % capacity;
        T lastItem = items[nextLast];
        items[nextLast] = null;
        if ((size * 2 < capacity) && (size != 8)) {
            resize(capacity / 2);
        }
        return lastItem;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            return null;
        }
        int pos = (nextFirst + 1 + index) % capacity;
        return items[pos];
    }

    /*
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque) {
            if (((ArrayDeque<?>) o).size != size) {
                return false;
            }
            if (((ArrayDeque<?>) o).capacity != capacity) {
                return false;
            }
            if (((ArrayDeque<?>) o).nextFirst != nextFirst) {
                return false;
            }
            if (((ArrayDeque<?>) o).nextLast != nextLast) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (get(i) != ((ArrayDeque<?>) o).get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

     */

    public boolean equals(Object o){
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (! (o instanceof Deque)) {
            return false;
        }

        if (isEmpty() != ((Deque<?>) o).isEmpty()) {
            return false;
        }

        if (size() != ((Deque<?>) o).size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (get(i) != ((Deque<?>) o).get(i)) {
                return false;
            }
        }

        return true;
    }

}
