package inf101.v20.rogue101.objects;

import inf101.v20.gfx.textmode.Printer;
import javafx.scene.paint.Color;

/**
 * @author Sandra Solberg - sit006@gmail.com
 *
 */

public class HealthHeart implements IItem {
    /**
     * char representation of this type
     */
    public static final char SYMBOL = 'H';
    private int hp = getMaxHealth();

    @Override
    public int getCurrentHealth() {
        return hp;
    }

    @Override
    public int getDefence() {
        return 0;
    }

    @Override
    public int getMaxHealth() {
        return 20;
    }

    @Override
    public String getLongName() {
        return "Health for player";
    }

    @Override
    public String getShortName() {
        return "heart";
    }

    @Override
    public int getSize() {
        return 2;
    }


    @Override
    public String getGraphicTextSymbol() {
        if (useEmoji()) {
            return Printer.coloured("♡", Color.RED);
        } else {
            return "" + SYMBOL;
        }
    }
    private boolean useEmoji() {
        return true;
    }

    @Override
    public int handleDamage(int amount) {
        return 0;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}

