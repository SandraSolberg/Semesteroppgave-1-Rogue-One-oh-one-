package inf101.v20.rogue101.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import inf101.v20.grid.ILocation;
import inf101.v20.rogue101.game.Game;
import inf101.v20.rogue101.map.MapReader;
import inf101.v20.rogue101.objects.Carrot;
import inf101.v20.rogue101.objects.Rabbit;

/**
 * @author Martin Vatshelle
 *
 */
class RabbitTest {

	@Test
	void testConstructor(){
		Rabbit rabbit = new Rabbit();
		assertEquals(rabbit.getMaxHealth(), rabbit.getCurrentHealth());
	}

	@Test
	void CuteRabbitIsHarmless() {
		Game game = new Game(MapReader.playerTrapWith('R'));
		IPlayer player = (IPlayer) game.setCurrent(2, 2);

		for (int i = 0; i < 1000; i++) game.doTurn();

		assertEquals(100, player.getCurrentHealth());
	}
	
	@Test
	void testDoTurn() {
		Game game = new Game(MapReader.BUILTIN_MAP);
		ILocation loc = game.getMap().getLocation(5, 5);
		Rabbit rabbit = new Rabbit();
		game.getMap().add(loc, rabbit);
		game.setCurrent(5,5);
		game.addItem(rabbit);

		int hp = rabbit.getCurrentHealth();
		ILocation before = game.getLocation();		
		boolean hasCarrot = game.containsItem(Carrot.class);

		game.doTurn();
		ILocation after = game.getLocation();
		
		if(hasCarrot) {
			assertEquals(before, after);
		}else {
			assertNotEquals(before, after);
		}
	}
}
