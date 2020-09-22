package Commands;

import Classes.User;
import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class Register extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public Register (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public Register() {
    }

    @Override
    protected void writeInfo() {
        System.out.println("register <login> <password>: регистрация нового пользователя");
    }

    @Override
    protected void execute(String[] args, User user) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 3) {
            System.out.println("Что-то пошло не так (недостаточно аргументов).\nregister <login> <password>");
        }
        commandReciever.register(new User(args[1], args[2]));
    }
}
