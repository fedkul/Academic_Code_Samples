package controller;

import java.awt.Point;

import model.Enemy;
import model.GameGrid;

public class MoveNorth extends MoveStrategy {

	public MoveNorth(GameGrid grid, Enemy enemy) {
		super(grid,enemy);
	}

	@Override
	public Point move(Point currentPoint) {
		if(!grid.isBlocked(currentPoint.x,currentPoint.y-1)&& enemy.inBounds(currentPoint.x,currentPoint.y-1))
			return new Point(currentPoint.x,currentPoint.y-1);
		else
			return currentPoint;
	}
}