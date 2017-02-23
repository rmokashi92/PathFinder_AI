package pathfinder;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.io.*;
import java.util.ArrayList;

import static gridpack.CommonTags.*;

public class AStar1 {

    Grid g = new Grid();
    Heuristic e;
    double hWeight;
    int flag = 0;
    PathAttributes p;
    public AStar1(Grid g, double hWeight) throws IOException {
        this.g = g;
        //this.e = e;
        this.hWeight = hWeight;
    }

    long start_time,end_time,elapsed_time;
    int currpoint,counter;
    int infinity = Integer.MAX_VALUE;
    Node_state goal, start;
    int val_hueristic;
    
    int no_of_heuristics = 4;
    
    double w1 = 1.5, w2 = 2;

    double[][] distance = new double[g.ROWS][g.COLUMNS];
    
    ArrayList<Node_state> closed_anchor;
    ArrayList<Node_state> closed_inadm;
    
    
    BinaryHeap[] open = new BinaryHeap[no_of_heuristics];
	Node_state[] start_arr= new Node_state[no_of_heuristics];
	//ArrayList<Node_state> closed = new ArrayList<Node_state>();
	
	
	
	ArrayList<Node_state> path = new ArrayList<Node_state>();


    private double evaluation(int x, int y)
    {
        return hWeight * e.getHeuristicCost(x,y, g.xGoal, g.yGoal);
    }

    public PathAttributes findpath() {
        // TODO Auto-generated method stub
        
    	boolean countflag = true;
    	//BinaryHeap[] close = new BinaryHeap[4];
    	
    	ArrayList<Node_state> current = new ArrayList<Node_state>();
    	
    	 start = new Node_state(g.xStart, g.yStart);
         goal = new Node_state(g.xGoal, g.yGoal);

         start_time = System.currentTimeMillis();
         
        //init for start state
//        distance[g.xStart][g.yStart] = 0;
     
        for(int i = 0; i<no_of_heuristics; i++)
         {
        	 //states[i] = new Node_state[ROWS][COLUMNS];
        	 //initStates(states[i]);
        	 start_arr[i] = new Node_state(g.xStart, g.yStart);
         }
         
         
         for(int j =0; j < ROWS ; j++)
         {
             for(int k = 0; k < COLUMNS; k++)
             {
                 state[j][k] = new Node_state(j,k);
             }
         }
         
         initStates();
         
        for(int i = 0; i<no_of_heuristics; i++)
        {
        	open[i] = new BinaryHeap(g.ROWS*g.COLUMNS);
        	
        	
        	//states[i] = new Node_state[ROWS][COLUMNS];
           
            
            
           
            //start_time = System.currentTimeMillis();

            //ArrayList<Node_state> current = new ArrayList<Node_state>();

            System.out.println("xStart: " + start.x + "yStart: " + start.y);
            System.out.println("xGoal: " + goal.x + "yGoal: " + goal.y);

            state[g.xStart][g.yStart] = start;
            state[g.xGoal][g.yGoal] = goal;
            
            state[g.xStart][g.yStart].g = 0;
            state[g.xGoal][g.yGoal].g = infinity;
            
            state[g.xStart][g.yStart].parent = start;
            state[g.xGoal][g.yGoal].parent = null;
            
            start_arr[i].g = 0;
            start_arr[i].h = getEvaluation(i, start.x , start.y);
            start_arr[i].f = start_arr[i].g + start_arr[i].h*w1;
            
            /*start.g = 0;
            start.h = getEvaluation(i, start.x , start.y);
            start.f = start.g + start.h*w1;*/
         
            
            open[i].insert(start_arr[i]);
        	
        }
        
       closed_anchor = new ArrayList<Node_state>();
       closed_inadm = new ArrayList<Node_state>();
        
        
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
            
            for(int i = 1; i < no_of_heuristics; i++)
            {
            	if(open[i].findMin(i).f <= w2 * open[0].findMin(i).f)
            	{
            		if(state[g.xGoal][g.yGoal].g < open[i].findMin(i).g)
            		{
            		if( state[g.xGoal][g.yGoal].g < infinity)
            		{
            			 System.out.println("Path Found");
                         flag = 1;
                         val_hueristic = i;
                         System.out.println("path cost:" + state[g.xGoal][g.yGoal].g);
                         Node_state cur = state[g.xGoal][g.yGoal];

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
                        findSuccessors(succ,i);
                        closed_inadm.add(succ);
            		}
            	}
            	
            	else
            	{
            		if(state[g.xGoal][g.yGoal].g < open[0].findMin(i).g)
            		{
            			if(state[g.xGoal][g.yGoal].g < infinity)
            			{
            				System.out.println("Path Found");
                            flag = 2;
                            System.out.println("path cost:" + state[g.xGoal][g.yGoal].g);
                            Node_state cur = state[g.xGoal][g.yGoal];

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
                            findSuccessors(succ,0);
                            closed_anchor.add(succ);
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
        	System.out.println("No Solution");
        }
        
        end_time = System.currentTimeMillis();
        elapsed_time = end_time - start_time;

        //currpoint path length
        //counter visited/explored node
        //goal.g cost

        if(!checkopen())
        {
            System.out.println("No solution");
            return null;
        }
        if(flag == 1) {
            p = new PathAttributes(elapsed_time, currpoint, counter, state[g.xGoal][g.yGoal].g);
            return p;
        }
        else if(flag == 2)
        {
        	p = new PathAttributes(elapsed_time, currpoint, counter, state[g.xGoal][g.yGoal].g);
            return p;
        }
        else
        {
        	return null;
        }
    }


    private boolean checkopen() {
		// TODO Auto-generated method stub
    	for(int i = 0; i < no_of_heuristics; i++)
    	{
    		if(open[i].isEmpty())
    		{
    			return false;
    		}
    	}
    	
		return true;
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
    		/*int dx = abs(goal.x- x);
	        int dy = abs(goal.y-y);
	        value = sqrt(pow(min(dx,dy),2) * 2) + max(dy,dx);*/
    		value = sqrt((goal.x-x)*(goal.x-x) + (goal.y-y)*(goal.y-y));
    		break;
    		
    	case 3:
    		  /*double cost = 0;
    		 for(int j = 0; j<8; j++)    {
    	            cost += sqrt((hardPoints[j][0]-x)*(hardPoints[j][0]-x) + (hardPoints[j][1]-y)*(hardPoints[j][1]-y));
    	        }
    		
    		value = 1200 - cost;*/
    		int dx1 = abs(goal.x- x);
	        int dy1 = abs(goal.y-y);
	        value = max(dx1,dy1);
    	break;
    	}
    	
		return value;
	}

	private void findSuccessors(Node_state succ, int j) {
        // TODO Auto-generated method stub
       
		for(int i = 0; i<no_of_heuristics; i++)
		{
			if(i!=j && !open[i].isEmpty())
			{
				open[i].deleteMin();
			}
		}
		
		
		Node_state succ_child;
		double value;
        int[] neighbourx = new int[]{1,1,1,0,0,-1,-1,-1};
        int[] neighboury = new int[]{0,1,-1,-1,1,1,0,-1};

        for(int i = 0; i<8; i++)
        {
            if(!(succ.x + neighbourx[i] < 0) && !(succ.x + neighbourx[i] >= g.ROWS) && !(succ.y + neighboury[i] < 0) && !(succ.y + neighboury[i] >= g.COLUMNS))
            {
                succ_child = state[succ.x+neighbourx[i]][succ.y+neighboury[i]];
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
                        
                        succ_child.h = getEvaluation(0,succ_child.x, succ_child.y);
                        succ_child.f = succ_child.g + succ_child.h*w1;
                        value = succ_child.f;
                        
                        if(!closed_anchor.contains(succ_child) && !succ_child.infringe)
                        {
                        	open[0].insert(succ_child);
                        	succ_child.infringe = true;
                        	
                        	if(!closed_inadm.contains(succ_child))
                        	{
                        		for(int k = 1; k < no_of_heuristics; k++)
                        		{
                        			 succ_child.h = getEvaluation(k,succ_child.x, succ_child.y);
                                     succ_child.f = succ_child.g + succ_child.h*w1;
                                     
                                     if(succ_child.f <= w2*value)
                                     {
                                    	 open[k].insert(succ_child);
                                     }
                        		}
                        	}
                        }

                       /* succ_child.h = getEvaluation(j,succ_child.x, succ_child.y);
                        succ_child.f = succ_child.g + succ_child.h*w1;
                        if(fringe.find(succ_child)!=0)
                        {
                            fringe.delete(fringe.find(succ_child));
                        }
                        else
                        {
                            fringe.insert(succ_child);
                            succ_child.infringe = true;
                        }*/
                    }
                }
            }

        }

    }


    private void initStates() {
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
