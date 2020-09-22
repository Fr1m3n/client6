package Commands;

import Classes.User;
import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class RemoveByID extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public RemoveByID(CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public RemoveByID() {
    }

    @Override
    protected void execute(String[] args, User user) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length == 2) {
            commandReciever.removeById(args[1], user);
        } else {
            System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        }
    }


    @Override
    protected void writeInfo() {
        System.out.println("remove_by_id: удалитб элемент по id");
    }
}

