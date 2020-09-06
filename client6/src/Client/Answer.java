package Client;

import Commands.SerializedMessage;

import java.io.IOException;

public class Answer {

    static void toAnswer(Object o) throws IOException {
        if (o instanceof SerializedMessage) {
            SerializedMessage serializedMessage = (SerializedMessage) o;
            System.out.println(((SerializedMessage) o).getMessage());
        }
    }
}
