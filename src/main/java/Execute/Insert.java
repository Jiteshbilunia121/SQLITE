package Execute;


import static Init.LoadDB.map;
import Handlers.CommandHandler;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;


public class Insert implements CommandHandler {

    @Override
    public String handle(String command){

        String[] args = command.split(" ");
        String table_name = args[2];
        String myPath = "C:\\Users\\Jitesh\\IdeaProjects\\SQLITE\\src\\main\\java\\DB"+table_name;
        insertTable(args, myPath, table_name);
        return "Insert Success";
    }
    public String generatePrimaryKey() {
        return UUID.randomUUID().toString();
    }
    private void insertTable(String[] rowData, String filePath, String tableName){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true for appending data
            StringBuilder row = new StringBuilder();
            String primaryKey = generatePrimaryKey();
            row.append(primaryKey);
            for (int i = 3; i < rowData.length; i++) {
                row.append(rowData[i]);
                if (i < rowData.length - 1) {
                    row.append(", ");  // Separating values by commas
                }
            }
            writer.write(row.toString());
            writer.newLine();  // Move to the next line after writing the row
            updateMap(filePath, tableName);
        } catch (IOException e) {
            System.out.println("Exception in creating the table "+e.getMessage());
        }
    }

    private void updateMap(String filePath, String tableName) {

        HashMap<String, String> tempMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] vals = line.split(" ");
                for (String values : vals) {
                    tempMap.put(values, line);
                }
                // Check if the row contains the search value
            }
            map.put(tableName, tempMap);
            // The contenets have been copied to the map
        } catch (IOException e) {
            System.out.println("Exception thrown" + e.getMessage());
            e.printStackTrace();
        }
    }
}
