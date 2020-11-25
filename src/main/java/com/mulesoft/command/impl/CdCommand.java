package com.mulesoft.command.impl;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;

public class CdCommand implements ICommand {
    private String path = "/home/user/";

    private Command cdTerminalCommand;

    public CdCommand(Command newCdCommand, String actualPath) {
        this.cdTerminalCommand = newCdCommand;
        this.path = actualPath.isEmpty() ? path : actualPath;
    }

    @Override
    public void execute() {
        cdTerminalCommand.cd(path);
    }
}
