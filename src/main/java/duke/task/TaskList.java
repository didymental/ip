package duke.task;

import duke.exception.DukeIncorrectInputs;
import duke.exception.DukeNoSuchTask;

import java.util.ArrayList;

/**
 * Represents a Task List that stores the log of
 * tasks in the users' to-do list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    private TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Returns the total number of tasks in the list in String.
     */
    public String getTotal() {
        int total = this.listOfTasks.size();
        return total + (total == 1 ? " task" : " tasks");
    }

    /**
     * Returns the total number of tasks in the list in String.
     */
    public int getTotalNumber() {
        return this.listOfTasks.size();
    }

    /**
     * Add a task into the list of tasks.
     *
     * @param task the task to be added into the list of tasks.
     */
    public void addTaskToList(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Create a TaskList object.
     *
     * @return a new TaskList that has no tasks stored.
     */
    public static TaskList makeNewTaskList() {
        return new TaskList();
    }

    /**
     * Sets the status of the specified task as 'done'.
     * @param i Index of the task in the task list, that
     *          is to be set as 'done'.
     */
    public void setTaskAsDone(int i) throws DukeIncorrectInputs {
        if (i < 1 || i > this.listOfTasks.size()) {
            throw new DukeNoSuchTask(new IllegalArgumentException());
        }
        this.listOfTasks.get(i - 1).markAsDone();
    }

    /**
     * Returns the task at the specified position.
     * @param i Index of the task in the task list, that
     *          is to be returned.
     */
    public Task getTask(int i) {
        return this.listOfTasks.get(i);
    }

    public Task deleteTask(int i) throws DukeIncorrectInputs {
        if (i < 1 || i > this.listOfTasks.size()) {
            throw new DukeNoSuchTask(new IllegalArgumentException());
        }
        return this.listOfTasks.remove(i - 1);
    }

    /**
     * String representation of the TaskList object.
     */
    @Override
    public String toString() {
        if (this.listOfTasks.size() == 0) { // if there is no task within list
            return "\tYou have not added any tasks to your list.\n"
                    + "\tLog any task you wish to add.\n";
        }
        int i = 1;
        String toPrint = "";

        for (Task task : this.listOfTasks) {
            toPrint = toPrint + "\t" + i + ". " + task + "\n";
            i++;
        }
        return toPrint;
    }
}
