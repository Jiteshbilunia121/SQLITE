package Execute;


import Handlers.CommandHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static Init.LoadDB.*;


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

        // For updating fetch the map and from the loadDB class
        // Delete the old map
        // insert the new map in place of the old map in mainMap, col1index, col2index, col3index

        HashMap<String, String> mainTable = mainMap.get(tableName);
        HashMap<String, List<String>> col1Table = col1Index.get(tableName);
        HashMap<String, List<String>> col2Table = col2Index.get(tableName);
        HashMap<String, List<String>> col3Table = col3Index.get(tableName);
        mainMap.remove(tableName);
        col1Index.remove(tableName);
        col2Index.remove(tableName);
        col2Index.remove(tableName);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] vals = line.split(" ");
                String primaryKey = vals[0];
                mainTable.put(primaryKey, line);
                int i = 0;
                for(String colValues : vals){
                    if(i == 0){
                        col1Table.computeIfAbsent(colValues, k -> new ArrayList<>()).add(primaryKey);
                    }
                    else if(i == 1){
                        col2Table.computeIfAbsent(colValues, k -> new ArrayList<>()).add(primaryKey);
                    }
                    else if(i == 2){
                        col3Table.computeIfAbsent(colValues, k -> new ArrayList<>()).add(primaryKey);
                    }
                    i++;
                }
            }
            mainMap.put(tableName, mainTable);
            col1Index.put(tableName, col1Table);
            col2Index.put(tableName, col2Table);
            col3Index.put(tableName, col3Table);
            // The contenets have been copied to the map
        } catch (IOException e) {
            System.out.println("Exception thrown" + e.getMessage());
            e.printStackTrace();
        }
    }
}
