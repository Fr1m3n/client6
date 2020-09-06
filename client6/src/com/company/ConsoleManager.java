package com.company;

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
        } catch (InterruptedException ex){
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


        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }

        session.closeSession();
    }
}


