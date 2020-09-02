package inf101.v20.rogue101;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import inf101.v20.grid.ILocation;
import inf101.v20.rogue101.game.Game;
import inf101.v20.rogue101.map.MapReader;
import inf101.v20.rogue101.objects.Carrot;
import inf101.v20.rogue101.objects.Rabbit;

@TestInstance(Lifecycle.PER_CLASS)
class TestRabbitStrategy {

	double movesAvg = 0;
	int NUMBER_OF_SIMULATIONS = 10;

	/**
	 * This test sets up a game with lots of Carrots and 1 rabbit The game runs as
	 * long as the Rabbit has more energy. The object of the rabbit is to
	 * survive as many moves as possible.
	 * 
	 * @throws Exception
	 */
	@BeforeAll
	void setUp() throws Exception {
		movesAvg = 0;
		for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++) {
			runSimulation();
		}		
		movesAvg = movesAvg / NUMBER_OF_SIMULATIONS;
	}

	private void runSimulation() {
		Game game = new Game(MapReader.CARROT_HUNT);
		game.doTurn();
		Rabbit rabbit = (Rabbit) game.getActor();
		ILocation previous = game.getLocation();
		int carrots = 0;
		while (rabbit.getCurrentHealth() > 0) {
			game.doTurn();
			if (game.getActor() == rabbit) {
				if (game.containsItem(Carrot.class)) {
					carrots++;
				}
				if (!game.getLocation().equals(previous)) {
					movesAvg++;
				}

			} else {
				System.err.println("Strange, how has other actors entered the game?");
				fail("Not allowed to reproduce");
			}
		}
	}

	@Test
	void level1() {
		assertTrue(movesAvg > 10);
	}

	@Test
	void level2() {
		assertTrue(movesAvg > 20);
	}

	@Test
	void level3() {
		assertTrue(movesAvg > 30);
	}

	@Test
	void level4() {
		assertTrue(movesAvg > 40);
	}

	@Test
	void level5() {
		assertTrue(movesAvg > 50);
	}

	@Test
	void level6() {
		assertTrue(movesAvg > 60);
	}

	@Test
	void level7() {
		assertTrue(movesAvg > 70);
	}

	@Test
	void level8() {
		assertTrue(movesAvg > 80);
	}

	@Test
	void level9() {
		assertTrue(movesAvg > 90);
	}

	@Test
	void level10() {
		assertTrue(movesAvg > 100);
	}

	@Test
	void level11() {
		assertTrue(movesAvg > 200);
	}
	@Test
	void level12() {
		assertTrue(movesAvg > 300,"Num moves was "+movesAvg);
	}

}
