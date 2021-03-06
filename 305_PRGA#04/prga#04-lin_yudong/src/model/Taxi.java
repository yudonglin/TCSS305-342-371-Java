package model;

/**
 * A subclass of AbstractCar
 */
public final class Taxi extends AbstractCar {

    // clock cycles the taxi will wait if crosswalk light is red
    private final int clockCyclesWillWait = 3;
    // the count-down of the waiting time
    private int waitCountDown = clockCyclesWillWait;

    /**
     * Constructs a new Taxi
     *
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Taxi(final int x, final int y, final Direction direction) {
        super(x, y, direction);
    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        super.reset();
        // and reset count-down
        waitCountDown = clockCyclesWillWait;
    }

    /**
     * Returns whether this object may move onto the given type of
     * terrain, when the streetlights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this object may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain == Terrain.CROSSWALK) {
            if (theLight != Light.RED || waitCountDown == 0) {
                waitCountDown = clockCyclesWillWait;
                return true;
            } else {
                waitCountDown--;
                return false;
            }
        } else {
            return super.canPass(theTerrain, theLight);
        }
    }
}
