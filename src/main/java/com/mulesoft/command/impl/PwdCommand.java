package com.mulesoft.command.impl;

import com.mulesoft.command.ICommand;
import com.mulesoft.command.Command;

public class PwdCommand implements ICommand {
    private Command pwdTerminalCommand;

    public PwdCommand(Command newPwdCommand) {
        this.pwdTerminalCommand = newPwdCommand;
    }

    @Override
    public void execute() {
        pwdTerminalCommand.pwd();
    }
}
