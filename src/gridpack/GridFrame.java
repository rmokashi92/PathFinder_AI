package gridpack;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
   A frame that shows a n * n grid.
*/
public class GridFrame extends JFrame
{
   
	
	public GridFrame()
   {  
      

      // Use helper methods 
     // createTextField();
      //createButton();
      createPanel();
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }
/*
   private void createTextField()
   {
      countLabel = new JLabel("Count");

      final int FIELD_WIDTH = 10;
      countField = new JTextField(FIELD_WIDTH);
      countField.setText(10 + "");
      
    
   }

   private void createButton()
   {
      button = new JButton("Draw");
      
      
      class GridListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
         
        	GridComponent component = new GridComponent();
        	panel.add(component);
        	add(panel);
        	createPanel();
            
         }            
      }
      
      ActionListener listener = new GridListener();
      button.addActionListener(listener);
      
      
   }
  */
        
   
   private void createPanel()
   {
      panel = new JPanel();
     // panel.add(countLabel);
      //panel.add(countField);
      
        
      //panel.add(button);
      
  	  CommonTags.component = new GridComponent();
       ToolTipManager.sharedInstance().registerComponent(CommonTags.component);
  	CommonTags.component.setPreferredSize(new Dimension(1024, 1024));
      panel.add(CommonTags.component);
      add(panel);
   }
      
   private JLabel countLabel;
   private JTextField countField;
   

   private JButton button;


   private JPanel panel;

 
   private static final int FRAME_WIDTH = 10000;
   private static final int FRAME_HEIGHT = 10000;
   

   
}


