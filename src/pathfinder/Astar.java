package pathfinder;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.io.*;
import java.util.ArrayList;

import static gridpack.CommonTags.*;

public class Astar {

    Grid g = new Grid();
    Heuristic e;
    double hWeight;
    int flag = 0;
    PathAttributes p;
    public Astar(Grid g, double hWeight) throws IOException {
        this.g = g;
        //this.e = e;
        this.hWeight = hWeight;
    }

    long start_time,end_time,elapsed_time;
    int currpoint,counter;
    int infinity = Integer.MAX_VALUE;
    Node_state goal, start;
    int val_hueristic;
    
    int no_of_heuristic = 4;
    
    double w1 = 1.5, w2 = 2;

    double[][] distance = new double[g.ROWS][g.COLUMNS];
    
	BinaryHeap[] open = new BinaryHeap[no_of_heuristic];
	Node_state[] start_arr= new Node_state[no_of_heuristic];
	//ArrayList<Node_state> closed = new ArrayList<Node_state>();
	
	
	
	ArrayList<Node_state> path = new ArrayList<Node_state>();


    private double evaluation(int x, int y)
    {
        return hWeight * e.getHeuristicCost(x,y, g.xGoal, g.yGoal);
    }

    public PathAttributes findpath() {
        // TODO Auto-generated method stub
        
    
    	//BinaryHeap[] close = new BinaryHeap[4];
    	
    	ArrayList<Node_state> current = new ArrayList<Node_state>();
    	
    	boolean countflag = true;
    	
    	 start = new Node_state(g.xStart, g.yStart);
         goal = new Node_state(g.xGoal, g.yGoal);

         start_time = System.currentTimeMillis();
         
        //init for start state
//        distance[g.xStart][g.yStart] = 0;
     
         for(int i = 0; i<no_of_heuristic; i++)
         {
        	 states[i] = new Node_state[ROWS][COLUMNS];
        	 initStates(states[i]);
        	 start_arr[i] = new Node_state(g.xStart, g.yStart);
         }
         
        for(int i = 0; i<no_of_heuristic; i++)
        {
        	open[i] = new BinaryHeap(g.ROWS*g.COLUMNS);
        	
        	
        	//states[i] = new Node_state[ROWS][COLUMNS];
            for(int j =0; j < ROWS ; j++)
            {
                for(int k = 0; k < COLUMNS; k++)
                {
                    states[i][j][k] = new Node_state(j,k);
                }
            }
            
            
           
            //start_time = System.currentTimeMillis();

            //ArrayList<Node_state> current = new ArrayList<Node_state>();

            System.out.println("xStart: " + start.x + "yStart: " + start.y);
            System.out.println("xGoal: " + goal.x + "yGoal: " + goal.y);

            states[i][g.xStart][g.yStart] = start;
            states[i][g.xGoal][g.yGoal] = goal;
            
            states[i][g.xStart][g.yStart].g = 0;
            states[i][g.xGoal][g.yGoal].g = infinity;
            
            states[i][g.xStart][g.yStart].parent = start;
            states[i][g.xGoal][g.yGoal].parent = null;
            
            start_arr[i].g = 0;
            start_arr[i].h = getEvaluation(i, start.x , start.y);
            start_arr[i].f = start_arr[i].g + start_arr[i].h*w1;
            
            /*start.g = 0;
            start.h = getEvaluation(i, start.x , start.y);
            start.f = start.g + start.h*w1;*/
         
            
            open[i].insert(start_arr[i]);
        	
        }
        
        
        /*start.g = 0;
        start.h = evaluation(start.x, start.y);
        start.f = start.g + start.h;
        start.parent = start;*/
        //fringe = null;

        

        //BinaryHeap fringe = new BinaryHeap(g.ROWS*g.COLUMNS);
        //fringe.insert(start);
        //Now dequeue
//        fringe.printHeap();

        while(!open[0].isEmpty() && flag == 0 && countflag)
        {
            counter++;
            
            if(counter > 2000)
            {
            	countflag = false;
            }
            
            for(int i = 1; i < no_of_heuristic; i++)
            {
            	if(open[i].findMin(i).f <= w2 * open[0].findMin(i).f)
            	{
            		if(states[i][g.xGoal][g.yGoal].g < open[i].findMin(i).g)
					{	
						if( states[i][g.xGoal][g.yGoal].g < infinity)
						{
							System.out.println("Path Found");
							flag = 1;
							val_hueristic = i;
							System.out.println("path cost:" + states[i][g.xGoal][g.yGoal].g);
							Node_state cur = states[i][g.xGoal][g.yGoal];

							while(cur.x!= start.x || cur.y!= start.y)
							{
								current.add(cur);
								cur = cur.parent;
								currpoint++;
								System.out.println(cur.x + "," +cur.y);
								//PRINT PATH HERE
								path.add(cur);
								gridcopy[cur.x][cur.y] = "99";
							}
							gridcopy[g.xStart][g.yStart] = "111";
							break;
						}
					}
						else
						{
							Node_state succ = open[i].deleteMin();
							succ.visited = true;
							//closed[i][counter1].add(succ);
							findSuccessors(succ,open[i],i);
						}
            	}
            	
            	else
            	{
            		if(states[0][g.xGoal][g.yGoal].g < open[0].findMin(i).g)
            		{
            			if(states[0][g.xGoal][g.yGoal].g < infinity)
            			{
            				System.out.println("Path Found");
                            flag = 2;
                            System.out.println("path cost:" + states[i][g.xGoal][g.yGoal].g);
                            Node_state cur = states[0][g.xGoal][g.yGoal];

                            while(cur.x!= start.x || cur.y!= start.y)
                            {
                                current.add(cur);
                                cur = cur.parent;
                                currpoint++;
                                //System.out.println(cur.x + "," +cur.y);
                                //PRINT PATH HERE
                                path.add(cur);
                                gridcopy[cur.x][cur.y] = "99";
                            }
                            gridcopy[g.xStart][g.yStart] = "111";
                            break;

            			}
            			else
            			{
            				Node_state succ = open[0].deleteMin();
                			succ.visited = true;
                            //closed[i][counter1].add(succ);
                            findSuccessors(succ,open[0],0);
            			}
            		}
            	}
            	
            }
            
           /* 
            if((fringe.findMin().x == goal.x) && (fringe.findMin().y == goal.y))
            {
                System.out.println("Path Found");
                flag = 1;
                Node_state cur = fringe.deleteMin();

                while(cur.x!= start.x || cur.y!= start.y)
                {
                    current.add(cur);
                    cur = cur.parent;
                    currpoint++;
                    //System.out.println(cur.x + "," +cur.y);
                    //PRINT PATH HERE
                    path.add(cur);
                    gridcopy[cur.x][cur.y] = "99";
                }

                //System.out.println("Path Cost:" = );
                break;
            }*/

           // Node_state succ = fringe.deleteMin();

           // succ.visited = true;
           // closed.add(succ);
           // findSuccessors(succ,fringe);
        }
        
        if(!countflag)
        {
        	System.out.println("No solution");
        }
        
        end_time = System.currentTimeMillis();
        elapsed_time = end_time - start_time;

        //currpoint path length
        //counter visited/explored node
        //goal.g cost

        if(open[0].isEmpty())
        {
            System.out.println("No solution");
            return null;
        }
        if(flag == 1) {
            p = new PathAttributes(elapsed_time, currpoint, counter, states[val_hueristic][g.xGoal][g.yGoal].g);
            return p;
        }
        else if(flag == 2)
        {
        	p = new PathAttributes(elapsed_time, currpoint, counter, states[0][g.xGoal][g.yGoal].g);
            return p;
        }
        else
        {
        	return null;
        }
    }


    private double getEvaluation(int i, int x, int y) {
		// TODO Auto-generated method stub
    	double value = 0;
    	
    	switch(i)
    	{
    	case 0:
    		int dx = abs(goal.x- x);
	        int dy = abs(goal.y-y);
	        value = sqrt(pow(min(dx,dy),2) * 2) + max(dy,dx);
    	        break;
    	
    	case 1:
    		value = (abs(goal.x-x) + abs(goal.y-y));
    		break;
    	
    	case 2:
    		value = sqrt((goal.x-x)*(goal.x-x) + (goal.y-y)*(goal.y-y));
    		break;
    		
    	case 3:
    		 /* double cost = 0;
    		 for(int j = 0; j<8; j++)    {
    	            cost += sqrt((hardPoints[j][0]-x)*(hardPoints[j][0]-x) + (hardPoints[j][1]-y)*(hardPoints[j][1]-y));
    	        }
    		
    		value = 900 - cost;*/
    		int dx1 = abs(goal.x- x);
	        int dy1 = abs(goal.y-y);
	        value = max(dx1,dy1);
    	break;
    	}
    	
		return value;
	}

	private void findSuccessors(Node_state succ, BinaryHeap fringe, int j) {
        // TODO Auto-generated method stub
        Node_state succ_child;
        int[] neighbourx = new int[]{1,1,1,0,0,-1,-1,-1};
        int[] neighboury = new int[]{0,1,-1,-1,1,1,0,-1};

        for(int i = 0; i<8; i++)
        {
            if(!(succ.x + neighbourx[i] < 0) && !(succ.x + neighbourx[i] >= g.ROWS) && !(succ.y + neighboury[i] < 0) && !(succ.y + neighboury[i] >= g.COLUMNS))
            {
                succ_child = states[j][succ.x+neighbourx[i]][succ.y+neighboury[i]];
                if(!succ_child.visited)
                {
                    if(!succ_child.infringe)
                    {
                        succ_child.g = Integer.MAX_VALUE;
                        succ_child.parent = null;
                    }
                    if(succ_child.g > succ.g + g.getCost(succ.x, succ.y, succ_child.x, succ_child.y))
                    {
                        succ_child.g = succ.g + g.getCost(succ.x, succ.y, succ_child.x, succ_child.y);
                        succ_child.parent = succ;

                        succ_child.h = getEvaluation(j,succ_child.x, succ_child.y);
                        succ_child.f = succ_child.g + succ_child.h*w1;
                        if(fringe.find(succ_child)!=0)
                        {
                            fringe.delete(fringe.find(succ_child));
                        }
                        else
                        {
                            fringe.insert(succ_child);
                            succ_child.infringe = true;
                        }
                    }
                }
            }

        }

    }


    private void initStates(Node_state[][] state) {
        // TODO Auto-generated method stub


        for(int i = 0; i < g.ROWS; i ++)
        {
            for(int j = 0; j < g.COLUMNS; j++)
            {
                state[i][j] = new Node_state(i,j);
                state[i][j].x = i;
                state[i][j].y = j;
                state[i][j].g = infinity;
                state[i][j].visited = false;
                state[i][j].value = grid[i][j];

            }
        }

    }

   /* public void savePath(String FName) {
        File file = new File(FName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e1) {
            System.out.println("Error writing path to file");
        }
        PrintStream ps = new PrintStream(fos);
        PrintStream original = System.out;
        System.setOut(ps);
        System.out.println(state[xGoal][yGoal].g);
        System.out.println(state[xGoal][yGoal].x  + ", " + state[xGoal][yGoal].y);

        for(int i = 0; i < path.size(); i++)
            System.out.println(path.get(i).x + ", " + path.get(i).y);
        System.setOut(original);
    }
*/
}
