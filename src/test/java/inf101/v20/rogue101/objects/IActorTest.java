package inf101.v20.rogue101.objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import inf101.v20.grid.GridDirection;
import inf101.v20.grid.ILocation;
import inf101.v20.rogue101.game.Game;
import inf101.v20.rogue101.game.IllegalMoveException;
import inf101.v20.rogue101.map.MapReader;

/**
 * @author mva021
 *
 */
class IActorTest {

	/**
	 * This method returns several instances of IActors
	 * Most tests are run on each of these instances.
	 * @return
	 */
	public static List<IActor> getInstances(){
		ArrayList<IActor> list = new ArrayList<IActor>();
		list.add(new Rabbit());
		list.add(new Spider());
		Spider weakSpider = new Spider();
		try {
			weakSpider.handleDamage(10);
			list.add(weakSpider);			
		} catch (NullPointerException e) {
			//Spider must have an implementation that more
			//advanced than before ant this test no longer works
		}
				
		return list;
	}

	/**
	 * This method runs a specified test on a list of different IActors
	 * @param test the method to test
	 */
	void runTest(Consumer<IActor> test) {
		for(IActor actor : getInstances())
			test.accept(actor);
	}
	
	@Test
	void testGetAttack() {
		runTest(this::testGetAttack);
	}
	
	/**
	 * Tests that attack is non-negative
	 * @param actor the actor to test
	 */
	void testGetAttack(IActor actor) {
		assertTrue(actor.getAttack()>=0);
	}

	@Test
	void testGetDamage() {
		runTest(this::testGetDamage);
	}

	/**
	 * Tests that getDamage is non-negative
	 * @param actor the actor to test
	 */
	void testGetDamage(IActor actor) {
		assertTrue(actor.getDamage()>=0);
	}

	@Test
	void testDoTurn() {
		runTest(this::testDoTurn);
	}
	/**
	 * Tests that after calling doTurn the actor is in 
	 * one of the locations given by {@link game.Game#getPossibleMoves()}
	 * @param actor
	 */
	void testDoTurn(IActor actor) {
		Game game = new Game(MapReader.BUILTIN_MAP);
		ILocation loc = game.getMap().getLocation(5, 5);
		game.getMap().add(loc, actor);
		game.setCurrent(5,5);
		List<GridDirection> moves = game.getPossibleMoves();
		ArrayList<ILocation> locations = new ArrayList<ILocation>();
		for(GridDirection dir : moves) {
			locations.add(loc.go(dir));
		}
		try {
			actor.doTurn(game);			
		} catch (IllegalMoveException e) {
			fail("You can only move once");
		}
		game.setCurrent(actor);
		assertEquals(actor, game.getActor());
		assertTrue(locations.contains(game.getLocation()));
	}

	@Test
	void testDoTurn_NoMove() {
		testDoTurn_NoMove(Spider.SYMBOL);
		testDoTurn_NoMove(Rabbit.SYMBOL);
	}
	/**
	 * Tests that when put in a situation with no possible moves,
	 * the actor stays at same position.
	 * @param symbol
	 */
	void testDoTurn_NoMove(Character symbol) {
		Game game = new Game(MapReader.mapTrap(symbol));
		ILocation loc = game.getMap().getLocation(1, 1);
		IActor actor = game.setCurrent(loc);
		assertTrue(game.containsActor(loc, actor.getClass()));
		assertNotEquals(null, game.getLocation());
		try {
			actor.doTurn(game);			
		} catch (IllegalMoveException e) {
			fail("You can only move once");
		}
		game.setCurrent(loc);
		assertEquals(loc,game.getLocation());
	}
}
