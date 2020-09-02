package inf101.v20.rogue101.map;

import java.util.*;

import inf101.v20.gfx.gfxmode.Direction;
import inf101.v20.grid.GridDirection;
import inf101.v20.grid.IArea;
import inf101.v20.grid.ILocation;
import inf101.v20.grid.ILocationComparator;
import inf101.v20.grid.IMultiGrid;
import inf101.v20.grid.MultiGrid;
import inf101.v20.rogue101.game.IllegalMoveException;
import inf101.v20.rogue101.objects.IActor;
import inf101.v20.rogue101.objects.IItem;
import inf101.v20.rogue101.objects.Wall;
import javafx.util.Pair;

public class GameMap implements IGameMap {
    /**
     * The grid that makes up our map
     */
    private final IMultiGrid<IItem> grid;
    /**
     * These locations have changed since last time graphics drew the screen,
     * and need to be redrawn soon.
     */
    private final Set<ILocation> dirtyLocs = new HashSet<>();
    /**
     * An index of all the items in the map and their locations.
     */
    // an IdentityHashMap uses object identity as a lookup key, so items are
    // considered equal if they are the same object (a == b)
    private final Map<IItem, ILocation> items = new IdentityHashMap<>();

    public GameMap(IArea area) {
        grid = new MultiGrid<>(area);
    }

    public GameMap(int width, int height) {
        grid = new MultiGrid<>(width, height);
    }

    /**
     * This method adds an IItem to the given location.
     * Since the draw method only draws one element in each cell,
     * the first element is the one drawn
     * In this implementation of IGameMap IItem's at same location
     * should be sorted such that the IItem with largest size() is first.
     */
    @Override
    public void add(ILocation loc, IItem item) {
        // keep track of location of all items
        items.put(item, loc);
        // also keep track of whether we need to redraw this cell
        dirtyLocs.add(loc);

        // do the actual adding
        List<IItem> list = grid.get(loc);
        list.add(item);
        Collections.sort(list, Collections.reverseOrder());
    }

    /**
     * In this implementation of IGame an IActor can not go through Walls
     * and only one IActor can be at each cell
     */
    @Override
    public boolean canGo(ILocation to) {
        return !grid.contains(to, (i) -> (i instanceof Wall || i instanceof IActor));
    }

    @Override
    public boolean hasNeighbour(ILocation from, GridDirection dir) {
        return from.canGo(dir);
    }

    @Override
    public boolean canGo(ILocation from, GridDirection dir) {
        if (!from.canGo(dir))
            return false;
        ILocation loc = from.go(dir);
        return canGo(loc);
    }

    /**
     * Returns the ILocation's that needs to be redrawn due to some change
     * that could lead to a different image being displayed at that ILocation
     */
    public Set<ILocation> getDirtyLocs() {
        return dirtyLocs;
    }

    /**
     * In this implementation only 1 IActor can occupy each cell,
     * hence the size() of the list returned is at most 1.
     */
    @Override
    public List<IActor> getActors(ILocation loc) {
        List<IActor> items = new ArrayList<>();
        for (IItem item : grid.get(loc)) {
            if (item instanceof IActor)
                items.add((IActor) item);
        }

        return items;
    }

    @Override
    public List<IItem> getAll(ILocation loc) {
        return Collections.unmodifiableList(grid.get(loc));
    }

    @Override
    public List<IItem> getAllModifiable(ILocation loc) {
        dirtyLocs.add(loc);
        return grid.get(loc);
    }

    @Override
    public void clean(ILocation loc) {
        // remove any items that have health < 0:
        if (grid.get(loc).removeIf((item) -> {
            if (item.isDestroyed()) {
                items.remove(item);
                return true;
            } else {
                return false;
            }
        }))

            dirtyLocs.add(loc);
    }

    @Override
    public IArea getArea() {
        return grid.getArea();
    }

    @Override
    public int getHeight() {
        return grid.getHeight();
    }

    @Override
    public List<IItem> getItems(ILocation loc) {
        List<IItem> items = new ArrayList<>(grid.get(loc));
        items.removeIf((i) -> i instanceof IActor);
        return items;
    }

    @Override
    public ILocation getLocation(IItem item) {
        return items.get(item);
    }

    @Override
    public ILocation getLocation(int x, int y) {
        return grid.getArea().location(x, y);
    }

    @Override
    public ILocation getNeighbour(ILocation from, GridDirection dir) {
        if (!hasNeighbour(from, dir))
            return null;
        else
            return from.go(dir);
    }

    @Override
    public int getWidth() {
        return grid.getWidth();
    }

    @Override
    public ILocation go(ILocation from, GridDirection dir) throws IllegalMoveException {
        if (!from.canGo(dir))
            throw new IllegalMoveException("Cannot move outside map!");
        ILocation loc = from.go(dir);
        if (!canGo(loc))
            throw new IllegalMoveException("Occupied!");

        return loc;
    }


    @Override
    public boolean has(ILocation loc, IItem target) {
        return grid.contains(loc, target);
    }

    @Override
    public boolean hasActors(ILocation loc) {
        return grid.contains(loc, (i) -> i instanceof IActor);
    }

    @Override
    public boolean hasItems(ILocation loc) {
        // true if grid cell contains an item which is not an IActor
        return grid.contains(loc, (i) -> !(i instanceof IActor));
    }

    @Override
    public boolean hasWall(ILocation loc) {
        return grid.contains(loc, (i) -> i instanceof Wall);
    }

    @Override
    public void remove(ILocation loc, IItem item) {
        grid.remove(loc, item);
        items.remove(item);
        dirtyLocs.add(loc);
    }

    /**
     * Gitt oppgaven sjekker den for dist = 1  8 Directione rundt loc, og retunerer en liste
     * dist = 2 sjekker for 8 nærmeste og de 16 nest nærmeste posisjonene og legger dem til i en liste
     *
     * Etter den har lagt til alle posisjoner som er innen en dist dersom de ikke inneholder vegger.
     * Lager en ekstra for-loop for å fjerne posisjoner med vegger fra locations med dist 1.
     **/

    @Override
    public List<ILocation> getNeighbourhood(ILocation loc, int dist) {
        if (dist <= 0 || loc == null) {
            throw new IllegalArgumentException();
        } else if (dist == 0) {
            return new ArrayList<>(); // empty!
        }

        List<ILocation> neighbour = new ArrayList<>();

        if (dist == 1) {
            neighbour.addAll(loc.allNeighbours());


        } else {
            for (int x = -dist; x <= dist; x++) {
                for (int y = -dist; y <= dist; y++) {
                    int newX = loc.getX() + x;
                    int newY = loc.getY() + y;

                    if (newX < 0 || newX > this.getWidth()) continue;
                    if (newY < 0 || newY > this.getHeight()) continue;

                    if (!hasWall(getLocation(newX, newY)))
                        neighbour.add(getLocation(newX, newY));

                }
            }
        }
        for (ILocation w : neighbour) {
            if (hasWall(w)) {
                neighbour.remove(w);
            }

        }
        sortList(neighbour, loc);
        neighbour.remove(loc);


        return neighbour;
    }

    /**
     * Hjelpe-metode for å sortere listen i getNeighbourhood.
     * Tar inn to paramtere en Liste, og en ILocation obj
     * velger to ILocations og sammenligner de med loc
     * @param list
     * @param loc
     * Bruker ILocationComparator og dens metode compare(loc1, loc2)
     * bruker Collections.sort(list, comp) for å sortere basert på metoden
     *
     */
    public void sortList(List<ILocation> list, ILocation loc){
        ILocation second = list.get(1);
        ILocation last = list.get(list.size()-1);
        ILocationComparator comp = new ILocationComparator(loc);
        comp.compare(last,second);
        Collections.sort(list, comp);
    }
    /**
     * Går gjennom en liste med directions og sjekker om det er mulig å gå der.
     * Bruker GridDirection.NINE_Directions fordi det er alle possibleMoves
     *
     * @param currentLocation
     * @return possibleMoves liste
     */

    @Override
    public List<GridDirection> getPossibleMoves(ILocation currentLocation) {

        List<GridDirection> possible = GridDirection.NINE_DIRECTIONS;
        List<GridDirection> dir = new ArrayList<>();
        for (GridDirection direction : possible) {
            if (canGo(currentLocation, direction)) {
                dir.add(direction);

            }
        }
        return dir;
    }
    /**
     * Bruker BFS - algrotimen til å implementere metoden
     * har en boolean array for å holde styr på besøkte posisjoner
     * Lager queue for å se hvilke posisjoner som skal ses på videre
     *  Går gjennom og ser om vi kan nå nabo-posisjoner også legger vi dem til i queuen
     *  Når vi har nådd antall step gitt i dist fjerner vi loc og retunerer liste med locationer
     **/

    @Override
    public List<ILocation> getReachable(ILocation loc, int dist) {
        List<ILocation> result = new ArrayList<>();
        //ser om posisjonen blir sett;
        boolean visited[][] = new boolean[grid.getWidth()][grid.getHeight()];

        Queue<Pair<ILocation,Integer>> q = new LinkedList<>();
        q.add(new Pair<>(loc,0));
        visited[loc.getX()][loc.getY()] = true;
        while(!q.isEmpty()){
            Pair<ILocation, Integer> curr = q.poll();
            int cx = curr.getKey().getX(), cy = curr.getKey().getY(), steps = curr.getValue();

            if(steps > dist)
                continue;

            result.add(curr.getKey());

            for(int dx = -1; dx < 2; dx++) {
                for (int dy = -1; dy < 2; dy++){
                    int px = cx+dx, py = cy+dy;
                    if(px == cx && py == cy)
                        continue;
                    ILocation nxtloc = getLocation(px,py);
                    if(canGo(nxtloc) && !visited[px][py]){
                        visited[px][py] = true;
                        q.add(new Pair<>(nxtloc,steps+1));
                    }
                }
            }
        }
        result.remove(loc);
        return result;
    }
}





