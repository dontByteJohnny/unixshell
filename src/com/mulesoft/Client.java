package com.mulesoft;

import com.mulesoft.command.Command;

import java.io.IOException;
import java.util.Scanner;

// Client
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("----------------------");
        System.out.println("Unix Terminal Emulator");
        System.out.println("----------------------");
        System.out.println("Use help to see the list of commands availables");
        System.out.println("                        ");
        System.out.println("                        ");

        String userInput = "";

        Command command = new Command();

        do {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner. nextLine();
            if (!userInput.isEmpty()) {
                // 1st part of user input
                String userCommand = userInput.split(" ")[0];
                // 2nd part of user input
                String userPathOrFile = userInput.split(" ").length > 1 ? userInput.split(" ")[1] : "";
                Terminal terminal = new Terminal(command, userPathOrFile);

                terminal.executeCommand(userCommand);
            }
        } while(!userInput.equals("exit") || !userInput.equals("quit"));

        System.out.println("SEE YOU LATER MULE!");
    }
}
