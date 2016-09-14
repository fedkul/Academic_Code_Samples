package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class LevelPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Font font = new Font("ComicSans", Font.PLAIN, 24);
	
	   public LevelPanel(){
		   setBackground(Color.WHITE);
		   JLabel textLabel = new JLabel("Level 1");
		   textLabel.setFont(font);
		   textLabel.setForeground(Color.BLACK);
		   this.add(textLabel);
		   
		   setVisible(true);
	   }	 

	   public Dimension getPreferredSize(){
	        return new Dimension(100,50);
	   }

	    public Dimension getMinimumSize(){
	        return getPreferredSize();
	    }


}
