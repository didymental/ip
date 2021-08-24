package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the general delete command.
 */
public class DeleteCommand extends Command {
    private final int index;

    /** Constructor for the Delete command.
     *
     * @param duke Duke chatbot that is in use.
     * @param sc Scanner object that is in use.
     * @param index index of the task to be deleted.
     */
    public DeleteCommand(Duke duke, Scanner sc, int index) {
        super(duke, sc);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) throws IOException {
        Task taskRemoved = this.duke.deleteTask(this.index);
        Ui.printDeleteTaskMessage(taskRemoved, taskList.getTotal());
        this.duke.saveTasks();
    }
}
