package Execute;

import Handlers.CommandHandler;

import java.io.*;

public class Create implements CommandHandler {

    @Override
    public String handle(String command) throws IOException {

        String[] args = command.split(" ");
        String table_name = args[2];
        String myPath = "C:\\Users\\Jitesh\\IdeaProjects\\SQLITE\\src\\main\\java\\DB"+table_name;
        File file = new File(myPath);

        if(file.createNewFile()){
            System.out.println("File created for your table");
        }
        createTable(args, myPath);

        return "Successfully created";
    }


    public void createTable(String[] rowData, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true for appending data
            StringBuilder row = new StringBuilder();
            for (int i = 3; i < rowData.length; i++) {
                row.append(rowData[i]);
                if (i < rowData.length - 1) {
                    row.append(", ");  // Separating values by commas
                }
            }
            writer.write(row.toString());
            writer.newLine();  // Move to the next line after writing the row
        } catch (IOException e) {
            System.out.println("Exception in creating the table "+e.getMessage());
        }
    }

}
