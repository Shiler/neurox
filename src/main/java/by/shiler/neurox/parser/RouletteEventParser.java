package by.shiler.neurox.parser;

import by.shiler.neurox.event.RouletteEvent;
import by.shiler.neurox.event.game.RouletteGameEvent;
import by.shiler.neurox.event.player.RoulettePlayerEvent;
import by.shiler.neurox.event.result.RouletteResultEvent;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class RouletteEventParser {

    private static final Gson gson = new Gson();

    public static RouletteEvent parse(String msg) {
        StringBuilder sb = new StringBuilder(msg);
        stub(sb);
        switch (selectType(sb)) {
            case "game": {
                return parseGameEvent(sb);
            }
            case "result": {
                return parseResultEvent(sb);
            }
            case "player": {
                return parsePlayerEvent(sb);
            }
            default:
                return null;
        }
    }

    private static String selectType(StringBuilder stringBuilder) {
        int index = stringBuilder.indexOf(",");
        int index1 = stringBuilder.indexOf(":");
        String type = stringBuilder.substring(index1 + 1, index - 1);
        stringBuilder.delete(0, index+1);
        return type;
    }

    /**
     * Cleanups malformed json message
     * e.g. 42[{"message":"msg"}] to {"message":"msg"}
     *
     * @param stringBuilder
     */
    private static void stub(StringBuilder stringBuilder) {
        if (stringBuilder.substring(0, 3).equals("42[")) {
            stringBuilder.delete(0, 3);
        }
        int length = stringBuilder.length();
        if (stringBuilder.charAt(length - 1) == ']') {
            stringBuilder.deleteCharAt(length - 1);
        }
    }

    private static RouletteGameEvent parseGameEvent(StringBuilder stringBuilder) {
        JsonElement jsonElement = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject().get("preGameObj");
        return gson.fromJson(jsonElement, RouletteGameEvent.class);
    }

    private static RouletteResultEvent parseResultEvent(StringBuilder stringBuilder) {
        JsonElement jsonElement = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject().get("gameObj");
        return gson.fromJson(jsonElement, RouletteResultEvent.class);
    }

    private static RoulettePlayerEvent parsePlayerEvent(StringBuilder stringBuilder) {
        return gson.fromJson(stringBuilder.toString(), RoulettePlayerEvent.class);
    }

}
