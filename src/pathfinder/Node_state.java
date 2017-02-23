package pathfinder;

import static gridpack.CommonTags.*;

public class Node_state {

    int x,y;
    String value;
    Node_state parent;
    boolean visited,infringe;
    public double g,h,f;


    public Node_state(int x_int,int y_int)
    {
        x = x_int;
        y = y_int;
        value = grid[x_int][y_int];
        parent = null;
        visited = false;
        infringe = false;
        g = Integer.MAX_VALUE;

    }

}
