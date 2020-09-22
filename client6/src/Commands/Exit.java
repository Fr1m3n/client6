package Commands;

import Classes.User;
import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class Exit extends Command {
    private CommandReciever commandReciever;

    public Exit (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public Exit() {}

    @Override
    protected void execute(String[] args, User user) throws IOException {
        if (args.length > 1) {
            System.out.println("Опаньки! Что-то пошло не так");
        }
        commandReciever.exit();
    }

    @Override
    protected void writeInfo() {
        System.out.println("exit: завершить без сохранения");
    }
}
