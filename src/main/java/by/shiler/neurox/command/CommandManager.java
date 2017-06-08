package by.shiler.neurox.command;

import by.shiler.neurox.command.impl.FailureCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class CommandManager {

    private static Map<String, ICommand> commands = new HashMap<>();
    private static Map<String, List<String>> parameters = new HashMap<>();
    private static List<String> terminateCommands = new ArrayList<>();

    static {

    }

    public static boolean isTerminatingCommand(String command) {
        return terminateCommands.contains(command);
    }

    public ICommand defineCommand(String commandString) {
        if (commands.containsKey(commandString)) {
            return commands.get(commandString);
        } else {
            return new FailureCommand(AvailableOperations.INVALID_COMMAND_MESSAGE);
        }
    }

    public static Map<String, ICommand> getCommands() {
        return commands;
    }

    public static Map<String, List<String>> getParameters() {
        return parameters;
    }

}
