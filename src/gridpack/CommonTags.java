package gridpack;

import pathfinder.Node_state;

public class CommonTags {

	public static GridComponent component;
	public static int COLUMNS = 160;
	public static int ROWS = 120;
	public static String [][] grid = new String[ROWS][COLUMNS];
	public static String [][] gridcopy = new String[ROWS][COLUMNS];
    public final static String CLEAR = "1";
    public final static String RIVER = "a";
    public final static String HARDRIVER = "b";
    public final static String HARD = "2";
    public final static String BLOCKED = "0";
    public static int xStart =0, yStart=0, xGoal=0, yGoal=0;
    public static int[][] pathList = new int[ROWS*COLUMNS][2];
    public static Node_state[][] state = new Node_state[ROWS][COLUMNS];
    public static Node_state[][][] states = new Node_state[ROWS][COLUMNS][4];
    Node_state[][] closed = new Node_state[ROWS*COLUMNS][4];
    public static int[][] hardPoints = new int[8][2];
}
