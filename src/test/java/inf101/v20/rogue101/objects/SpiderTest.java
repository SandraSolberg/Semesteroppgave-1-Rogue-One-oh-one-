package inf101.v20.rogue101.objects;

import inf101.v20.rogue101.game.Game;
import inf101.v20.rogue101.map.MapReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpiderTest {

    @Test
    void ConstructorTest() {
        Spider spider = new Spider();
        assertEquals(spider.getMaxHealth(), spider.getCurrentHealth());
    }

    @Test
    void SpiderKillsPlayer() {
        Game game = new Game(MapReader.playerTrapWith('S'));
        IPlayer player = (IPlayer) game.setCurrent(2, 2);

        for (int i = 0; i < 1000; i++) game.doTurn();

        assertEquals(-1, player.getCurrentHealth());
    }

    @Test
    void SpiderHasShield() {
        Game game = new Game(MapReader.mapTrap('S'));
        Spider spider = (Spider) game.setCurrent(1, 1);
        assertEquals(10, spider.getCurrentHealth());
        spider.handleDamage(5);
        assertEquals(6, spider.getCurrentHealth());
    }
}
