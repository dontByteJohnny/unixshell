package com.mulesoft.command.impl;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;

public class LsCommand implements ICommand {
    private String recursiveString = "";

    private Command lsTerminalCommand;

    public LsCommand(Command newLsCommand, String recursive) {
        this.lsTerminalCommand = newLsCommand;
        this.recursiveString = recursive;
    }

    @Override
    public void execute() {
        if ("-r".equals(recursiveString))
            lsTerminalCommand.lsRecursive();
        else if(recursiveString.isEmpty())
            lsTerminalCommand.ls();
        else
            System.out.println("Invalid command");
    }

}
