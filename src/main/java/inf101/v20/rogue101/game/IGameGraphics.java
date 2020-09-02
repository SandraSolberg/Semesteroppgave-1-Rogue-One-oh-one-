package inf101.v20.rogue101.game;

import inf101.v20.gfx.gfxmode.IBrush;
import inf101.v20.gfx.textmode.Printer;
import inf101.v20.rogue101.map.IMapView;

/**
 * GameGraphics interface
 * <p>
 * The game has a map which also knows the current location of all the actors
 * and items. This class reads from that map and draws the current state on the
 * screen.
 *
 * @author Martin Vatshelle, extracted from code by Anya
 *
 */
public interface IGameGraphics {

	/**
	 * Displays a message in the debug area on the screen (bottom line)
	 *
	 * @param s A message
	 */
	void displayDebug(String s);

	/**
	 * Displays a message in the message area on the screen (below the map and the
	 * status line)
	 *
	 * @param s A message
	 */
	void displayMessage(String s);

	/**
	 * Displays a status message in the status area on the screen (right below the
	 * map)
	 *
	 * @param s A message
	 */
	void displayStatus(String s);

	/**
	 * Displays a message in the message area on the screen (below the map and the
	 * status line)
	 *
	 * @param s A message
	 * @see String#format(String, Object...)
	 */
	void formatDebug(String s, Object... args);

	/**
	 * Displays a formatted message in the message area on the screen (below the map
	 * and the status line)
	 *
	 * @param s A message
	 * @see String#format(String, Object...)
	 */
	void formatMessage(String s, Object... args);

	/**
	 * Displays a formatted status message in the status area on the screen (right
	 * below the map)
	 *
	 * @param s A message
	 * @see String#format(String, Object...)
	 */
	void formatStatus(String s, Object... args);

	/**
	 * Clear the unused graphics area (you can fill it with whatever you want!)
	 */
	void clearFreeGraphicsArea();

	/**
	 * Clear the unused text area (you can fill it with whatever you want!)
	 */
	void clearFreeTextArea();

	/**
	 * Get the bounds of the free graphics area.
	 * <p>
	 * You can fill this with whatever you want, using {@link #getPainter()} and
	 * {@link #clearFreeGraphicsArea()}.
	 *
	 * @return Array of coordinates; ([0],[1]) are the top-left corner, and
	 *         ([2],[3]) are the bottom-right corner (exclusive).
	 */
	double[] getFreeGraphicsAreaBounds();

	/**
	 * Get the bounds of the free text area.
	 * <p>
	 * You can fill this with whatever you want, using {@link #getPrinter()} and
	 * {@link #clearFreeTextArea()}.
	 * <p>
	 * You'll probably want to use something like:
	 *
	 * <pre>
	 * int[] bounds = getFreeTextArea();
	 * int x = bounds[0];
	 * int y = bounds[1];
	 * game.getPrinter().printAt(x, y++, "Hello");
	 * game.getPrinter().printAt(x, y++, "Do you have any carrot cake?", Color.ORANGE);
	 * </pre>
	 *
	 * @return Array of column/line numbers; ([0],[1]) is the top-left corner, and
	 *         ([2],[3]) is the bottom-right corner (inclusive).
	 */
	int[] getFreeTextAreaBounds();

	/**
	 * See {@link #getFreeGraphicsAreaBounds()}, {@link #clearFreeGraphicsArea()}.
	 *
	 * @return A Turtle, for painting graphics
	 */
	IBrush getPainter();

	/**
	 * See {@link #getFreeTextAreaBounds()}, {@link #clearFreeTextArea()}.
	 *
	 * @return A printer, for printing text
	 */
	Printer getPrinter();

	/**
	 * @return The map
	 */
	IMapView getMap();
}
