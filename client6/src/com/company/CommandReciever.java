package com.company;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import Classes.SpaceMarine;
import Client.ClientSender;
import Client.Receiver;
import Commands.*;

/**
* «Логика» имеющихся команд
 */

import static com.company.CollectionManager.getCollection;


public class CommandReciever {
    private final CommandInvoker commandInvoker;
    private final ClientSender sender;
    private final SocketChannel socketChannel;
    private final Integer delay;
    private final Creator creator;

    public CommandReciever(CommandInvoker commandInvoker, ClientSender sender, SocketChannel socketChannel, Integer delay, Creator creator) {
        this.commandInvoker = commandInvoker;
        this.sender = sender;
        this.socketChannel = socketChannel;
        this.delay = delay;
        this.creator = creator;
    }

    public void help() {
        commandInvoker.getCommandMap().forEach((name,command) -> command.writeInfo());
    }

    public void info() throws IOException, ClassNotFoundException, InterruptedException {
        sender.toSend(new SerializedSimplyCommand(new Info()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void show() throws IOException, ClassNotFoundException, InterruptedException {
        sender.toSend(new SerializedSimplyCommand(new Show()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void add() throws IOException, InterruptedException, ClassNotFoundException {
        sender.toSend(new SerializedObjectCommand(new Add(), Creator.SpaceMarineCreator()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    /**
     *
     * @param ID - удаление по ID.
     */
    public void removeById(String ID) throws IOException, InterruptedException, ClassNotFoundException {
        sender.toSend(new SerializedArgumentCommand(new RemoveByID(), ID));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void clear() throws IOException, InterruptedException, ClassNotFoundException {
        sender.toSend(new SerializedSimplyCommand(new Clear()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void exit() throws IOException {
        socketChannel.close();
        System.out.println("Конец");
        System.exit(0);
    }

    /**
     * @param path путь до файла
     */

    public void ExcecuteScript(String path) {
        String line;
        String command;
        ArrayList<String> field = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)),StandardCharsets.UTF_8))){
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update")) {
                    command = line;
                    for (int i = 0; i < 13; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            field.add(line);
                        } else {
                            System.out.println("Не хватает параметров для создания объекта.");
                            break;
                        }
                    }
                    SpaceMarine spaceMarine = Creator.ScriptFromJsonToCollection(field);
                    switch (command.split(" ")[0]) {
                        case "add":
                            sender.toSend(new SerializedObjectCommand(new Add(), spaceMarine));
                            Thread.sleep(delay);
                            Receiver.receive(socketChannel);
                            break;
                        case "update":
                            sender.toSend(new SerializedCombinedCommand(new Update(), creator.SpaceMarineCreator(), command.split(" ")[1]));
                            Thread.sleep(delay);
                            Receiver.receive(socketChannel);
                            break;
                    }
                } else if (line.split(" ")[0].equals("execute_script") && line.split(" ")[1].equals(ExcecuteScript.getPath()))
                {
                    System.out.println("Упс, рекурсия");
                } else {
                    commandInvoker.executeCommand(line.split(" "));
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    /*
    SIMPLY COMMAND?
     */

    public void removeLast() throws IOException, InterruptedException, ClassNotFoundException {
        sender.toSend(new SerializedSimplyCommand(new removeLast()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void descendingHeight() throws IOException, InterruptedException, ClassNotFoundException {
        sender.toSend(new SerializedSimplyCommand(new DescendingHeight()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void ascendingHeight() throws IOException, InterruptedException, ClassNotFoundException {
        sender.toSend(new SerializedSimplyCommand(new AscendingHeight()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

}