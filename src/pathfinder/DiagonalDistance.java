package pathfinder;

import static java.lang.Math.*;

/**
 * Created by Girish on 10/3/2016.
 */
public class DiagonalDistance implements Heuristic {
    @Override
    public double getHeuristicCost(int x1, int y1, int x2, int y2) {
        int dx = abs(x2-x1);
        int dy = abs(y2-y1);
        return sqrt(pow(min(dx,dy),2) * 2) + max(dy,dx);
        //return 0;
    }
}
