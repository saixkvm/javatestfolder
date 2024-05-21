

// Saikarthik Mummadisingu
// Section B
// I pledge my honor that I have abided by the Stevens Honor System

import java.util.Iterator;

public class TaskList<E>
{
    //3 listqueue attribues
    // 2 int attributes
    private ListQueue<E> all;
    private ListQueue<E> completed;
    private ListQueue<E> active;
    private int LOW_PRIORITY = Integer.MAX_VALUE;
    int HIGH_PRIORITY = 1;

    /**
     * Constructor that initializes all listqueue attributes
     */
    public TaskList(){
        all = new ListQueue<>();
        completed = new ListQueue<>();
        active = new ListQueue<>();
    }

    /**
     * method that adds the item into active and all queues with default priority as LOW_PRIORITY.
     * @param item
     * @return true/false
     */
    public boolean createTask(E item){
        if(item == null){return false;}
        active.addRear(item);
        all.addRear(item);
        return true;
    }

    /**
     * method that adds a item to active and all listqueue with priority
     * @param item
     * @param priority
     * @return true/false
     */
    public boolean createTask(E item, int priority){
        if (item == null){
            return false;
        }
        active.offer(item, priority);
        all.offer(item, priority);
        return true;
    }
    // getter methods for all listqueue items
    public ListQueue<E> getActive() {
        return active;
    }
    public ListQueue<E> getAll() {
        return all;
    }
    public ListQueue<E> getCompleted() {
        return completed;
    }

    /**
     * method that prints the 3 highest priority tasks
     */
    public void printTopThreeTasks(){
        ListQueue.Node n = active.getFront();
        for (int i = 0; i < 3; i++){
            if (n == null){
                break;
            }
            System.out.println(i+1 + ". " + n.getData());
            n = n.getNext();
        }
    }
    // void methods that print the active tasks
    public void showActiveTasks(){
        printTasks(active);
    }
    public void showAllTasks(){
        printTasks(all);
    }
    public void showCompletedTasks(){
        printTasks(completed);
    }

    /**
     * helper method that takes in a queue and prints out the queue
     * @param queue
     */
    private void printTasks(ListQueue<E> queue){
        Iterator n = queue.iterator();
        int c = 1;
        while(n.hasNext()){
            System.out.println(c + ". " + n.next());
            c++;
        }
    }

    /**
     * method that removes the highest priority task from the list
     * @return true/false
     */
    public boolean crossOffMostUrgent(){
        if (active.getFront() == null){
            System.out.println("Error: List is null");
            return false;
        }
        E d = active.poll();
        System.out.println("Task is completed and removed from the list: " + d.toString());
        completed.addRear(d);
        return true;
    }

    /**
     * method that crosses off the task with the task number passed
     * @param taskNumber
     * @return true/false
     */
    public boolean crossOffTask(int taskNumber){

        if (taskNumber <= 0 || taskNumber > active.getSize()){
            return false;
        }
        Iterator<E> iterator = active.iterator();
        ListQueue.Node<E> n = active.getFront();
        int x = 1;
        while (iterator.hasNext() && x < taskNumber){
            n = n.getNext();
            x++;
        }
        completed.addRear(n.getData());
        active.remove(n);
        System.out.println("Task number " + taskNumber + " is removed: " + n.getData());
        return true;
    }
}
