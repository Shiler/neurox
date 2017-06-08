package by.shiler.neurox.controller;

import by.shiler.neurox.command.CommandContainer;
import by.shiler.neurox.command.CommandManager;
import by.shiler.neurox.command.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class Controller {

    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private CommandManager commandManager;

    public Controller() {
        commandManager = new CommandManager();
    }

    public CommandContainer read() {
        CommandContainer commandContainer = new CommandContainer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            input = input.trim();
            int delimiter = input.indexOf(" ");
            String commandString = null;
            String paramString = "";
            if (delimiter > 0) {
                commandString = input.substring(0, delimiter).trim();
                paramString = input.substring(delimiter).trim();
            } else if (!input.isEmpty()) {
                commandString = input;
            }

            commandContainer.setCommand(commandString);
            commandContainer.setParameters(paramString);
        } catch (IOException e) {
            LOG.fatal("Error while reading from console", e);
            throw new RuntimeException(e);
        }

        return commandContainer;
    }

    public void process(CommandContainer commandContainer) {
        ICommand command = commandManager.defineCommand(commandContainer.getCommand());
        print(command.execute(commandContainer.getParameters()));
    }

    private void print(String response) {
        System.out.println(response);
    }

}
