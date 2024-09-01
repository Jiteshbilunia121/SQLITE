package Init;
import java.util.Scanner;

import static Init.ProcessCommands.processCommand;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to your own SQLITE!");
        try {

            System.out.print("Enter Yes to connect to your DB");
            boolean running = true;
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine().trim();

            if (Commands.conn.contains(cmd)) {
                System.out.println("Connected to your DB.......");
                while(running){
                    System.out.println("DB>>");

                    cmd = scanner.nextLine();
                    if (cmd.equalsIgnoreCase("EXIT")) {
                        running = false;
                        System.out.println("Exiting the DB. Goodbye!");
                    }
                    else {
                        // Process the command
                        String output = processCommand(cmd);
                        System.out.println(output);
                        if(output.equals(cmd)){
                            System.out.println("Please enter a valid command..");
                        }
                    }
                }
                scanner.close();

                // After this user should be able to connect to the your program.......
            }
            System.exit(0);

        }
        catch(Exception exception) {
            System.out.println("Please enter a valid key to connect to your DB"+exception.getMessage() );
        }
        // create a list of valid command

    }
}