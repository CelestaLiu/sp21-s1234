package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private TNode sentinel;
    private int size;

    private class TNode<T> {
        private TNode prev;
        private T item;
        private TNode next;
        TNode(T i, TNode n1, TNode n2) {
            item = i;
            prev = n1;
            next = n2;
        }
    }

    private class DequeIterator implements Iterator<T> {
        private int pos;

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

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    private T getRecursiveH(int index, TNode<T> t) {
        if (index == 0) {
            return (T) t.item;
        } else {
            return (T) getRecursiveH(index - 1, t.next);
        }
    }

    public T getRecursive(int index) {
        TNode<T> t = sentinel.next;
        return getRecursiveH(index, t);
    }

    @Override
    public void addFirst(T item) {
        TNode<T> newNode = new TNode<>(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        TNode<T> newNode = new TNode<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        TNode<T> t = sentinel.next;

        while (t != sentinel) {
            System.out.print(t.item + " ");
            t = t.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        //if(sentinel.next == sentinel) return null;
        TNode<T> firstNode = sentinel.next;
        TNode<T> nextNode = sentinel.next.next;
        sentinel.next = nextNode;
        nextNode.prev = sentinel;
        size--;
        return firstNode.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        //if(sentinel.next == sentinel) return null;
        TNode<T> lastNode = sentinel.prev;
        TNode<T> nextNode = sentinel.prev.prev;
        sentinel.prev = nextNode;
        nextNode.next = sentinel;
        size--;
        return lastNode.item;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        }

        TNode<T> t = sentinel.next;
        index--;
        while (index != -1) {
            index--;
            t = t.next;
            if (t == sentinel) {
                return null;
            }
        }
        return t.item;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque) {
            TNode<T> t1 = sentinel.next;
            TNode<T> t2 = ((LinkedListDeque<T>) o).sentinel.next;

            if (size() != ((LinkedListDeque<T>) o).size()) {
                return false;
            }

            while (t1 != sentinel) {
                if (!(t1.item.equals(t2.item))) {
                    return false;
                }
                t1 = t1.next;
                t2 = t2.next;
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

        if (isEmpty() == ((Deque<?>) o).isEmpty()) {
            return true;
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
