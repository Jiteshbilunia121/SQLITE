package Init;

import Handlers.HandlerMap;

import static Init.Commands.query;

public class ProcessCommands {



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
        switch (qry){
                case "CREATE TABLE":
                return "Table created successfully (simulation).";
                case "INSERT INTO TABLE":
                    return "Table inserted successfully (simulation).";
                    case "DROP TABLE":
                        return "Table dropped successfully (simulation).";






        }
        // Here you would implement your command handling logic

    }

}


