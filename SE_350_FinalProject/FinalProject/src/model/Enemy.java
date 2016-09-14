package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import controller.MoveEast;
import controller.MoveNorth;
import controller.MoveNorthEast;
import controller.MoveSouth;
import controller.MoveSouthWest;
import controller.MoveStrategy;
import controller.MoveWest;

public abstract class Enemy implements Runnable, Observer{
	GameGrid grid;
	Point p;
	String name;
	Color color;
	Point topLeftBounds;
	Point bottomRightBounds;
	protected MoveStrategy currentMoveStrategy;
	protected MoveStrategy moveNorth;
	protected MoveStrategy moveSouth;
	protected MoveStrategy moveEast;
	protected MoveStrategy moveWest;
	protected MoveStrategy moveNorthEast;
	protected MoveStrategy moveSouthWest;
	Random rand;

	public Enemy(){
		grid = GameGrid.getInstance();
		createMoveStrategies();
		rand = new Random();
		currentMoveStrategy = setRandomDirection();
		setBounds();
		setColor();
	}

	public void setBounds(){
		topLeftBounds = new Point(1,1);
		bottomRightBounds = new Point(grid.getXDimension()-1,grid.getYDimension()-1);  // default
	}

	public boolean inBounds(int x, int y){
		if((x>=topLeftBounds.x && x <= bottomRightBounds.x)&& (y>= topLeftBounds.y && y <= bottomRightBounds.y))
			//if((x>=0 && x < xDimension)&& (y >=0 && y < yDimension)) 
			return true;
		else
			return false;
	}

	public MoveStrategy setRandomDirection(){
		int dirPicker = rand.nextInt(4);
		switch (dirPicker) {
		case 1:
			currentMoveStrategy = moveNorth;
			break;
		case 2:
			currentMoveStrategy = moveSouth;
			break;
		case 3:
			currentMoveStrategy = moveEast;
			break;
		default:
			currentMoveStrategy = moveWest;
		}
		return currentMoveStrategy;     		
	}

	public void createMoveStrategies(){
		moveNorth = new MoveNorth(grid,this);
		moveSouth = new MoveSouth(grid,this);
		moveWest = new MoveWest(grid,this);
		moveEast = new MoveEast(grid,this);
		moveNorthEast = new MoveNorthEast(grid,this);
		moveSouthWest = new MoveSouthWest(grid,this);
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return p.x;
	}

	public int getY() {
		return p.y;
	}

	public Point getTopLeft(){
		return topLeftBounds;
	}

	public Point getBottomRight(){
		return bottomRightBounds;
	}
	public Color getColor(){
		return color;
	}

	public void setPoint(int x, int y){
		if (p == null)
			p = new Point(x,y);
		else{
			p.x = x;
			p.y = y;
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(((Chip)arg0).x == p.getX() && ((Chip)arg0).y == p.getY()){
			doAction(arg0);
		}
	}
	
	public abstract void doAction(Observable obj);
	public abstract void move();

	public void run() {
		while (true){
			move();												  
		}
	}
	
	public void setColor(){
		color = Color.CYAN; // default
	}
}