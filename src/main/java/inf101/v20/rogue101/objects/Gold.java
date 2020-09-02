package inf101.v20.rogue101.objects;

import inf101.v20.gfx.gfxmode.DrawHelper;
import inf101.v20.gfx.gfxmode.IBrush;
import inf101.v20.gfx.textmode.Printer;
import inf101.v20.rogue101.RogueApplication;
import javafx.scene.paint.Color;

/**
 * Gold i spillet Rogue 101.
 *
 *
 * @author Sandra Solberg - sit006
 *
 */

public class Gold implements IItem {

    /**
     * char representation of this type
     */
    public static final char SYMBOL = 'G';
    private int hp = getMaxHealth();

    @Override
    public int getCurrentHealth() {
        return hp;
    }

    /*
    Gull gitt gull defence 4 siden
     */
    @Override
    public int getDefence() {
        return 4;
    }

    @Override
    public int getMaxHealth() {
        return 20;
    }
    /*
    Au, gold
     */

    @Override
    public String getLongName() {
        return "Au, gold";
    }

    @Override
    public String getShortName() {
        return "gold";
    }

    @Override
    public int getSize() {
        return 4;
    }


    /**
     *  Brukte Symbola i font filen, og kopierte en emoji fra hjemmesiden vedlagt i
     *RougeApplications
     **/

    @Override
    public String getGraphicTextSymbol() {
        if (useEmoji()) {
            return Printer.coloured("⛀", Color.GOLD);
        } else {
            return "" + SYMBOL;
        }
    }
    private boolean useEmoji() {
        return true;
    }
    /*
    Gull skal hittil ikke skades
     */

    @Override
    public int handleDamage(int amount) {
        return 0;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}
