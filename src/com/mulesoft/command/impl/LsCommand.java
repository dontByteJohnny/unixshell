package com.mulesoft.command.impl;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;

public class LsCommand implements ICommand {
    private String path = "/home/user/";

    private Command lsTerminalCommand;

    public LsCommand(Command newLsCommand) {
        this.lsTerminalCommand = newLsCommand;
    }

    @Override
    public void execute() {
        lsTerminalCommand.ls();
    }
}
