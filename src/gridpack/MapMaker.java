package gridpack;

import pathfinder.Astar;

import javax.swing.*;
import java.io.*;
import java.util.Random;
import static gridpack.CommonTags.*;
//import static gridpack.CommonTags.ROWS;
//import static gridpack.CommonTags.grid;

public class MapMaker {
    //private final static int COLUMNS = 120;
    //private final static int ROWS = 160;
    //private int[][] grid = new int[ROWS][COLUMNS];
//    private final static int CLEAR = 1;
//    private final static int RIVER = 9;
//    private final static int HARD = 2;
//    private final static int BLOCKED = 0;

    public MapMaker() throws FileNotFoundException {
        //createMap();
    }
    private static Random random = new Random();
    private static int x,y, riverAttempts=0, riverCount = 0;
    //, xStart = 0, yStart = 0, xGoal = 0, yGoal = 0;
    private static int[][] hardCells = new int[8][2];

    public void createMap(){


        try {
            MapMaker c = new MapMaker();

            for(int i = 0; i < grid.length; i++)
                for(int j = 0; j < grid[i].length; j++)
                    grid[i][j] = CLEAR;
            for(int i = 0; i < 8; i++)  {
                int xRand = random.nextInt(ROWS);
                int yRand = random.nextInt(COLUMNS);
                hardCells[i][0] = xRand;
                hardCells[i][1] = yRand;
                c.markHardCells(xRand, yRand);
            }

            while(!c.markRivers())  {}

            c.markBlockedCells();

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }


        //c.printGrid();
    }

    public static void displayGrid() {
        JFrame frame = new GridFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GridViewer");
        frame.setVisible(true);
    }

    public void printFile(String FName) {
        File file = new File(FName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream ps = new PrintStream(fos);
        PrintStream original = System.out;
        System.setOut(ps);
        System.out.println(xStart + ", " + yStart);
        System.out.println(xGoal + ", " + yGoal);
        for(int i = 0; i < 8; i++)
            System.out.println(hardCells[i][0] + ", " + hardCells[i][1]);
        System.out.println(ROWS + ", " + COLUMNS);
        printGrid();
        System.setOut(original);
    }

    public void selectStartEnd() {
        do{
            if(random.nextDouble() < 0.5)   {
                xStart = random.nextInt(20);
                yStart = random.nextInt(COLUMNS);
            }
            else    {
                xStart = random.nextInt(20) + ROWS - 40;
                yStart = random.nextInt(COLUMNS);
            }

            if(random.nextDouble() < 0.5) {
                xGoal = random.nextInt(ROWS);
                yGoal = random.nextInt(20);
            }
            else    {
                xGoal = random.nextInt(ROWS);
                yGoal = random.nextInt(20) + COLUMNS - 40;
            }
        }while(grid[xStart][yStart] == (BLOCKED) && grid[xGoal][yGoal] == (BLOCKED) && (Math.abs(xGoal - xStart) < 100) || (Math.abs(yGoal - yStart) < 100));

    }

    private Boolean markRivers() {
        newRiver:
        for(; riverCount < 4; riverCount++, riverAttempts++)   {
            //System.out.println("Count: " + riverCount);
            String[][] gridBackup = new String[ROWS][COLUMNS];
            //gridBackup = grid;
            gridCopy(grid, gridBackup);
            int xRand, yRand, riverLen, direction;
            //int x = xRand, y = yRand;
            //N X dec,    0
            //S X inc,    2
            //E Y inc,    1
            //W Y dec     3

            //get river starting point
            if(random.nextDouble() < 0.5)   {
                xRand = random.nextInt(ROWS);
                if(random.nextDouble() < 0.5) {
                    yRand = 0;
                    direction = 1;
                }
                else {
                    yRand = COLUMNS - 1;
                    direction = 3;
                }
            }
            else {
                yRand = random.nextInt(COLUMNS);
                if(random.nextDouble() < 0.5) {
                    xRand = 0;
                    direction = 2;
                }
                else {
                    xRand = ROWS - 1;
                    direction = 0;
                }
            }

//            if((xRand == 0 && yRand == 0) || (xRand == 0 && yRand == COLUMNS) || (xRand == ROWS && yRand == 0) || (xRand == ROWS && yRand == COLUMNS))   {
//                //riverCount--;
//                continue newRiver;
//            }

            grid[xRand][yRand] = RIVER + Integer.toString(riverCount);
            riverLen = 1;
            x = xRand;
            y = yRand;

            // first 20 go straight
            for(; riverLen < 20; riverLen ++)   {
                if(goAhead(direction, riverCount) < 1) {
                    //restart the loop
                    gridCopy(gridBackup, grid);
                    if(riverCount < 0)
                        riverCount = -1;
                    else
                        riverCount--;
                    continue newRiver;
                }
            }

            // keep going till you hit border
            int temp = -999;
            do {
                direction = getDirection(direction);
                int localLen = 0;
                while(localLen < 20 && (temp = goAhead(direction, riverCount)) == 1)    {
                    riverLen++;
                    localLen++;
                }
                if(localLen < 20)   {
                    if(temp == -1)  {   //hit by block/river, remove current river, restart loop
                        gridCopy(gridBackup, grid);
//                        System.out.println("Found block/river. restarting ");
                        if(riverCount < 0)
                            riverCount = -1;
                        else
                            riverCount--;
                        continue newRiver;
                    }
                    else if(temp == 0)  {   //hit by border, if river length < 100 then restart
                        if(riverLen < 100)  {
                            gridCopy(gridBackup, grid);
//                            System.out.println("Found border before 100. restarting " + riverCount);
                            if(riverCount < 0)
                                riverCount = -1;
                            else
                                riverCount--;

                            continue newRiver;
                        }
                        else    {   //river is COMPLETE
                            System.out.println("River done: " + riverCount + " Length " + riverLen);
                            break;
                        }
                    }
                }
/*                switch (temp) {
                    case -1:
                        //hit by block/river, remove current river, restart loop
                        grid = gridBackup;
                        //riverCount--;
                        System.out.println("goto 1 " + riverCount);
                        continue newRiver;
                    case 0:
                        //hit by border, if river length < 100 then restart
                        if (riverLen < 100) {
                            //restart the loop
                            grid = gridBackup;
                            //riverCount--;
                            System.out.println("goto 2" + riverCount);
                            continue newRiver;
                        } else {
                            break doLoop;
                        }
                    case 1:
                        continue doLoop;
                }
*/

/*                else if (ran < 0.4) {   //turn -1
                    direction = (direction - 1) % 4;
                    int localLen = 0;
                    while(localLen < 20 && (temp = goAhead(direction)) == 1)    {
                        riverLen++;
                        localLen++;
                    }
                    switch (temp){
                        case -1:
                            //restart the loop
                            grid = gridBackup;
                            //riverCount--;
                            System.out.println("goto 3" + riverCount);
                            continue newRiver;
                        case 0:
                            if(riverLen < 100)  {
                                //restart the loop
                                grid = gridBackup;
                                //riverCount--;
                                System.out.println("goto 4" + riverCount);
                                continue newRiver;
                            }   else    {
                                break doLoop;
                            }
                            break;
                        case 1:
                            continue doLoop;
                            System.out.println("goto 5" + riverCount);
                    }
                } else {   //go straight
                    int localLen = 0;
                    while(localLen < 20 && (temp = goAhead(direction)) == 1)    {
                        riverLen++;
                        localLen++;
                    }
                    switch (temp){
                        case -1:
                            //restart the loop
                            grid = gridBackup;
                            //riverCount--;
                            System.out.println("goto 6" + riverCount);
                            continue newRiver;
                        case 0:
                            if(riverLen < 100)  {
                                //restart the loop
                                grid = gridBackup;
                                //riverCount--;
                                System.out.println("goto 7" + riverCount);
                                continue newRiver;
                            }
                            System.out.println("goto 8" + riverCount);
                            continue newRiver;

                    }
                }
*/
                //System.out.println("End of do");
                if(riverAttempts >= 400) {
                    //System.out.println("Too many attempts");
                    return false;
                }
            } while(true);
//                System.out.println("Out of a loop");
        }   //for 4 rivers
        return true;
    }   //function

    private void gridCopy(String[][] a, String[][] b) {
        for(int i=0; i < a.length; i++)
            System.arraycopy(a[i], 0, b[i], 0, a[i].length);
    }

    private int getDirection(int direction) {
        double ran = random.nextDouble();
        if (ran < 0.2)  //turn + 1
            return ((direction + 1) % 4);
        if (ran < 0.4)    //turn -1
            return ((direction - 1) % 4);
        return direction;
    }

    private Integer goAhead(int direction, int riverCount) { //0 for border, -1 for blocked/river, 1 for success
        if(direction == 0 && (x-1) >= 0 && !grid[x-1][y].startsWith(RIVER) && grid[x-1][y] != BLOCKED)  {
            grid[--x][y] = RIVER + Integer.toString(riverCount);
            if(x == 0)   return 0;
            return 1;
        }
        else if (direction == 2 && (x+1) < ROWS && !grid[x+1][y].startsWith(RIVER) && grid[x+1][y] != BLOCKED) {
            grid[++x][y] = RIVER + Integer.toString(riverCount);
            if(x == ROWS)    return 0;
            return 1;
        }
        else if (direction == 1 && (y+1)  < COLUMNS && !grid[x][y+1].startsWith(RIVER) && grid[x][y+1] != BLOCKED) {
            grid[x][++y] = RIVER + Integer.toString(riverCount);
            if(y == COLUMNS) return 0;
            return 1;
        }
        else if (direction == 3 && (y-1) >= 0 && !grid[x][y-1].startsWith(RIVER) && grid[x][y-1] != BLOCKED) {
            grid[x][--y] = RIVER + Integer.toString(riverCount);
            if(y == 0)   return 0;
            return 1;
        }
        return -1;
    }

    private void markBlockedCells() {
        for( int i = 0; i < ROWS * COLUMNS * 0.2; i++)  {
            int xrand = random.nextInt(ROWS);
            int yrand = random.nextInt(COLUMNS);
            grid[xrand][yrand] = (grid[xrand][yrand].startsWith(RIVER)) ? grid[xrand][yrand].replace(RIVER, HARDRIVER) : BLOCKED;
        }
        gridCopy(grid, gridcopy);
    }

    private static void printGrid() {
        for(int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++)
                System.out.print(grid[i][j] + ",\t");
            System.out.println();
        }

    }

    private void markHardCells(int xrand, int yrand) {
        int xmin = ((xrand - 15) > 0) ? xrand - 15 : 0;
        int xmax = ((xrand + 15) < ROWS) ? xrand + 15 : ROWS;
        int ymin = ((yrand - 15) > 0) ? yrand - 15 : 0;
        int ymax = ((yrand + 15) < COLUMNS) ? yrand + 15 : COLUMNS;
        for(int x = xmin; x < xmax; x++) {
            for(int y = ymin; y < ymax; y++) {
                if(random.nextDouble() < 0.5)
                    grid[x][y] = HARD;
            }
        }
    }
}