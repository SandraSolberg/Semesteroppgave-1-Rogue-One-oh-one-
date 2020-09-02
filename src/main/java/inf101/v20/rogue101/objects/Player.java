package inf101.v20.rogue101.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import inf101.v20.grid.GridDirection;
import inf101.v20.rogue101.RogueApplication;
import inf101.v20.rogue101.game.IGame;
import inf101.v20.rogue101.game.PlayerBag;
import javafx.scene.input.KeyCode;

/**
 * En spiller i Rogue 101
 * <p>
 * Spilleren navigerer labyrinten og slåss med monster. For å komme seg ut
 * og vinne spille må spilleren gå gjennom portalen; for å åpne portalen
 * må den finne amuletten og bære den med seg.
 * <p>
 * På veien kan den plukke opp gull og slåss med monster
 *
 * @author Anna Eilertsen - anna.eilertsen@uib.no
 */
public class Player implements IPlayer {
    /**
     * char representation of this type
     */
    public static final char SYMBOL = '@';
    private static final int MAXHEALTH = 100;
    private int attack;
    private int defence;
    private int damage;
    private int hp;
    private String name;

    //bag for å holde på objekter
    PlayerBag<IItem> bag = new PlayerBag<>();


    public Player() {
        attack = 10;
        defence = 2;
        damage = 3;
        hp = Player.MAXHEALTH;
        name = System.getProperty("user.name");
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getCurrentHealth() {
        return hp;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public int getMaxHealth() {
        return Player.MAXHEALTH;
    }

    @Override
    public String getShortName() {
        return getLongName();
    }

    @Override
    public String getLongName() {
        return name;
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public String getGraphicTextSymbol() {
        if (useEmoji()) {
            return hp > 0 ? "👸" : "⚱️"; // 🤴  ⚰️
        } else {
            return "" + SYMBOL;
        }
    }

    private boolean useEmoji() {
        return true;
    }

    @Override
    public int handleDamage(int amount) {
        amount = Math.max(0, amount - defence);
        amount = Math.min(hp + 1, amount);
        hp -= amount;
        return amount;
    }

    @Override
    public void keyPressed(IGame game, KeyCode key) {
        System.err.println("Player moves");
        switch (key) {
            case LEFT:
                tryToMove(game, GridDirection.WEST);
                break;
            case RIGHT:
                tryToMove(game, GridDirection.EAST);
                break;
            case UP:
                tryToMove(game, GridDirection.NORTH);
                break;
            case DOWN:
                tryToMove(game, GridDirection.SOUTH);
                break;
            case P:
                pickUp(game);
                break;
            case D:
                drop(game);
                break;
            default:
                System.err.printf("Illegal key pressed. Key: %s", key);
        }
        showStatus(game);
    }

    private void tryToMove(IGame game, GridDirection dir) {
        if (game.canGo(dir)) {
            game.displayDebug("Moving.");
            game.move(dir);
        } else {
            if (game.attack(dir))
                game.displayDebug("Victory!");
            else
                game.displayDebug("Ouch! Can't go there.");
        }
    }

    public void showStatus(IGame game) {

        game.displayMessage("Player has " + this.hp + " hp left" + " Iitems in bag : " + display());
    }


    /**
     * Pick-up method, item is put in playerBag
     * Add items to bag
     * If it is a health-heart element it droppes player drops it as soon as it uses it
     * Every other item picked-up is added normaly, only hearts can be used. If player has maxHealth
     * the heart is kept and could be used again by dropping item and pick-it up again
     *
     */
    public void pickUp(IGame game) {
        int oldhp = getCurrentHealth();
        IItem item = game.getLocalNonActorItems().get(0);
        if (game.getLocalNonActorItems().isEmpty() || item.getSize()>this.getSize()){
            game.displayMessage("Could not pick-up, Item is to big for your bag");
            return;
        }
        if(game.containsItem(game.getLocation(), HealthHeart.class)) {

            game.displayMessage("You got a Health heart, gives you 10 HP");
            if (getCurrentHealth() < 90) {
                hp += 10;
                game.displayMessage("Health heart used");
            } else if (getCurrentHealth() > 90 && getCurrentHealth() < 100) {
                hp = getMaxHealth();
                game.displayMessage("HP is full again");

            }
        }
        bag.add(item);
        game.pickUp(item);
        System.out.println("Player picked-up item " + item.getShortName());
        if(item instanceof HealthHeart && oldhp < getMaxHealth()){
            bag.remove(bag.size()-1);
        }

    }
    /**
     * drop-up method, removes first item picked-up from bag
     *
     */
    public void drop(IGame game) {
        if (!bag.isEmpty()) {
            game.drop(bag.remove(0));

        }
    }


    @Override
    public void doTurn(IGame game) {
    }

    @Override
    public boolean isDestroyed() {
        return false; //Even when dead, the player should remain on the map
    }

    /**
     *
     * @param item
     * @return boolean true if item is in bag
     */
    @Override
    public boolean hasItem(IItem item) {
        if (!bag.isEmpty() && bag.contains(item)) {
            return true;
        }
        return false;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }

    /**
     * Display Iitems i listen and returns a string with items
     * Uses java StringBuilder to display in a good manner
     */
    public String display() {
        StringBuilder str = new StringBuilder();

        String s = "";

        for (int i = 0; i < bag.size(); i++) {
            IItem item = bag.get(i);


            s += (item.getShortName() + str.append(","));
            s += (str.deleteCharAt(str.length() - 1).toString());


        }
        return s;
    }
}

