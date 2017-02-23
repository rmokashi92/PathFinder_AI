package pathfinder;

import static java.lang.StrictMath.sqrt;

public class Euclidian implements Heuristic{
    @Override
    public double getHeuristicCost(int x1, int y1, int x2, int y2) {
        return sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }
}
