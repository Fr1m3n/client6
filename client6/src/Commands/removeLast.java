package Commands;

import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class removeLast extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public removeLast (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public removeLast() {}

    @Override
    protected void execute(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Опаньки! Что-то пошло не так");
        }
        commandReciever.removeLast();
    }

    @Override
    protected void writeInfo() {
        System.out.println("remove_last: удалить последний элемент");
    }
}
