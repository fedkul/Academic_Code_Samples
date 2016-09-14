package model;

import java.awt.Color;
import java.awt.Point;

import model.GameGrid;
import controller.MoveStrategy;

public abstract class EnemyFactory {
	GameGrid grid;
	public EnemyFactory(){
		grid = GameGrid.getInstance();
	}
	
	public Enemy createEnemy(int x, int y) {
		Enemy enemy = buildEnemy();
		enemy.setColor();
		Point p = grid.findFreeCell(enemy.getTopLeft(), enemy.getBottomRight());
		enemy.setPoint(x,y);
		return enemy;
	}	
	abstract Enemy buildEnemy();
}
