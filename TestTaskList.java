
// Saikarthik Mummadisingu
// Section B
// I pledge my honor that I have abided by the Stevens Honor System

import java.util.Scanner;

public class TestTaskList<E> {
    //tasklist attribute and scanner variable
    private TaskList<E> toDoList = new TaskList<>();
    private Scanner scan;

    // static method that calls the printMenu method from it
    public static void main(String[] args) {
        TestTaskList n = new TestTaskList();
        n.printMenu();
    }

    /**
     * Takes a number from the user which indicates the operation user chooses.
     * From this method, we call the helper method processMenuItem() and keep running
     * till user hits 8 to break the code
     */
    public void printMenu(){
        scan = new Scanner(System.in);
        String a = "~~~ TO-DO List Program, created by truly yours ~~~\n";
        String C1 = "==> Currently there are NO items in the To-Do List\n";
        String DNC =  "To add a new task without priority information, press 1.\n" +
            "To add a new task with a priority information, press 2.\n" +
            "To cross off the task at the top of the list, press 3.\n" +
            "To cross off a certain task in the list, press 4.\n" +
            "To see the top 3 highest priority tasks, press 5.\n" +
            "To see the completed tasks, press 6.\n" +
            "To see the all tasks that has been completed or still active, press 7.\n" +
            "To quit the program, press 8.";
        System.out.println(a + C1+ DNC);
        int ans = 0;
        while (ans != 8) {
            // checks if input is an int datatype
            if (scan.hasNextInt()) {
                ans = scan.nextInt();
                if (ans < 1 || ans  > 8 || ans == 0) {
                    System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
                    if (toDoList.getActive().getSize() == 0){System.out.println(C1);}
                    System.out.println(DNC);
                }
                else {
                    //breaks from the code
                    if (ans == 8){break;}
                    processMenuItem(ans);
                    if (toDoList.getActive().getSize() == 0){System.out.println(C1);}
                    System.out.println(DNC);
                }
            }
            //prints an error if it isnt an int data type
            else {
                System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
                if (toDoList.getActive().getSize() == 0){System.out.println(C1);}
                System.out.println(DNC);
                scan.nextLine();
            }
        }
    }

    /**
     * helper method takes one input from the user has given
     * it calls the appropriate functions for each operation from the toDoList object
     *
     * @param menuItem
     * @return true/false
     */
    private boolean processMenuItem(int menuItem){
        scan = new Scanner(System.in);
        String xd = " Current TO-DO List:\n" +
                "-------------------";
        if (menuItem == 1){
            System.out.println("Please enter the task description:");
            String answ = scan.nextLine();
            boolean t = toDoList.createTask((E) answ);
            if (t){
                System.out.println("Successfully entered the task to the to-do list!");
                System.out.println(xd);
                toDoList.showActiveTasks();
            }
            else{return false;}
        }
        if (menuItem == 2)
        {
            System.out.println("Please enter the task description");
            String answ = scan.nextLine();
            System.out.println("Please enter a priority number (1 indicates highest priority, increasing " +
                    "numbers show lower priority) :");
            int ans = scan.nextInt();
            if (ans <=0){
                System.out.println("Unsuccessful operation! Please try again!");
                System.out.println(xd);
                toDoList.showActiveTasks();
            }
            else
            {
                boolean t = toDoList.createTask((E)answ, ans);
                if (t){
                System.out.println("Successfully entered the task to the to-do list!");
                System.out.println(xd);
                toDoList.showActiveTasks();
                }
                else{return false;}
            }
        }
        if (menuItem == 3){
            boolean t = toDoList.crossOffMostUrgent();
            if (t){
                System.out.println("Successfully removed the most urgent task/top of the list task!");
                System.out.println(xd);
                toDoList.showActiveTasks();
            }
            else{return false;}
        }
        if (menuItem == 4){
            System.out.println("Please enter the task number you would like to cross off the list :");
            int ans = scan.nextInt();
            if (ans > toDoList.getActive().getSize() || ans < 0){
                System.out.println("Unsuccessful operation! Please try again!");
                System.out.println(xd);
                toDoList.showActiveTasks();
            }
            else{
                boolean t = toDoList.crossOffTask(ans);
                if (t){
                    System.out.println("Successfully removed the task number: " + ans);
                    System.out.println(xd);
                    toDoList.showActiveTasks();
                }
            }
        }
        if (menuItem == 5){
            System.out.println("Top 3 highest priority tasks:\n" +
                    "------------------------------\n" +
                    "Printing Top Three Tasks...");
            toDoList.printTopThreeTasks();
            System.out.println(xd);
            toDoList.showActiveTasks();
        }
        if (menuItem == 6){
            System.out.println("Completed Tasks:\n" +
                    "----------------");
            toDoList.showCompletedTasks();
            System.out.println(xd);
            toDoList.showActiveTasks();
        }
        if (menuItem == 7){
            System.out.println("All of the Tasks - Both completed and active:\n" +
                    "---------------------------------------------");
            toDoList.showAllTasks();
            System.out.println(xd);
            toDoList.showActiveTasks();
        }
        return true;
    }
}
