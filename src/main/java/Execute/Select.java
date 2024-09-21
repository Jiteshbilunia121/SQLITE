package Execute;

import Handlers.CommandHandler;
import static Init.LoadDB.*;

import java.util.*;

public class Select implements CommandHandler {

    public List<String> queryMultipleColumns(ArrayList<HashMap<String, List<String>>> colTable, HashMap<String, String> mainTable, String[] args) {
        ArrayList<Set<String>> colKeySet = new ArrayList<>();
        int idx = 3; // Start from the 4th argument (after "SELECT from TABLE_NAME")

        // Loop through column tables and corresponding query values
        for (HashMap<String, List<String>> map : colTable) {
            String queryValue = args[idx];

            // Only consider columns where a value is provided
            if (!queryValue.equals("null")) {  // Assuming "null" represents no query value
                List<String> keys = map.getOrDefault(queryValue, new ArrayList<>());
                colKeySet.add(new HashSet<>(keys)); // Add keys to column key set
            }
            idx++;
        }

        // Initialize the intersection set (with a non-empty set, if possible)
        Set<String> intersection = new HashSet<>();
        if (!colKeySet.isEmpty()) {
            intersection = colKeySet.get(0);
            for (int i = 1; i < colKeySet.size(); i++) {
                intersection.retainAll(colKeySet.get(i)); // Retain only common keys
            }
        }

        // Retrieve rows from mainTable for matching primary keys
        List<String> results = new ArrayList<>();
        for (String key : intersection) {
            results.add(mainTable.get(key));
        }

        return results;
    }

    @Override
    public String handle(String command) {
        String[] args = command.split(" ");
        String table_name = args[2];  // Table name is the third argument

        // Get the mainTable and column indexes
        HashMap<String, String> mainTable = mainMap.get(table_name);
        ArrayList<HashMap<String, List<String>>> colTable = new ArrayList<>();

        // Add column indexes based on the command input (up to 3 column values)
        for (int cols = 3, i = 0; cols < args.length && i < 3; cols++, i++) {
            HashMap<String, List<String>> mp = null;

            if (i == 0) {
                mp = col1Index.get(table_name);  // Index for col1
            } else if (i == 1) {
                mp = col2Index.get(table_name);  // Index for col2
            } else if (i == 2) {
                mp = col3Index.get(table_name);  // Index for col3
            }

            if (mp != null) {
                colTable.add(mp);
            }
        }

        // Query for the results based on the column values provided
        List<String> results = queryMultipleColumns(colTable, mainTable, args);
        for (String result : results) {
            System.out.println(result);  // Output each result
        }

        return "Query handled successfully";
    }
}
