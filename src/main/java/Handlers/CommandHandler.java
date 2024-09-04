package Handlers;

import java.io.IOException;

public interface CommandHandler {

    String handle(String command) throws IOException;
}
