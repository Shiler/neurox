package by.shiler.neurox.parser;

import by.shiler.neurox.event.game.RouletteGameEvent;
import by.shiler.neurox.event.player.RoulettePlayerEvent;
import by.shiler.neurox.event.result.RouletteResultEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Evgeny Yushkevich on 08.06.2017.
 */
public class RouletteEventParserTest {

    @Test
    public void testParseGameEvent() {
        String msg = "42[\"roulette:game\",{\"preGameObj\":{\"id\":9441532,\"start\":20000}}]";
        RouletteGameEvent expectedRouletteGameEvent = new RouletteGameEvent();
        expectedRouletteGameEvent.setId(9441532);
        expectedRouletteGameEvent.setStart(20000);
        RouletteGameEvent actualRouletteGameEvent = (RouletteGameEvent) RouletteEventParser.parse(msg);
        assertEquals(expectedRouletteGameEvent, actualRouletteGameEvent);
    }

    @Test
    public void testParseResultEvent() {
        String msg = "42[\"roulette:result\",{\"gameObj\":{\"id\":9441533,\"hash\":\"1daaa26204ab2d27915304d1a8d1abdab2ccd2e3db3cf548b7c2038a79a7376f\",\"roll\":6,\"state\":0,\"created_at\":null,\"updated_at\":null}}]";
        RouletteResultEvent expectedRouletteResultEvent = new RouletteResultEvent();
        expectedRouletteResultEvent.setId(9441533);
        expectedRouletteResultEvent.setHash("1daaa26204ab2d27915304d1a8d1abdab2ccd2e3db3cf548b7c2038a79a7376f");
        expectedRouletteResultEvent.setRoll(6);
        expectedRouletteResultEvent.setState(0);
        RouletteResultEvent actualRouletteResultEvent = (RouletteResultEvent) RouletteEventParser.parse(msg);
        assertEquals(expectedRouletteResultEvent, actualRouletteResultEvent);
    }

    @Test
    public void testParsePlayerEvent() {
        String msg = "42[\"roulette:player\",{\"bet\":{\"total\":\"0.05\",\"current\":\"0.05\"},\"user\":{\"id\":425631,\"username\":\"CS.MONEY csgobounty.com\",\"avatar\":\"https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/1c/1c911e810ff0e6633f257097609f0aa23339a148.jpg\"},\"team\":{\"color\":\"green\",\"mode\":3}}]";
        System.out.println(RouletteEventParser.parse(msg));
    }

}
