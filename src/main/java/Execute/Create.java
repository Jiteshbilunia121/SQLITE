package Execute;

import Handlers.CommandHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Create implements CommandHandler {

    @Override
    public String handle(String command) throws IOException {

        String[] args = command.split(" ");
        String table_name = args[2];
        String myPath = "C:\\Users\\Jitesh\\IdeaProjects\\SQLITE\\src\\main\\java\\DB"+table_name;
        File file = new File(myPath);
        if(file.createNewFile()){
            System.out.println("Table Created");
        }

        return "";
    }
}
