package com.company;

import Classes.User;

import java.io.IOException;

public class Info extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public Info (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public Info() {}

    @Override
    protected void execute(String[] args, User user) throws IOException, ClassNotFoundException, InterruptedException  {
        if (args.length > 1) {
            System.out.println("Что-то пошло не так");
        }
        commandReciever.info(user);
    }

    @Override
    protected void writeInfo() {
        System.out.println("info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }
}
