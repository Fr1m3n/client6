package Commands;

import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class Help extends Command {
    private CommandReciever commandReciever;

    public Help (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public Help() {}

    @Override
    protected void execute(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Опаньки! Что-то пошло не так");
        }
        commandReciever.help();
    }

    @Override
    protected void writeInfo() {
        System.out.println("help: вывести справку по доступным командам");
    }
}
