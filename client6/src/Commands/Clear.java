package Commands;

import Classes.User;
import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class Clear extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public Clear (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public Clear() {}

    @Override
    protected void execute(String[] args, User user) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Опаньки! Что-то пошло не так");
        }
        commandReciever.clear(user);
    }

    @Override
    protected void writeInfo() {
        System.out.println("clear: очистить коллекцию");
    }
}
