package pathfinder;

public class PathAttributes {
    public long runTime;
    public int pathLength, exploredNodes;
    public double pathCost;
    public PathAttributes(long time, int length, int explored, double cost) {
        runTime = time;
        pathLength = length;
        exploredNodes= explored;
        pathCost = cost;
    }
}
