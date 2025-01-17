package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void printList() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i).toString());
        }
    }

    public static int getLength() {
        return list.size();
    }

    //Takes ith number task as argument
    public static void mark(int num) {
        list.get(num - 1).toggleTrue();
        Ui.giveOutput("Nice! I've marked this task as done:");
        Ui.giveOutput(list.get(num - 1).toString());
    }

    public static void unmark(int num) {
        list.get(num - 1).toggleFalse();
        Ui.giveOutput("OK, I've marked this task as not done yet:");
        Ui.giveOutput(list.get(num - 1).toString());
    }

    public static void delete(int num) {
        Ui.giveOutput("Noted. I've removed this task:");
        Ui.giveOutput("  " + list.get(num-1).toString());
        list.remove(num-1);
        Ui.giveOutput("Now you have " + list.size() + " tasks in the list.");
    }

    public static void find(String keyword) {
        int index = 0;
        for (Task t : list) {
            String[] sample = t.toString().split(" ");
            List<String> temp = new ArrayList<>(Arrays.asList(sample));
            if (temp.contains(keyword)) {
                index++;
                Ui.giveOutput(index + "." + t.toString());
            }
            if (index == 0) {
                Ui.giveOutput("Could not find the given word");
            }
        }
    }

    public static void add_to_list(String str) throws InvalidCommandException, NoDescriptionException {

        if ((str.split(" ", 2).length == 1)) {
            throw new NoDescriptionException();
        }
        else if ((str.split(" ", 2)[0]).equals("todo")) {

            list.add(new Todo(str));
        }
        else if ((str.split(" ", 2)[0]).equals("deadline")) {
            list.add(new Deadline(str));
        }
        else if ((str.split(" ", 2)[0]).equals("event")) {
            list.add(new Event(str));
        }
        else {
            throw new InvalidCommandException();
        }
        int size = list.size();
        Ui.giveOutput("Got it. I've added this task:");
        Ui.giveOutput("  " + list.get(size-1).toString());
        Ui.giveOutput("Now you have " + size + " tasks in the list");
    }


}

