package com.company;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            ConsoleManager consoleManager = new ConsoleManager();
            consoleManager.InteractiveMode(args[0], args[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Некорректное количество аргументов: необходимы адрес, порт");
        }
    }
}