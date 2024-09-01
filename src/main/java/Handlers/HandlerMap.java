package Handlers;
import java.util.HashMap;
import java.util.Map;

public class HandlerMap {
    private final Map<String, CommandHandler> handlers = new HashMap<>();

    public void registerHandler(String command, CommandHandler handler) {
        handlers.put(command, handler);
    }

    public void executeCommand(String command) {
        CommandHandler handler = handlers.get(command);
        if (handler != null) {
            handler.handle();
        } else {
            System.out.println("Command not recognized: " + command);
        }
    }
}
