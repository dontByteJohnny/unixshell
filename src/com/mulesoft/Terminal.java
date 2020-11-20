package com.mulesoft;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;
import com.mulesoft.command.impl.CdCommand;
import com.mulesoft.command.impl.HelpCommand;
import com.mulesoft.command.impl.PwdCommand;

import java.util.HashMap;

// Invoker
public class Terminal {
    private ICommand iCommand;

    private HashMap<String, ICommand> keyCommandMap = new HashMap<>();

    public Terminal(Command iCommand, String path) {
        keyCommandMap.put("pwd", new PwdCommand(iCommand));
        keyCommandMap.put("help", new HelpCommand(iCommand));
        keyCommandMap.put("cd", new CdCommand(iCommand, path));
    }

    public void executeCommand(String userInput) {
        String action = userInput.split(" ")[0];

        ICommand command = keyCommandMap.get(action);
        if (command == null) {
            System.out.println("invalid command");
        } else {
            command.execute();
        }
    }



}