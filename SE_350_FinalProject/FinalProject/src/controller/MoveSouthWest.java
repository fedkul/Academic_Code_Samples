package controller;

import java.awt.Point;

import model.Enemy;
import model.GameGrid;

public class MoveSouthWest extends MoveStrategy {

	public MoveSouthWest(GameGrid grid, Enemy enemy) {
		super(grid,enemy);
	}

	@Override
	public Point move(Point currentPoint) {
		if(!grid.isBlocked(currentPoint.x -+1,currentPoint.y-1) && enemy.inBounds(currentPoint.x-1,currentPoint.y-1))
			return new Point(currentPoint.x-1,currentPoint.y-1);
		else
			return currentPoint;
	}
}