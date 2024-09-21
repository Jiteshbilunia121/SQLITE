package Execute;

import Handlers.CommandHandler;
import static Init.LoadDB.*;

import java.lang.reflect.Array;
import java.util.*;

public class Select implements CommandHandler {


    public List<String> queryMultipleColumns(ArrayList<HashMap<String, List<String>>> colTable, HashMap<String, String> mainTable, String[] args) {
        // Get primary keys for col1 and col2 ...col3values
        // Put the liSt in a set
        // Find intersection of all the sets(using retailALl)
        // Query over final set and add fetch the values from the mainTable
        //   col1KeySet.retainAll(col2KeySet);

        ArrayList<List<String>> keys = new ArrayList<>();
        ArrayList<Set<String>> colKeySet = new ArrayList<>();
        int idx = 3;
        for(HashMap<String, List<String>> map : colTable) {
                keys.add(map.getOrDefault(args[idx], new ArrayList<>()));
                idx++;
        }
        for(List<String> key : keys) {
            colKeySet.add(new HashSet<>(key));
        }
        Set<String> intersection = colKeySet.get(0);
        for(int i = 1; i < colKeySet.size(); i++) {
            intersection.retainAll(colKeySet.get(i));
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
        String table_name = args[2];
        HashMap<String, String> mainTable = mainMap.get(table_name);
        ArrayList<HashMap<String, List<String>>> colTable = new ArrayList<>();
        for(int cols = 3, i = 0; cols < args.length && i < 3; cols++, i++) {
            HashMap<String, List<String>> mp = new HashMap<>();
            if (i == 0) {
                    mp = col1Index.get(table_name);
            } else if (i == 1) {
                mp = col2Index.get(table_name);

            } else if (i == 2) {
                mp = col3Index.get(table_name);
            }
            colTable.add(mp);
        }
        List<String> results;
        results = queryMultipleColumns(colTable, mainTable, args);
        for(String result : results) {
            System.out.println(result);
        }

        return "Query Handled successfully";
    }
}
