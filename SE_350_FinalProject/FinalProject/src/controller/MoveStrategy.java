package controller;

import java.awt.Point;

import model.Enemy;
import model.GameGrid;

public class MoveStrategy {
	GameGrid grid;
	Enemy enemy;
	public MoveStrategy(GameGrid grid, Enemy enemy){
		this.grid = grid;
		this.enemy = enemy;
	}
	public Point move(Point currentPoint){
		return currentPoint; // default is to do nothing
	}
}
