package bstmap;

import bstmap.Map61B;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K, V> {
    private BSTNode top;
    private int size;
    private class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> leftChild;
        BSTNode<K, V> rightChild;

        BSTNode(K k, V v) {
            this.key = k;
            this.value = v;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        top = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKey(this.top, key);
    }

    private boolean containsKey(BSTNode node, K key) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo((K) node.key);
        if (cmp > 0) {
            return containsKey(node.rightChild, key);
        } else if (cmp < 0) {
            return containsKey(node.leftChild, key);
        } else {
            return true;
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        BSTNode n = this.top;
        V v = get(top, key);
        return v;
    }

    private V get(BSTNode node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo((K) node.key);
        if (cmp > 0) {
            return (V) get(node.rightChild, (K) key);
        } else if (cmp < 0) {
            return (V) get(node.leftChild, (K) key);
        } else {
            return (V) node.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        this.top = put(this.top, key, value);
        size ++;
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if(node == null) {
            return new BSTNode(key, value);
        }

        int cmp = key.compareTo((K) node.key);
        if (cmp > 0) {
            node.rightChild = put(node.rightChild, key, value);
        } else if (cmp < 0) {
            node.leftChild = put(node.leftChild, key, value);
        } else {
            node.value = value;
        }

        return node;
    }


    /* Prints out the BSTMap in order of increasing Key. */
    public void printInOrder() {
        System.out.println(printInOrder(this.top));
    }

    private String printInOrder(BSTNode node) {
        if(node == null) {
            return "";
        }

        if(node.leftChild != null) {
            return printInOrder(node.leftChild);
        } else if (node.rightChild != null) {
            return node.key + " -> " + printInOrder(node.rightChild);
        } else {
            return (String) node.key;
        }
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}