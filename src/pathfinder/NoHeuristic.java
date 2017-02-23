package pathfinder;

public class NoHeuristic implements Heuristic {
    @Override
    public double getHeuristicCost(int x1, int y1, int x2, int y2) {
        return 0;
    }
}
