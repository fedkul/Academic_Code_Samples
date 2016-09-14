package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WinPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Font font = new Font("ComicSans", Font.PLAIN, 48);
	
	   public WinPanel(){
		   setBackground(Color.WHITE);
		   JLabel textLabel = new JLabel("You Win!");
		   textLabel.setFont(font);
		   textLabel.setForeground(Color.BLACK);
		   this.add(textLabel);
		   
		   setVisible(true);
	   }	

	   public Dimension getPreferredSize(){
	        return new Dimension(200,200);
	   }

	    public Dimension getMinimumSize(){
	        return getPreferredSize();
	    }


}
