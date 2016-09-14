package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;

import controller.MoveStrategy;

public class HorizontalEnemy extends Enemy{
	int dirPicker;
	public HorizontalEnemy() {
		super();
		name = "Horizontal Enemy";
		setColor();
		dirPicker = rand.nextInt(2)+1;
		currentMoveStrategy = setRandomDirection();
	}

	public void setColor() {
		color = Color.red;		
	}

	public void setBounds(){
		topLeftBounds = new Point(1,1);
		bottomRightBounds = new Point(23,23); // Technical Debt
	}
	
	@Override
	public void move() {
		Point newPoint = currentMoveStrategy.move(p);
		
		if(newPoint.equals(p)){
			if (dirPicker == 1) dirPicker = 2;
			else dirPicker = 1;
			setRandomDirection();
		}
		p = newPoint;	
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public MoveStrategy setRandomDirection(){
		switch (dirPicker) {
		case 1:
			currentMoveStrategy = moveEast;
			break;
		case 2:
			currentMoveStrategy = moveWest;
			break;
		}
		return currentMoveStrategy;     		
	}
	
	@Override
	public void doAction(Observable obj) {
		// TODO Auto-generated method stub
		
	}

}
