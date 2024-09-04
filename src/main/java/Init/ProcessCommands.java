package Init;

import Execute.*;
import Handlers.HandlerMap;

import static Init.Commands.query;

public class ProcessCommands {

    static HandlerMap handlerMap = new HandlerMap();

    public static String processCommand(String command) {

        String qry = "";
        for(String s: query){
            if(command.toUpperCase().startsWith(s)){
                qry = s;
                break;
            }
        }
        if(qry.isEmpty()){
            return command;
        }
//        String output = "";
        return handlerMap.executeCommand(qry, command);
        // Here you would implement your command handling logic

    }

}


