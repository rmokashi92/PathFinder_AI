package pathfinder;

import gridpack.CommonTags;

import java.io.*;
import java.util.Scanner;

import static gridpack.CommonTags.grid;
import static gridpack.CommonTags.hardPoints;

import static gridpack.CommonTags.*;

public class Grid {


    public int COLUMNS;
    public int ROWS;
    //public static int ;
    //public String [][] grid ;
    final static String CLEAR = "1";
    final static String RIVER = "a";
    final static String HARDRIVER = "b";
    final static String HARD = "2";
    final static String BLOCKED = "0";
    int xStart, yStart, xGoal, yGoal;

    public Grid(){
    }
    /*public double getCost(int x1, int y1, int x2, int y2)  {

        if(Math.abs(x2-x1)>1 || Math.abs(y2-y1)>1 )
        {
            return Integer.MAX_VALUE;
        }

        if(grid[x1][y1].equals(BLOCKED) || grid[x2][y2].equals(BLOCKED))
            return Integer.MAX_VALUE;
        if(!isDiagonal(x1,x2,y1,y2)){
            if(grid[x1][y1].equals(HARD))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 2;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 1.5;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 1.5/4;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 0.5;
                }
            }
            else if(grid[x1][y1].equals(CLEAR))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 1.5;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 1;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 1/4;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 1.5/4;
                }
            }
            else if(grid[x1][y1].startsWith("a"))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 1.5/4;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 0.25;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 0.25;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 1.5/4;
                }
            }
            else if(grid[x1][y1].startsWith("b"))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 0.5;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 1.5/4;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 1.5/4;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 0.5;
                }
            }
        }
        else    {
            if(grid[x1][y1].equals(HARD))    {
                if(grid[x2][y2].equals(HARD))    {
                    return Math.sqrt(8);
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return (Math.sqrt(2) + Math.sqrt(2))/2;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return Integer.MAX_VALUE;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return Integer.MAX_VALUE;
                }
            }
            else if(grid[x1][y1].equals(CLEAR))    {
                if(grid[x2][y2].equals(HARD))    {
                    return (Math.sqrt(2) + Math.sqrt(2))/2;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return Math.sqrt(2);
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return Integer.MAX_VALUE;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return Integer.MAX_VALUE;
                }
            }
            else if(grid[x1][y1].startsWith("a"))    {
                return Integer.MAX_VALUE;
            }
            else if(grid[x1][y1].startsWith("b"))    {
                return Integer.MAX_VALUE;
            }
        }


        return 0;
    }*/


    public double getCost(int x1, int y1, int x2, int y2)  {

        if(Math.abs(x2-x1)>1 || Math.abs(y2-y1)>1 )
        {
            return Integer.MAX_VALUE;
        }

        if(grid[x1][y1].equals(BLOCKED) || grid[x2][y2].equals(BLOCKED))
            return Integer.MAX_VALUE;
        if(!isDiagonal(x1,x2,y1,y2)){
            if(grid[x1][y1].equals(HARD))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 2;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 1.5;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 1.5/4;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 0.5;
                }
            }
            else if(grid[x1][y1].equals(CLEAR))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 1.5;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 1;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 1/4;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 1.5/4;
                }
            }
            else if(grid[x1][y1].startsWith("a"))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 1.5;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 1;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 0.25;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 1.5/4;
                }
            }
            else if(grid[x1][y1].startsWith("b"))    {
                if(grid[x2][y2].equals(HARD))    {
                    return 1.5;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return 1;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return 1/4;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return 0.5;
                }
            }
        }
        else    {
            if(grid[x1][y1].equals(HARD))    {
                if(grid[x2][y2].equals(HARD))    {
                    return Math.sqrt(8);
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return (Math.sqrt(2) + Math.sqrt(2))/2;
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return Integer.MAX_VALUE;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return Integer.MAX_VALUE;
                }
            }
            else if(grid[x1][y1].equals(CLEAR))    {
                if(grid[x2][y2].equals(HARD))    {
                    return (Math.sqrt(2) + Math.sqrt(2))/2;
                }
                else if(grid[x2][y2].equals(CLEAR))  {
                    return Math.sqrt(2);
                }
                else if(grid[x2][y2].startsWith("a") )  {
                    return Integer.MAX_VALUE;
                }
                else if(grid[x2][y2].startsWith("b") )  {
                    return Integer.MAX_VALUE;
                }
            }
            else if(grid[x1][y1].startsWith("a"))    {
                return Integer.MAX_VALUE;
            }
            else if(grid[x1][y1].startsWith("b"))    {
                return Integer.MAX_VALUE;
            }
        }


        return 0;
    }


    private static boolean isDiagonal(int x1, int y1, int x2, int y2)    {
        return (x1 - 1 == x2 && y1 - 1 == y2) || (x1 + 1 == x2 && y1 + 1 == y2) || (x1 - 1 == x2 && y1 + 1 == y2) || (x1 + 1 == x2 && y1 - 1 == y2);
    }

    public void readFile(String FName) throws IOException {
        File file = new File(FName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine();
            String[] start = line.split(", ");
            xStart = Integer.parseInt(start[0]);
            yStart = Integer.parseInt(start[1]);
            line = br.readLine();
            start = line.split(", ");
            xGoal = Integer.parseInt(start[0]);
            yGoal = Integer.parseInt(start[1]);
            for (int i = 0; i< 8; i++){
                line = br.readLine();
                start = line.split(", ");
                hardPoints[i][0] = Integer.parseInt(start[0]);
                hardPoints[i][1] = Integer.parseInt(start[1]);
            }
            line = br.readLine();
            start = line.split(", ");
            ROWS = Integer.parseInt(start[0]);
            COLUMNS = Integer.parseInt(start[1]);
            grid = new String[ROWS][COLUMNS];
            for(int i = 0; i < ROWS ; i++)   {
                line = br.readLine();
                if (line == null)
                        break;
                start = line.split(",\t");
                for(int j = 0; j < COLUMNS; j++){
                    grid[i][j] = start[j];
                }
            }
            gridcopy[xStart][yStart] = "111";
            gridcopy[xGoal][yGoal] = "112";
        }
    }
}
