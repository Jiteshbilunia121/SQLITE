package Init;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class LoadDB {

    public HashMap<String, HashMap<Integer, String> > map = new HashMap<>();
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
    public void CopyMap(String fileName){
         HashMap<Integer, String> tempMap = new HashMap<>();
         // Key of tempMap = primary key for the table, int value incremented by one for each row in table
         //....
        // Load the contents of this txt file byte wise and copy it to tempMap

        // After copying it to tempMap , move the map to the map declared above


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
