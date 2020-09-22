package com.company;

import Classes.User;

import java.io.IOException;

public class DescengingHeight extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReciever commandReciever;

    public DescengingHeight (CommandReciever commandReciever) {
        this.commandReciever = commandReciever;
    }

    public DescengingHeight() {}

    @Override
    protected void execute(String[] args, User user) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Опаньки! Что-то пошло не так");
        }
        commandReciever.descendingHeight(user);
    }

    @Override
    protected void writeInfo() {
        System.out.println("print_field_descending_field: вывести значения height в порядке возрастания");
    }
}

