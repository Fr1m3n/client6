package Commands;

import com.company.Command;
import com.company.CommandReciever;

import java.io.IOException;

public class ExcecuteScript extends Command {
    private CommandReciever commandReciever;
    private static String path;

    public ExcecuteScript (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public ExcecuteScript() {}

    @Override
    protected void execute(String[] args) throws StackOverflowError, IOException {
        try {
            if (args.length == 2) { path = args[1]; commandReciever.ExcecuteScript(args[1]); }
            else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
        } catch (StackOverflowError ex) {
            System.out.println("Ошибка! Обнаружен выход за пределы стека");
        }
    }
    @Override
    protected void writeInfo() {
        System.out.println("excecute_script: считать и исполнить скрипт");
    }

    public static String getPath() {
        return path;
    }
}
