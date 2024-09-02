package Init;

import Execute.*;
import Handlers.HandlerMap;

import static Init.Commands.query;

public class ProcessCommands {

    HandlerMap handlerMap = new HandlerMap();

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
        String output = "";
        return switch (qry) {
            case "CREATE" -> {
                Create create = new Create();
                yield create.handle();
            }
            case "UPDATE" -> {
                Update update = new Update();
                yield update.handle();
            }
            case "SELECT" -> {
                Select select = new Select();
                yield select.handle();
            }
            case "DROP" -> {
                Drop drop = new Drop();
                yield drop.handle();
            }
            case "INSERT" -> {
                Insert insert = new Insert();
                yield insert.handle();
            }
            default -> output;
        };
        // Here you would implement your command handling logic

    }

}


