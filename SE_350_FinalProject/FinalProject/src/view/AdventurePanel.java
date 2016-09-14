package view;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import controller.KeyPressController;
import model.Chip;
import model.Enemy;
import model.FinishTile;
import model.GameGrid;

public class AdventurePanel extends JPanel{
	private static AdventurePanel adventurePanel;
	private static final long serialVersionUID = 1L;
	KeyPressController kpc;
	Chip chip;
	FinishTile finish;
	GameGrid grid;
	int cellSize = 35;    
	ArrayList<Enemy> enemies;
	Image diamondImg, triadImg, chipImg, finishImg, waterBootImg, lavaBootImg;

	public static AdventurePanel getInstance( GameGrid grid, Chip chip, ArrayList<Enemy> enemies, FinishTile finish) {
		if(adventurePanel == null)
			adventurePanel = new AdventurePanel(grid,chip,enemies, finish);
		return adventurePanel; 
	}

	private AdventurePanel(GameGrid grid, Chip chip, ArrayList<Enemy> enemies, FinishTile finish) {

		this.chip = chip;
		this.grid = grid;
		this.enemies = enemies;
		this.finish = finish;
		kpc = new KeyPressController(this,grid,chip);
		this.addKeyListener(kpc);
		this.setFocusable(true);
		
		grid.blockCells();
		setBackground(Color.GRAY);

		try {
			lavaBootImg = ImageIO.read(new File("src/images/lavaBoots.png"));
			lavaBootImg = lavaBootImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);

		} catch (IOException e) {
			System.out.println("Can't find Triad image");
		}
	   try {
			waterBootImg = ImageIO.read(new File("src/images/waterBoots.png"));
			waterBootImg = waterBootImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);

		} catch (IOException e) {
			System.out.println("Can't find Triad image");
		}
		
		try {
			triadImg = ImageIO.read(new File("src/images/Triad.png"));
			triadImg = triadImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);

		} catch (IOException e) {
			System.out.println("Can't find Triad image");
		}

		try {
			diamondImg = ImageIO.read(new File("src/images/Diamond.png"));
			diamondImg = diamondImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);

		} catch (IOException e) {
			System.out.println("Can't find Diamond image");
		}
		try {
			chipImg = ImageIO.read(new File("src/images/chip.png"));
			chipImg = chipImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);

		} catch (IOException e) {
			System.out.println("Can't find Chip image");
		}
		try {
			finishImg = ImageIO.read(new File("src/images/portal.png"));
			finishImg = finishImg.getScaledInstance(35,35,Image.SCALE_SMOOTH);

		} catch (IOException e) {
			System.out.println("Can't find finish tile image");
		}
	}

	public void drawGrid(Graphics2D g2){
		// Draw horizontal lines
		for(int j = 1; j <= grid.getYDimension()+1; j++)
			g2.drawLine(cellSize, j*cellSize, cellSize+ (grid.getXDimension()*cellSize), j*cellSize);

		// Draw vertical lines
		for(int j = 0; j <= grid.getXDimension(); j++)
			g2.drawLine(j*cellSize+ cellSize, cellSize, j*cellSize+cellSize, cellSize+ grid.getYDimension()*cellSize);

		// Draw blocked cells
		g2.setPaint(Color.WHITE);
		for(int i = 0; i < grid.getXDimension(); i++){
			for(int j = 0; j < grid.getYDimension(); j++){
				if(grid.isBlocked(i,j))        	
					g2.fillRect(i*cellSize+cellSize,j*cellSize+ cellSize,cellSize,cellSize);        	
			}
		}         
		// Draw water cells
		g2.setPaint(Color.BLUE);
		for(int i = 0; i < grid.getXDimension(); i++){
			for(int j = 0; j < grid.getYDimension(); j++){
				if(grid.isWater(i,j))        	
					g2.fillRect(i*cellSize+cellSize,j*cellSize+ cellSize,cellSize,cellSize);        	
			}
		}   
		// Draw lava cells
		g2.setPaint(Color.RED);
		for(int i = 0; i < grid.getXDimension(); i++){
			for(int j = 0; j < grid.getYDimension(); j++){
				if(grid.isLava(i,j))        	
					g2.fillRect(i*cellSize+cellSize,j*cellSize+ cellSize,cellSize,cellSize);        	
			}
		}         
	}

	public void drawEnemies(Graphics2D g2){
		// Draw enemies
		for(Enemy enemy : enemies){
			if(enemy.getName().equals("Horizontal Enemy"))
				g2.drawImage(diamondImg, enemy.getX()*cellSize+cellSize,enemy.getY()*cellSize+cellSize,null);
			else if (enemy.getName().equals("Vertical Enemy"))
				g2.drawImage(triadImg, enemy.getX()*cellSize+cellSize,enemy.getY()*cellSize+cellSize,null);
			else if (enemy.getName().equals("Finish Tile"))
				g2.drawImage(finishImg, enemy.getX()*cellSize+cellSize,enemy.getY()*cellSize+cellSize,null);
			else if (enemy.getName().equals("Water Boot"))
				g2.drawImage(waterBootImg, enemy.getX()*cellSize+cellSize,enemy.getY()*cellSize+cellSize,null);
			else if (enemy.getName().equals("Lava Boot"))
				g2.drawImage(lavaBootImg, enemy.getX()*cellSize+cellSize,enemy.getY()*cellSize+cellSize,null);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		drawGrid(g2); 
		
		drawEnemies(g2);
		
		// Draw Chip
		g2.drawImage(chipImg, chip.getX()*cellSize+cellSize, chip.getY()*cellSize+cellSize,null);
	}

	public Dimension getPreferredSize(){
		return new Dimension(200,200);
	}

	public Dimension getMinimumSize(){
		return getPreferredSize();
	}
	
	public void reset(){
		adventurePanel = null;
	}
}
