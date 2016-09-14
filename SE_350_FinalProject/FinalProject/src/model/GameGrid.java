package model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;

/**
 * @author Jane Cleland-Huang
 * GameGrid manages the grid.  
 * Currently creates blocked cells and provides a function to specify whether a cell is blocked or not
 * Modified to make it a Singleton!!  
 */
public class GameGrid {
	private static GameGrid grid = null;
	public static final int xDimension = 25;
	public static final int yDimension = 25;
	Random rand;
	
	HashSet<String> blockedCells = new HashSet<String>();
	HashSet<String> waterCells = new HashSet<String>();
	HashSet<String> lavaCells = new HashSet<String>();
	
	public static GameGrid getInstance(){
		if (grid == null){
			grid = new GameGrid();
		}
		return grid;	
	}
	
	private GameGrid(){
		rand = new Random();
	}	
	
	public int getXDimension(){
		return xDimension;		
	}
	
	public int getYDimension(){
		return yDimension;
	}
	
	public void addBlockedCell(String newBlock){
		blockedCells.add(newBlock);
	}
	
	public void removeBlockedCell(String oldBlock){
		if(blockedCells.contains(oldBlock))
			blockedCells.remove(oldBlock);
	}
	
	public void addWaterCell(String newBlock){
		blockedCells.add(newBlock);
		waterCells.add(newBlock);
	}
	
	public void removeWaterCell(String oldBlock){
		if(blockedCells.contains(oldBlock))
			blockedCells.remove(oldBlock);
		if(waterCells.contains(oldBlock))
			waterCells.remove(oldBlock);
	}
	
	public void unblockWaterCell(String oldBlock){
		if(blockedCells.contains(oldBlock))
			blockedCells.remove(oldBlock);
	}
	
	public void addLavaCell(String newBlock){
		blockedCells.add(newBlock);
		lavaCells.add(newBlock);
	}
	
	public void removeLavaCell(String oldBlock){
		if(blockedCells.contains(oldBlock))
			blockedCells.remove(oldBlock);
		if(lavaCells.contains(oldBlock))
			lavaCells.remove(oldBlock);
	}
	
	public void unblockLavaCell(String oldBlock){
		if(blockedCells.contains(oldBlock))
			blockedCells.remove(oldBlock);
	}
	
	public String buildHashString(int x, int y){
		return ((Integer)x).toString() + "-" + ((Integer)y).toString();		
	}
	
	public void blockCells()
	{
		BuildRoomLayout roomBuilder = new BuildRoomLayout(this);
		
		/*for(int j = 0; j < blockCount; j++){
			String blockedCell = buildHashString(rand.nextInt(xDimension)+1, rand.nextInt(yDimension));
			if(!blockedCells.contains(blockedCell))
				blockedCells.add(blockedCell);
			else
				j--;   
		}
		*/
	}
	
	public Point findFreeCell(Point topLeft, Point bottomRight){
		Point p = null;
		while (true) {
			int newX = rand.nextInt(bottomRight.x-topLeft.x) + topLeft.x;
			int newY = rand.nextInt(bottomRight.y-topLeft.y) + topLeft.y;
			if(!isBlocked(newX, newY)){
				p = new Point(newX, newY);
				break;
			}			
		}
		return p;  // For now we assume we can find an empty spot.
	}
	
	
	public Point XfindFreeCell(Point topLeft, Point bottomRight){
		Point p = null;
		while (true) {
			int newX = rand.nextInt(xDimension);
			int newY = rand.nextInt(yDimension);
			if(!isBlocked(newX, newY)){
				p = new Point(newX, newY);
				break;
			}			
		}
		return p;  // For now we assume we can find an empty spot.
	}
	
	public boolean inBounds(int x, int y){
		if((x>=0 && x < xDimension)&& (y >=0 && y < yDimension)) 
			return true;
		else
			return false;
	}
	
	public Point findFreeNeighbor(Point currentPoint){
		Point newPoint = null;
		int counter = 6;  // Maximum times willing to try to move.
		while (true) {
			int moveX = rand.nextInt(2);
			if(moveX == 0)
				moveX--;
			int moveY = rand.nextInt(2);
			if (moveY == 0)
				moveY--;
			int currentX = currentPoint.x + moveX;
			int currentY = currentPoint.y + moveY;
			if(!isBlocked(currentX,currentY) && inBounds(currentX, currentY)){
				newPoint = new Point(currentX,currentY);
				break;
			}			
			if(counter++ > 6)
				break;
		}
		if (newPoint == null)
			return currentPoint;
		else
			return newPoint;  // For now we assume we can find an empty spot.
	}
	
	public Boolean isBlocked(int x, int y)
	{
		if(blockedCells.contains(buildHashString(x,y)))
			return true;
		else
			return false;
	}	
	
	public Boolean isWater(int x, int y)
	{
		if(waterCells.contains(buildHashString(x,y)))
			return true;
		else
			return false;
	}

	public boolean isLava(int x, int y) {
		if(lavaCells.contains(buildHashString(x,y)))
			return true;
		else
			return false;
	}	
}
