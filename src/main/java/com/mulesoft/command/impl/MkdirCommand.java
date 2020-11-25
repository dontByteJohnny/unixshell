package com.mulesoft.command.impl;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;

public class MkdirCommand implements ICommand {
    private String path = "/home/user/";

    private Command mkdirTerminalCommand;

    public MkdirCommand(Command newMkdirCommand, String newPath) {
        this.mkdirTerminalCommand = newMkdirCommand;
        this.path = newPath.isEmpty() ? path : newPath;
    }

    @Override
    public void execute() {
        mkdirTerminalCommand.mkdir(path);
    }
}
