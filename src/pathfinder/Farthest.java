package pathfinder;

import static gridpack.CommonTags.hardPoints;
import static java.lang.StrictMath.sqrt;

/**
 * Created by Girish on 10/3/2016.
 */
public class Farthest implements Heuristic {
    @Override
    public double getHeuristicCost(int x1, int y1, int x2, int y2) {
        double cost = 0;
        for(int i = 0; i<8; i++)    {
            cost += sqrt((hardPoints[i][0]-x1)*(hardPoints[i][0]-x1) + (hardPoints[i][1]-y1)*(hardPoints[i][1]-y1));
        }
        return (1200 - cost);
    }
}
