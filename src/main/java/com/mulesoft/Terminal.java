package com.mulesoft;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;
import com.mulesoft.command.impl.*;

import java.util.HashMap;

// Invoker
public class Terminal {
    private HashMap<String, ICommand> keyCommandMap = new HashMap<>();

    public Terminal(Command iCommand, String path) {
        keyCommandMap.put("pwd", new PwdCommand(iCommand));
        keyCommandMap.put("help", new HelpCommand(iCommand));
        keyCommandMap.put("cd", new CdCommand(iCommand, path));
        keyCommandMap.put("ls", new LsCommand(iCommand, path));
        keyCommandMap.put("mkdir", new MkdirCommand(iCommand, path));
        keyCommandMap.put("touch", new TouchCommand(iCommand, path));
        keyCommandMap.put("rm", new RmCommand(iCommand, path));
    }

    public void executeCommand(String userInput) {
        String action = userInput.split(" ")[0];

        ICommand command = keyCommandMap.get(action);
        if (command == null) {
            System.out.println("Unrecognized command");
        } else {
            try {
                command.execute();
            } catch(Exception ex) {
                System.out.println("Invalid command");
            }
        }
    }



}