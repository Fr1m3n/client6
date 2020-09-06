package Commands;

import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class Add extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public Add (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public Add() {}

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Что-то пошло не так (введен ненужный элемент)");
        }
        commandReciever.add();
    }

    @Override
    protected void writeInfo() {
        System.out.println("add: добавить новый элемент в коллекцию");
    }
}
