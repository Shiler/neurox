package by.shiler.neurox.command.impl;

import by.shiler.neurox.command.ICommand;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class FailureCommand implements ICommand {

    private String failureMessage;

    public FailureCommand(String message) {
        this.failureMessage = message;
    }

    @Override
    public String execute(String params) {
        return this.failureMessage;
    }

}
