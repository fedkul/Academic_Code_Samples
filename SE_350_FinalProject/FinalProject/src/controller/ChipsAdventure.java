package controller;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import view.*;

import javax.swing.JFrame;

import model.*;
import model.GameGrid;

public class ChipsAdventure implements Observer{

	AdventurePanel panel;
	public ScorePanel scorePanel;
	LevelPanel levelPanel;
	WinPanel winPanel;
	
	WindowMaker window;
	
	FinishTile finish;
	Chip chip;
	GameGrid gameGrid;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Thread> threads = new ArrayList<Thread>();
		
	public ChipsAdventure() {
		this.gameGrid = GameGrid.getInstance();
		finish = new FinishTile(12, 2);
		enemies.add(finish);
		createVerticals();
		createHorizontals();
		createBoots();
		this.chip = new Chip(this);
	}
	
	public void startGUI(){		
		int windowWidth = 1600;
		int windowHeight = 1200;
		
	    panel = AdventurePanel.getInstance(gameGrid,chip, enemies, finish);   // Create Panel in View
	    scorePanel = new ScorePanel();
	    levelPanel = new LevelPanel();
	    winPanel = new WinPanel();
	    window = new WindowMaker(panel, scorePanel, levelPanel);  // Create a window and display the panel
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Format the window etc.
	    window.setSize(windowWidth, windowHeight);
	    window.setVisible(true);
	}
	
	public void createVerticals(){
		EnemyFactory enemy = new BuildVertical();
		for(int i = 5; i < 20; i+=4){
			enemies.add(enemy.createEnemy(3,i));
		}
		
	}
	
	public void createHorizontals(){
		EnemyFactory enemy = new BuildHorizontal();
		//enemies.add(enemy.createEnemy());
	}
	public void createBoots(){
		EnemyFactory waterBoot = new BuildWaterBoot();
		EnemyFactory lavaBoot = new BuildLavaBoot();
		enemies.add(waterBoot.createEnemy(3,1));
		enemies.add(lavaBoot.createEnemy(21,1));
	}

	public void alternateStartThreads(){
		PaintController paintController = new PaintController(panel);
		(new Thread(paintController)).start();
		for(Enemy enemy:enemies){
			(new Thread(enemy)).start();
		}
	}
	
	public void startThreads(){
		PaintController paintController = new PaintController(panel);
		(new Thread(paintController)).start();
		for(Enemy enemy:enemies){
	    	threads.add(new Thread(enemy));
	    }
		for(Thread thread:threads){
	          thread.start();
	    }
	}
	
	public void win() {
		window.removeAll();
	    //window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		WindowMaker w2 = new WindowMaker(winPanel);
		w2.setSize(400, 200);
		w2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Format the window etc.
	    w2.setVisible(true);
	}
	
	public void reset(){
		for(Thread thread:threads){
	          try {
				thread.join(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
		window.removeAll();
		window.dispose();
		
		int windowWidth = 1600;
		int windowHeight = 1200;
		
		this.finish = new FinishTile(12, 2);
		this.enemies = new ArrayList<Enemy>();
		this.threads = new ArrayList<Thread>();
		enemies.add(finish);
		createVerticals();
		createHorizontals();
		this.chip = new Chip(this);
		
		panel.reset();
		AdventurePanel panel = AdventurePanel.getInstance(gameGrid ,chip, enemies, finish);
		this.panel = panel;
		scorePanel = new ScorePanel();
	    levelPanel = new LevelPanel();
	    winPanel = new WinPanel();
	    window = new WindowMaker(panel, scorePanel, levelPanel);  // Create a window and display the panel
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Format the window etc.
	    window.setSize(windowWidth, windowHeight);
	    window.setVisible(true);
	    startThreads();
		
//		window.removeAll();
//		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
//		main(null);
	}
	public void close(){
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	public static void main(String[] args){
		// Create main controller objects
		ChipsAdventure chipsAdventure = new ChipsAdventure();   
		
		// Create View objects
	 	chipsAdventure.startGUI();  // Start up the view
	 	
	 	chipsAdventure.startThreads();
	    
	}

	@Override
	public void update(Observable o, Object arg) {
		if(chip.getX() == finish.getX() && chip.getY() == finish.getY()) win();
		
	}
}