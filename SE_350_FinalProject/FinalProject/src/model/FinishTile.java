package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public class FinishTile extends Enemy{
	
	public FinishTile() {
		grid = GameGrid.getInstance();
		setColor();
		name = "Finish Tile";
	}
	
	public FinishTile(int j, int i) {
		grid = GameGrid.getInstance();
		setBounds();
		setColor();
		setPoint(j, i);
		name = "Finish Tile";
	}
	
	public void run() {
		while (true){
			//do nothing											  
		}
	}
	@Override
	public void setColor(){
		color = Color.YELLOW; // default
	}

	@Override
	public void doAction(Observable obj){
		((Chip) obj).game.win();
	}

	@Override
	public void move() {
		// do nothing
		
	}
}
