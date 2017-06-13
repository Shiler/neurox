package by.shiler.neurox.command.impl;

import by.shiler.neurox.command.ICommand;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */
public class ErrorCommand implements ICommand {

    public enum Type {
        UNDEFINED_COMMAND, INVALID_ARGUMENT
    }

    private Type type;

    public ErrorCommand(Type type) {
        this.type = type;
    }

    @Override
    public void execute() {
        String out;
        switch (type) {
            case UNDEFINED_COMMAND: {
                out = "Undefined command!";
            } break;
            case INVALID_ARGUMENT: {
                out = "Invalid argument!";
            } default: out = "Undefined error!";
        }
        System.out.println(out);
        System.exit(1);
    }

}
