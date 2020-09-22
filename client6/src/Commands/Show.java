package Commands;

import Classes.User;
import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class Show extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public Show (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public Show() {}

    @Override
    protected void execute(String[] args, User user) throws IOException, InterruptedException, ClassNotFoundException {
        if (args.length > 1) {
            System.out.println("Опаньки! Что-то пошло не так");
        }
        commandReciever.show(user);
    }

    @Override
    protected void writeInfo() {
        System.out.println("show: вывести все элементы коллекции в строковом представлении.");
    }
}
