package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Font font = new Font("ComicSans", Font.PLAIN, 26);
	Image lavaBootImg, waterBootImg;
	
   public ScorePanel(){
	   
	   JLabel textLabel2 = new JLabel("Items:");
	   textLabel2.setFont(font);
	   textLabel2.setForeground(Color.BLACK);
	   this.add(textLabel2);
	   	   
//	   JLabel textLabel2 = new JLabel("Blue Boots:");
//	   textLabel2.setFont(font);
//	   textLabel2.setForeground(Color.BLUE);
//	   this.add(textLabel2);
//	   
//	   JLabel textLabel3 = new JLabel("Gray Boots:");
//	   textLabel3.setFont(font);
//	   textLabel3.setForeground(Color.LIGHT_GRAY);
//	   this.add(textLabel3);
	   
//	   try {
//			lavaBootImg = ImageIO.read(new File("src/images/lavaBoots.png"));
//			lavaBootImg = lavaBootImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);
//
//		} catch (IOException e) {
//			System.out.println("Can't find Water Boot image");
//		}
//	   try {
//			waterBootImg = ImageIO.read(new File("src/images/waterBoots.png"));
//			waterBootImg = waterBootImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);
//
//		} catch (IOException e) {
//			System.out.println("Can't find Lava Boot image");
//		}
	   
	   setVisible(true);
   }
   
   public void paintLavaBoot(){
	   JLabel textLabel = new JLabel("Lava Boots");
	   textLabel.setFont(font);
	   textLabel.setForeground(Color.RED);
	   this.add(textLabel);
	   revalidate();
   }
   
   public void paintWaterBoot(){
	   JLabel textLabel = new JLabel("Water Boots");
	   textLabel.setFont(font);
	   textLabel.setForeground(Color.BLUE);
	   this.add(textLabel);
	   revalidate();
   }

   public Dimension getPreferredSize(){
        return new Dimension(250,200);
   }

    public Dimension getMinimumSize(){
        return getPreferredSize();
    }

}



