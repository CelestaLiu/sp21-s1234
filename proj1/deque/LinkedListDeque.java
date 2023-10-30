package deque;

/** An LinkedListDeque is a list of items,
 * which hides the complicated truth within. */
public class LinkedListDeque<T>{
    private class Node{
        public T item;
        public Node next;

        public Node(T i, Node n){
            item = i;
            next = n;
        }
    }


    public LinkedListDeque(){

    }

    public LinkedListDeque(T x){

    }

    public void addFirst(T x){

    }

    public void addLast(T x){

    }

    public T getFirst(T x){
        return x;
    }

    public T getLast(T x){
        return x;
    }

    public T removeFirst(T x){
        return x;
    }

    public T removeLast(T x) {
        return x;
    }

    public int size(){
        return 0;
    }

    public T getRecursive(int index){

    }

}
