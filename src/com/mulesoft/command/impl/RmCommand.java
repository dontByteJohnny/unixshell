package com.mulesoft.command.impl;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;

public class RmCommand implements ICommand {
    private Command rmTerminalCommand;
    private String toDelete = "";

    public RmCommand(Command newRmCommand, String toDelete) {
        this.rmTerminalCommand = newRmCommand;
        this.toDelete = toDelete;
    }

    @Override
    public void execute() {
        rmTerminalCommand.rm(toDelete);
    }
}
