package com.company;

import Classes.User;
import Client.ClientSender;
import Client.Session;
import Commands.*;

import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.Scanner;

/**
 * Управление и регистрация
 */

class ConsoleManager {
    private String login;
    private String password;

    void InteractiveMode(String hostName, String port) throws IOException {
        Session session = null;
        int delay = 0;

        try {
            session = new Session(hostName, Integer.parseInt(port));
            session.startSession();

            if (delay < 80) delay = 80;  // Минимальная задержка 80
        } catch (NumberFormatException ex) {
            System.out.println("Проверьте, чтобы имя было текстом, а порт и задержка целыми числами");
            System.exit(0);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        ClientSender sender = new ClientSender(session);

        Creator creator = new Creator();
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReciever commandReciever = new CommandReciever(commandInvoker, sender, session.getSocketChannel(), delay, creator);

        commandInvoker.register("help", new Help(commandReciever));
        commandInvoker.register("add", new Add(commandReciever));
        commandInvoker.register("info", new Info(commandReciever));
        commandInvoker.register("show", new Show(commandReciever));
        commandInvoker.register("remove_by_id", new RemoveByID(commandReciever));
        commandInvoker.register("clear", new Clear(commandReciever));
        commandInvoker.register("exit", new Exit(commandReciever));
        commandInvoker.register("execute_script", new ExcecuteScript(commandReciever));
        commandInvoker.register("print_field_descending_height", new DescengingHeight(commandReciever));
        commandInvoker.register("print_field_ascending_height", new AscendingHeight(commandReciever));
        commandInvoker.register("remove_last", new removeLast(commandReciever));
        commandInvoker.register("register", new Register(commandReciever));


        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String[] userInput = scanner.nextLine().trim().split(" ");
                if (userInput.length == 3 && "login".equals(userInput[0])) {
                    login(userInput[1], userInput[2]);
                    System.out.println("Вы вошли под логином: " + userInput[1]);
                    continue;
                }
                commandInvoker.executeCommand(userInput, new User(login, password));
            }
        } finally {
            session.closeSession();
        }
    }

    private void login(String login, String password) {
        this.login = login;
        this.password = password;
    }
}


