package com.mulesoft.command.impl;

import com.mulesoft.command.Command;
import com.mulesoft.command.ICommand;

public class HelpCommand implements ICommand {
    private Command helpTerminalCommand;

    public HelpCommand(Command newHelpCommand) {
        this.helpTerminalCommand = newHelpCommand;
    }

    @Override
    public void execute() {
        helpTerminalCommand.help();
    }
}
