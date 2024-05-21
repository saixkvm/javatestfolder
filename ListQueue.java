
// Saikarthik Mummadisingu
// Section B
// I pledge my honor that I have abided by the Stevens Honor System
import java.util.Iterator;
import java.util.NoSuchElementException;
public class ListQueue<E>
{
    public static class Node<E>{
        // attributes for Node class
        private E data;
        private Node<E> next;
        private int priority;

        //Node constructor with param dataItem
        public Node(E dataItem){
            data = dataItem;
            priority = 10000;
        }
        //Node constructor with dataItem and priority
        public Node(E dataItem,int priority){
            data = dataItem;
            this.priority = priority;
        }
        //Node constructor with parameters dataitem, next, and priority
        public Node(E dataItem, Node<E> next, int priority){
            data = dataItem;
            this.next = next;
            this.priority = priority;
        }
        //Getter methods for next and data variables
        public E getData(){
            return data;
        }
        public Node<E> getNext(){
            return next;
        }
    }
    private class Iter implements Iterator{
        // Next atttribute for iter class
        private Node<E> next = front;

        // boolean function that returns true when the next node not equal to null
        public boolean hasNext(){
            return next !=null;
        }

        //returns the data stored in the next node and updates the attributes
        public E next(){
            if (next == null){
                throw new NoSuchElementException();}
            E data = next.getData();
            next = next.next;
            return data;
        }
        // remove function that throws an exception
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    //front data field and size data field
    private Node<E> front;
    private int size;

    /**
     * ListQueue constructor that creates an empty single-linked representing the priority queue
     */
    public ListQueue(){
        front = null;
        size = 0;
    }

    /**
     * constructor a one-element single-linked list representing the priority queue
     * @param first
     */
    public ListQueue(Node<E> first){
        front = first;
        size++;
    }
    //getter and setter for size
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    //getter and setter for front
    public Node<E> getFront() {
        return front;
    }
    public void setFront(Node<E> front) {
        this.front = front;
    }
    /**
     * returns the information at the front of the queue
     * @return front.getData()
     */
    public E peek(){
        if (front == null){throw new NoSuchElementException();}
        return front.getData();
    }

    /**
     * method that adds item to queue according to its priority
     * @param item
     * @param priority
     * @return true/false
     */
    public boolean offer(E item , int priority){
        if (item == null){throw new NullPointerException();}
        Node temp= front;
        Node ite = new Node(item,priority);
        if (front == null || priority < front.priority){
            ite.next = front;
            front = ite;
            size++;
            return true;
        }
        while (temp.next != null && priority >= temp.next.priority) {
            temp = temp.next;
        }
        ite.next = temp.next;
        temp.next = ite;
        size++;
        return true;
    }

    /**
     * method that adds an item at the end of the queue
     * @param item
     * @return true/false
     */
    public boolean addRear(E item){
        if (item == null){throw new NullPointerException();}

        Node temp = front;
        Node ite = new Node(item);
        if (front == null){
            front = ite;
            size++;
            return true;
        }
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = ite;
        ite = null;
        size++;
        return true;
    }

    /**
     * method that returns the data at the front of the queue and removes it from the queue
     * @return d data
     */
    public E poll(){
        if (front == null){throw new NullPointerException();}
        E d = front.getData();
        if (size == 1){
            front = null;
        }
        else{
            front = front.next;
        }
        size--;
        return d;
    }

    /**
     * method that removes the node passed from the queue
     * @param toBeRemoved
     * @return true/false
     */
    public boolean remove(Node<E> toBeRemoved){
        if (toBeRemoved == null){return false;}
        Node temp = front;
        if (front.getData().equals(toBeRemoved.getData())){
            front = front.getNext();
            size--;
        }
        while (temp.next !=null){
            if (temp.getNext().getData().equals(toBeRemoved.getData())) {
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * method that returns an instance of iter class
     * @return iter
     */
    public Iterator<E> iterator(){
        Iter i = new Iter();
        return i;

    }
}
