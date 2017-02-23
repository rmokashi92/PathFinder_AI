package pathfinder;

public interface Heuristic {
    public double getHeuristicCost(int x1, int y1, int x2, int y2);
}
