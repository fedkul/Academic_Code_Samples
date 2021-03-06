package controller;

import java.awt.Point;

import model.Enemy;
import model.GameGrid;

public class MoveWest extends MoveStrategy {

	public MoveWest(GameGrid grid, Enemy enemy) {
		super(grid, enemy);
	}

	@Override
	public Point move(Point currentPoint) {
		if(!grid.isBlocked(currentPoint.x - 1,currentPoint.y)&& enemy.inBounds(currentPoint.x-1,currentPoint.y))
			return new Point(currentPoint.x-1,currentPoint.y);
		else
			return currentPoint;
	}
}

