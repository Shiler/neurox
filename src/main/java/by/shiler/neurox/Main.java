package by.shiler.neurox;

import by.shiler.neurox.command.CommandContainer;
import by.shiler.neurox.command.CommandManager;
import by.shiler.neurox.controller.Controller;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Type 'menu' to view available operations.");
        Controller controller = new Controller();
        CommandContainer commandContainer;
        do {
            commandContainer = controller.read();
            controller.process(commandContainer);
        } while (!CommandManager.isTerminatingCommand(commandContainer.getCommand()));
    }

}
