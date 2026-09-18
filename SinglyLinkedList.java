import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size < 2){
            return;
        }

        // Collect the existing nodes in list order.
        @SuppressWarnings("unchecked")
        Node<E>[] nodes = new Node[size];
        Node<E> current = head;
        for (int i = 0; i < size; i++){
            nodes[i] = current;
            current = current.getNext();
        }

        // order[k] = index (in list order) of the node holding the k-th smallest element.
        Integer[] order = new Integer[size];
        for (int i = 0; i < size; i++){
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> nodes[a].getElement().compareTo(nodes[b].getElement()));

        // rank[i] = how many elements are smaller than the element at list position i.
        int[] rank = new int[size];
        for (int k = 0; k < size; k++){
            rank[order[k]] = k;
        }

        // The element at position i is replaced by its mirror in sorted order,
        // so the node holding that mirror value must be moved to position i.
        @SuppressWarnings("unchecked")
        Node<E>[] swapped = new Node[size];
        for (int i = 0; i < size; i++){
            swapped[i] = nodes[order[size - 1 - rank[i]]];
        }

        // Relink the nodes into their new sequence.
        for (int i = 0; i < size - 1; i++){
            swapped[i].setNext(swapped[i + 1]);
        }
        swapped[size - 1].setNext(null);
        head = swapped[0];
        tail = swapped[size - 1];
    }
   
}
