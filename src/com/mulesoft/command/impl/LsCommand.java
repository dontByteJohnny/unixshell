package com.mulesoft.command.impl;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;

public class LsCommand implements ICommand {
    private boolean isRecursive = false;

    private Command lsTerminalCommand;

    public LsCommand(Command newLsCommand, String recursive) {
        this.lsTerminalCommand = newLsCommand;
        this.isRecursive = !recursive.isEmpty();
    }

    @Override
    public void execute() {
        if (isRecursive)
            lsTerminalCommand.lsRecursive();
        else
            lsTerminalCommand.ls();
    }

}
