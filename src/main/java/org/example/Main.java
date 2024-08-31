package org.example;
import org.example.Commands;

import javax.lang.model.type.NullType;
import java.util.Scanner;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to your own SQLITE!");
        try {

            System.out.print("Enter Yes/yes to connect to your DB");
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();
            if (Commands.conn.contains(cmd)) {
                System.out.println("Connected to your DB");

                // After this user should be able to connect to the your program.......
            }

        }
        catch(Exception exception) {
            System.out.println("Please enter a valid key to connect to your DB"+exception.getMessage() );
        }
        // create a list of valid command

    }
}