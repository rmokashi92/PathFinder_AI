package gridpack;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseEvent;

import static gridpack.CommonTags.*;

public class GridComponent extends JComponent
{
	int count = 0;
	int count_y = CommonTags.ROWS;
	int count_x = CommonTags.COLUMNS;
	//int map[][] = new int[count_x][count_y];
    int size = 6;
	
	public void paintComponent(Graphics g)
	{
		
		//makemap();
		
	Graphics2D g2 = (Graphics2D) g;
	

	
		for( int i = 0; i < count_x; i ++)
		{
			for( int j = 0; j < count_y; j++)
			{
				Rectangle grid = new Rectangle( 10 + i * size, 10 + j * size, size, size);
				
				if(CommonTags.grid[j][i].equals(CLEAR))
				{
				g2.setColor(Color.WHITE);
				g2.fillRect(10 + i * size, 10 + j * size, size, size);
				g2.draw(grid);
				}
				if(CommonTags.grid[j][i].equals(HARD))
				{
				g2.setColor(Color.GRAY);
				g2.fillRect(10 + i * size, 10 + j * size, size, size);
				g2.draw(grid);
				}
				if(CommonTags.grid[j][i].equals(BLOCKED))
				{
				g2.setColor(Color.BLACK);
				g2.fillRect(10 + i * size, 10 + j * size, size, size);
				g2.draw(grid);
				}
				if(CommonTags.grid[j][i].startsWith(RIVER))
				{
				g2.setColor(Color.blue);
				g2.fillRect(10 + i * size, 10 + j * size, size, size);
				g2.draw(grid);
				}
                if(CommonTags.grid[j][i].startsWith(HARDRIVER))
                {
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(10 + i * size, 10 + j * size, size, size);
                    g2.draw(grid);
                }
                if(gridcopy[j][i] == "99")
                {
                    g2.setColor(Color.RED);
                    g2.fillRect(10 + i * size, 10 + j * size, size, size);
                    g2.draw(grid);
                }
                if(gridcopy[j][i] == "111")
                {
                    g2.setColor(Color.GREEN);
                    g2.fillRect(10 + i * size, 10 + j * size, size, size);
                    g2.draw(grid);
                }
                if(gridcopy[j][i] == "112")
                {
                    g2.setColor(Color.GREEN);
                    g2.fillRect(10 + i * size, 10 + j * size, size, size);
                    g2.draw(grid);
                }
			}
		}
	 }

   @Override
    public String getToolTipText(MouseEvent event) {
        Point p = new Point(event.getX(), event.getY());
        String t = "(" + Integer.toString((event.getX()-10)/size) + "," + Integer.toString((event.getY()-10)/size) /*+ "," + Double.toString(state[(event.getY()-10)/size][(event.getX()-10)/size].f) */+ ")";

        return t;
    }
	/*private void makemap() {
		// TODO Auto-generated method stub
		for( int i = 0; i < count_x; i ++)
		{
			for( int j = 0; j < count_y; j++)
			{
				if((i+j)%2 == 0)
				{
					map[i][j] = 0;
				}
				else
				{
					map[i][j] = 1;
				}
			}
		
	}
}*/
}
	
	
