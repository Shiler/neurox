package by.shiler.neurox.command;

import by.shiler.neurox.command.impl.ErrorCommand;
import by.shiler.neurox.command.impl.FetchCommand;
import by.shiler.neurox.command.impl.PredictCommand;
import by.shiler.neurox.command.impl.TrainCommand;

/**
 * Created by Evgeny Yushkevich on 09.06.2017.
 */
public class CommandExecutor {

    public static void work(String[] args) {
        switch (args[0]) {
            case "fetch": {
                try {
                    int gamesToFetch = Integer.parseInt(args[1]);
                    new FetchCommand(gamesToFetch).execute();
                } catch (NumberFormatException | NullPointerException e) {
                    new ErrorCommand(ErrorCommand.Type.INVALID_ARGUMENT).execute();
                }
            }
            break;
            case "train": {
                new TrainCommand().execute();
            }
            break;
            case "predict": {
                new PredictCommand().execute();
            }
            default:
                new ErrorCommand(ErrorCommand.Type.UNDEFINED_COMMAND).execute();
        }
    }

}
