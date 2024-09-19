package Init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadDB {

    public static HashMap<String, HashMap<String, String> > mainMap = new HashMap<>();
    // Column 1 index (column1 value -> list of primary keys)
    public static HashMap<String, HashMap<String, List<String>>> col1Index = new HashMap<>();

    // Column 2 index (column2 value -> list of primary keys)
    public static HashMap<String, HashMap<String, List<String>>> col2Index = new HashMap<>();

    // Column 3 index (column3 value -> list of primary keys)
    public static HashMap<String, HashMap<String, List<String>>> col3Index = new HashMap<>();

    // Key of (outer)hashmap -- table name ..... this table name = file name
    // value of (outer)hashmap -- contents of the table

    // key of (value of Inner hashmap) -- row number of the table
    // value of(value of Inner hashmap) -- values of row strored in a string

    // These contents of Hasmap will be copied from the text file of each table

    public LoadDB(){

        String path = "C:\\Users\\Jitesh\\IdeaProjects\\SQLITE\\src\\main\\java";
        String directory = "DB";
        File theDir = new File("/"+path+"/"+directory);

        try {

            boolean directoryCreated = theDir.mkdir();
            if (directoryCreated) {
                System.out.println("A new DB Directory created");
                LoadFiles();
            }
            else{
                System.out.println("Directory not created......the DB directory already exists");
            }
        }
        catch (SecurityException ignored) {
            System.out.println("Security Exception");
        }
    }
    public void CopyMap(String tableName){
         HashMap<String, String> mainTable = new HashMap<>();
         HashMap<String, List<String>> col1Table = new HashMap<>();
         HashMap<String, List<String>> col2Table = new HashMap<>();
         HashMap<String, List<String>> col3Table = new HashMap<>();
         // Key of tempMap = primary key for the table, int value incremented by one for each row in table
        //....
        // Load the contents of this txt file byte wise and copy it to tempMap

        // After copying it to tempMap , move the map to the outer map declared above
        String filePath = "C:\\Users\\Jitesh\\IdeaProjects\\SQLITE\\src\\main\\java\\DB" + tableName;
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
//                String col1Value = vals[1];
//                col1Table.computeIfAbsent(col1Value, k -> new ArrayList<>()).add(primaryKey);
//
//                // Update column 2 index (for queries on col2)
//                String col2Value = vals[2];
//                col2Table.computeIfAbsent(col2Value, k -> new ArrayList<>()).add(primaryKey);
//
//                // Update column 3 index (for queries on col3)
//                String col3Value = vals[3];
//                col3Table.computeIfAbsent(col3Value, k -> new ArrayList<>()).add(primaryKey);
                // Check if the row contains the search value
            }

            // The contenets have been copied to the map
            mainMap.put(tableName, mainTable);
            col1Index.put(tableName, col1Table);
            col2Index.put(tableName, col2Table);
            col3Index.put(tableName, col3Table);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void LoadFiles(){
        String myPath = "C:\\Users\\Jitesh\\IdeaProjects\\SQLITE\\src\\main\\java";
        String directory = "DB";
        Path directoryPath = Paths.get("/"+myPath+"/"+directory);

        try {
            Files.walk(directoryPath)
                    .forEach(path -> {
                        if (Files.isRegularFile(path)) {
                            System.out.println("File: " + path.getFileName());
                            CopyMap(String.valueOf(path.getFileName()));
                        } else if (Files.isDirectory(path)) {
                            System.out.println("Directory: " + path.getFileName());
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
