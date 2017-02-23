package test;

import gridpack.MapMaker;
import pathfinder.*;

import java.io.*;

public class Test {
    static int maps = 5, runs = 10, heuristic = 0;
    static double hWeight = 1;

/*    public static void main(String args[]) throws IOException {

*//*        if (args.length > 0) {
            try {
                heuristic = Integer.parseInt(args[0]);

            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }*//*
*//*        Heuristic e = new NoHeuristic();

        switch (heuristic)  {
            case 0:
                e = new NoHeuristic();
                break;
            case 1:
                e = new Euclidian();
                break;
            case 2:
                e = new Manhattan();
                break;
            case 3:
                e = new DiagonalDistance();
                break;
            case 4:
                e = new Farthest();
                break;
            default:
                System.out.println("Invalid input");
                System.exit(1);
        }*//*


        Grid g = new Grid();
        MapMaker map = new MapMaker();
        map.createMap();
        map.selectStartEnd();
        map.printFile("map.txt");
        g.readFile("map.txt");
        int algo = 2;
        if(algo == 1)
        {
        	Astar h = new Astar(g, hWeight);
        	h.findpath();
        }
        if(algo == 2)
        {
        	AStar1 h = new AStar1(g, hWeight);
        	h.findpath();
        }
        
        MapMaker.displayGrid();
}*/

    public static void main(String args[]) throws IOException {
        double hWeight = 2;
        PathAttributes[] p = new PathAttributes[maps*runs];
        PathAttributes p2;
//        Heuristic e;
//        e = new Farthest();
        Grid g = new Grid();
        //MapMaker map = new MapMaker();

        for(int i=0; i < maps; i++){
//            map.createMap();
            for(int j = 0; j < runs; j++) {
//                map.selectStartEnd();
//                map.printFile(Integer.toString(i)+Integer.toString(j)+"map.txt");

                g.readFile(Integer.toString(i)+Integer.toString(j)+"map.txt");
                Astar h = new Astar(g, hWeight);
                int counter = 0;
                while((p2 = h.findpath()) == null && counter < 10)    {
                    counter ++;
//                    map.selectStartEnd();
//                    map.printFile(Integer.toString(i)+Integer.toString(j)+"map.txt");
//                    g.readFile(Integer.toString(i)+Integer.toString(j)+"map.txt");
//                    h = new Astar(g, e, hWeight);
                }
                p[i*10+j] = p2;
                //h.savePath(Integer.toString(i)+Integer.toString(j)+"path.txt");
            }
        }
        printAttributes(p);
        //MapMaker.displayGrid();
    }

    private static void printAttributes(PathAttributes[] p) {
        File file = new File("attributes.txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            PrintStream original = System.out;
            System.setOut(ps);

            System.out.println("Cost, RunTime, Explored, PathLength");
            for(int i = 0; i < maps*runs; i++) {
                if (p[i] != null)
                    System.out.println(p[i].pathCost + ", " + p[i].runTime + ", " + p[i].exploredNodes + ", " + p[i].pathLength);
                else
                    System.out.println();
            }
            System.setOut(original);
        } catch (FileNotFoundException e1) {
            System.out.println("Error writing path to file");
        }
    }
}
