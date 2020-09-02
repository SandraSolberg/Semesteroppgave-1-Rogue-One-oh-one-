package inf101.v20.rogue101.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inf101.v20.gfx.gfxmode.Direction;
import inf101.v20.gfx.textmode.Printer;
import inf101.v20.grid.Grid;
import inf101.v20.grid.GridDirection;
import inf101.v20.grid.ILocation;
import inf101.v20.rogue101.RogueApplication;
import inf101.v20.rogue101.game.Game;
import inf101.v20.rogue101.game.IGame;
import inf101.v20.rogue101.map.GameMap;
import inf101.v20.rogue101.map.IGameMap;
import javafx.scene.paint.Color;

/**
 * Kaniner i spillet Rogue 101. 
 * Kaniner hopper rundt i labyrinten på jakt etter gulrøtter. 
 * De har god luktesans og kan lukte gulrøtter på lang avstand. 
 * 
 * De forbrenner energi raskt og må være effektiv 
 * 
 * @author Knut Anders Stokke
 *
 */
public class Rabbit implements IActor {
	/**
	 * char representation of this type 
	 */
	public static final char SYMBOL = 'R';
	private int hp = getMaxHealth();

	@Override
	public void doTurn(IGame game) {
		if (getMaxHealth()-getCurrentHealth()>2 && eatIfPossible(game))
			return;

		//random movement
		//performMove(game);

		//delvis smartere
		//collectCarrot(game);

		//tørr ikke bruke denne. Testene ville ikke ferdig.
		smartereRabbit(game);
	}



    /**
     *
     *
     * Metode findCarrot
     * Bruker metoder i IGame
     *
     * Bruker getPossibleMoves() for å sjekke GridDirections fra current
     * Ser om den har en Iitem.Carrot
     *
     **/


	/**
	 * Burns one round of energy
	 */
	private void burnEnergy() {
		hp--;
	}


	/**
	 * Smartere Rabbit 5.1
	 * Bruker game.getReachable() for å få en sortert-liste med mulige posisjoner fra location
	 * går gjennom listen og sjekker om det ligger en gulerot i nærheten
	 * @param game
	 */

	/**
	 *Bruker getReachable for å sjekke om kaninen kan gå carrot
	 * i nærheten fra sin current posisjon. Regner så ut vinkel som bestemmer hvilke vei den skal gå
	 */

	private void smartereRabbit(IGame game) {

		ILocation bunnyLoc = game.getLocation();

		game.getReachable().contains(Carrot.class);
		//rabbit har possisjon
		for (ILocation l : game.getReachable()) {

			if (game.containsItem(l, Carrot.class)) {
				GridDirection dir = goToCarrot(bunnyLoc, l);
				game.move(dir);
				burnEnergy();

			}
			if(!game.getReachable().contains(Carrot.class)){
				performMove(game);
			}
			}
		}

	/**
	 *Hjelpe-metode for smartere kanin.
	 * Sjekker differansen (deltaX, deltaY) mellom current og source
	 * T
	 */
	public GridDirection goToCarrot(ILocation current, ILocation source){

		if(current.getX() > source.getX()){
			if(current.getY() < source.getY()){
				return GridDirection.SOUTHWEST;
			}
			else if(current.getY() > source.getY()){
				return GridDirection.NORTHWEST;
			}
			else{
				return GridDirection.WEST;
			}
		}
		else if(current.getX() < current.getY()){
			if(current.getY() < source.getY()){
				return GridDirection.SOUTHEAST;
			}
			else if(current.getY() > source.getY()){
				return GridDirection.NORTHEAST;
			}
			else{
				return GridDirection.EAST;
			}

		}else{
			if(current.getY() > source.getY()){
				return GridDirection.NORTH;
			}
			else if(current.getY() < source.getX()){
				return GridDirection.SOUTH;
			} else{
				return GridDirection.CENTER;
			}
		}


		}

	/**
	 * Dette er den litt smartere som sjekker naboer, og går dit om de er de nærmeste
	 * Ellers tar et random move.
	 *
	 * Note til meg selv: må sette diagonalene til å ikke brukes.
	 */
	private void collectCarrot(IGame game){

		List<GridDirection> possibleMoves = game.getPossibleMoves();
		int d;
		for(d = 0; d < possibleMoves.size(); d++) {
			ILocation location = game.getLocation(possibleMoves.get(d));

			if (game.containsItem(location, Carrot.class) ) {
                burnEnergy();
                game.move(possibleMoves.get(d));
                System.out.println("Yum");
            }
		}
		if(d >= possibleMoves.size()){
		    performMove(game);
        }

		}


	/**
	 * Denne metoden må beholdes slik at jeg ikke finner på noe tull
	 * @param game
	 */
	private void performMove(IGame game) {
		List<GridDirection> possibleMoves = game.getPossibleMoves();
		if (!possibleMoves.isEmpty()) {
					burnEnergy();
					Collections.shuffle(possibleMoves);
					game.move(possibleMoves.get(0));
				}

		}


	/**
	 * Eats carrot if any exist at current location
	 * 
	 * @param game
	 *            The game the object exists in
	 * @return true if it spend the turn eating
	 */
	private boolean eatIfPossible(IGame game) {
		for (IItem item : game.getLocalNonActorItems()) {
			if (item instanceof Carrot) {
				System.out.println("found carrot!");
				int eaten = item.handleDamage(Math.min(5, getMaxHealth()-getCurrentHealth()));
				if (eaten > 0) {
					System.out.println("ate carrot worth " + eaten + "!");
					hp += eaten;
					game.displayMessage("You hear a faint crunching (" + getLongName() + " eats " + item.getArticle()
							+ " " + item.getLongName() + ")");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getAttack() {
		return 5;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDamage() {
		return 8;
	}

	@Override
	public int getDefence() {
		return 2;
	}

	@Override
	public int getMaxHealth() {
		return 100;
	}

	@Override
	public String getShortName() {
		return getLongName();
	}

	public String getLongName() {
		return "rabbit";
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public String getGraphicTextSymbol() {
		if (useEmoji()) {
			return hp > 0 ? Printer.coloured("🐰", Color.LIGHTPINK) : "💀"; // 🐇
		} else {
			return hp > 0 ? "" + SYMBOL : "¤";
		}
	}
	
	private boolean useEmoji() {
		return true;
	}

	@Override
	public int handleDamage(int amount) {
		amount -= Math.min(amount, getDefence());
		int damage = Math.min(amount, hp);
		hp -= damage;
		return damage;
	}
	
	@Override
	public char getSymbol() {
		return SYMBOL;
	}
}
