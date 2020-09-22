package Commands;

import Classes.User;
import com.company.Command;

import java.io.Serializable;

public class SerializedSimplyCommand implements Serializable {

    private Command command;
    private User user;

    public SerializedSimplyCommand(Command command, User user) {
        this.command = command;
        this.user = user;
    }

    public Command getCommand() {
        return command;
    }

    public User getUser() {
        return user;
    }
}
