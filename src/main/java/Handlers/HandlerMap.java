package Handlers;
import Execute.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class HandlerMap {
    public final Map<String, CommandHandler> handlers = new HashMap<>();


    public HandlerMap(){
        handlers.put("CREATE", new Create());
        handlers.put("DROP", new Drop());
        handlers.put("UPDATE", new Update());
        handlers.put("SELECT", new Select());
        handlers.put("INSERT", new Insert());
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
