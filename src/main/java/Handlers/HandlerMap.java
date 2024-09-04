package Handlers;
import Execute.*;

import java.io.IOException;
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

    public String executeCommand(String qry, String command) throws IOException {
        CommandHandler handler = handlers.get(qry);
        if (handler != null) {
            return handler.handle(command);
        } else {
            System.out.println("Command not recognized: " + command);
        }
        return null;
    }
}
