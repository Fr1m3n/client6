package Commands;

import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class AscendingHeight extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public AscendingHeight (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public AscendingHeight() {}

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Опаньки! Что-то пошло не так");
        }
        commandReciever.ascendingHeight();
    }

    @Override
    protected void writeInfo() {
        System.out.println("print_field_ascending_field: вывести значения height в порядке возрастания");
    }
}
