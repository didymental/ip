import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Duke chatbot that can add tasks
 * to users' to-do list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Duke {
    private boolean isOpen;
    private TaskList listOfTasks;
    private Storage storage;
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Constructor for Duke chatbot.
     */
    private Duke() {
        this.listOfTasks = TaskList.makeNewTaskList();
        this.storage = new Storage(Duke.FILE_PATH);
    }

    /**
     * Opens and starts the chatbot.
     */
    private void openDukeChatBot() {
        this.isOpen = true;
        Ui.printOpeningMessage();
    }

    /**
     * Closes the chatbot.
     */
    public void closeDukeChatBot() {
        this.isOpen = false;
    }

    /**
     * Takes the task from user input and adds it to the
     * task list.
     * @param item item to be added into the task list.
     * @return item in the type Task.
     */
    public Task addTaskToList(String item) {
        Task task = Task.createTask(item);
        this.listOfTasks.addTaskToList(task);
        return task;
    }

    /**
     * Returns the task list.
     * @return task list.
     */
    public TaskList getTaskList() {
        return this.listOfTasks;
    }

    /**
     * Sets the task at the specified index to be done.
     * @param i index of the task that is done.
     */
    public void setTaskAsDone(int i) {
        this.listOfTasks.setTaskAsDone(i);
    }

    /**
     * Removes task specified by the index from task list.
     * @param i index of the task that is to be removed.
     * @return task that is removed.
     * @throws DukeNoSuchTask
     */
    public Task deleteTask(int i) throws DukeNoSuchTask {
        return this.listOfTasks.deleteTask(i);
    }

    /**
     * Saves the tasks into the storage file.
     * @throws IOException if there is an error in writing the file.
     */
    public void saveTasks() throws IOException {
        this.storage.saveTasks(this.listOfTasks);
    }

    /**
     * Loads tasks from the storage file into the given Duke chatbot.
     * @throws IOException if there is an error in reading the file.
     * @throws DukeUnableLoadTask if there is a corruption in tasks of the file.
     */
    public void loadSavedTasks() throws IOException, DukeUnableLoadTask {
        this.storage.loadSavedTasks(this);
    }


    /**
     * Runs the chatbot Duke.
     * @param args user inputs that will turn into commands.
     */
    public static void main(String[] args) {
        Duke d = new Duke();
        d.openDukeChatBot();
        Scanner sc = new Scanner(System.in);
        File output = new File(d.FILE_PATH);
        if (!output.isFile()) {
            output.getParentFile().mkdirs(); // if user does not have existing file path
            try {
                output.createNewFile();
            } catch (IOException e) {
                Ui.printErrorMessage(e);
            }
        }

        try {
            d.loadSavedTasks();
        } catch (IOException | DukeUnableLoadTask e) {
            Ui.printErrorMessage(e);
        }

        while (d.isOpen) {
            try {
                String userInput = sc.nextLine().strip();
                Command toExecute = Parser.parse(userInput, d, sc);
                toExecute.execute(d.listOfTasks);
            } catch (DukeException | IOException e) {
                Ui.printErrorMessage(e);
            }
        }
    }
}

