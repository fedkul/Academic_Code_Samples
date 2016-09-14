package model;

import java.awt.Point;
import java.util.ArrayList;

class WallBuilder{

	GameGrid grid;

	public WallBuilder(GameGrid grid){
		this.grid = grid;
	}

	public String buildHashString(int x, int y){
		return ((Integer)x).toString() + "-" + ((Integer)y).toString();		
	}

	public void blastADoor(Point doorPosition){
		String doorCell = buildHashString(doorPosition.x, doorPosition.y);
		grid.removeBlockedCell(doorCell);
	}
	public void constructWall(Point startPoint, Point endPoint){
		// We only allow straight walls
		if(startPoint.x != endPoint.x && startPoint.y != endPoint.y)
			return; // Don't build a wall.  Throw error message though.

		if(startPoint.x == endPoint.x){
			for (int j = startPoint.y; j <= endPoint.y; j++)
			{
				String blockedCell = buildHashString(startPoint.x,j);
				grid.addBlockedCell(blockedCell);
			}
		}	
		else { // startPoint.y must equal endPoint.y
			for (int j = startPoint.x; j <= endPoint.x; j++)
			{
				String blockedCell = buildHashString(j,startPoint.y);
				grid.addBlockedCell(blockedCell);
			}

		}
	}

}
public class BuildRoomLayout{ 
	GameGrid grid;
	WallBuilder wallBuilder;
	public BuildRoomLayout(GameGrid grid){
		this.grid = grid;
		wallBuilder = new WallBuilder(grid);
		BuildAllRooms();
	}

	public void cutDoors(ArrayList<Point> doors){
		for(Point door:doors)
			wallBuilder.blastADoor(door);
	}


	public void buildARoom(Point topLeft, Point bottomRight){
		// Construct walls
		wallBuilder.constructWall(topLeft, new Point(bottomRight.x, topLeft.y));
		wallBuilder.constructWall(topLeft, new Point(topLeft.x, bottomRight.y));
		wallBuilder.constructWall(new Point(topLeft.x,bottomRight.y), bottomRight);
		wallBuilder.constructWall(new Point(bottomRight.x,topLeft.y), bottomRight);

		// Cut the door
	}

	public void createWaterTiles(){
		for (int i = 18; i < 20; i++){
			for (int j = 7; j < 18; j++){
				String tmp = grid.buildHashString(j, i);
				grid.addWaterCell(tmp);
			}
		}
		for (int i = 3; i < 20; i+=8){
			for (int j = 1; j < 5; j++){
				String tmp = grid.buildHashString(j, i);
				grid.addWaterCell(tmp);
			}
		}
		for (int i = 7; i < 20; i+=8){
			for (int j = 2; j < 6; j++){
				String tmp = grid.buildHashString(j, i);
				grid.addWaterCell(tmp);
			}
		}
	}

	public void createLavaTiles(){
		for (int i = 20; i < 24; i++){
			String tmp = grid.buildHashString(16, i);
			grid.addLavaCell(tmp);
		}
		// so much lava...
		for (int i = 1; i < 18; i++){
			for (int j = 7; j < 9; j++){
				String tmp = grid.buildHashString(j, i);
				grid.addLavaCell(tmp);
			}
		}
		for (int i = 1; i < 18; i++){
			for (int j = 16; j < 18; j++){
				String tmp = grid.buildHashString(j, i);
				grid.addLavaCell(tmp);
			}
		}
	}

	public void BuildAllRooms(){
		ArrayList<Point> doors = new ArrayList<Point>();		
		buildARoom(new Point(0,0),new Point(24,24));
		buildARoom(new Point(9,0),new Point(15,4));
		wallBuilder.constructWall(new Point(6,1), new Point(6,23) );
		wallBuilder.constructWall(new Point(18,1), new Point(18,23) );
		createWaterTiles();
		createLavaTiles();

		doors.add(new Point(12,4));
		doors.add(new Point(6,20));
		doors.add(new Point(6,21));
		doors.add(new Point(18,20));
		doors.add(new Point(18,21));
		//		doors.add(new Point(15,15));
		//		doors.add(new Point(17,3));
		//		doors.add(new Point(19,12));
		cutDoors(doors);

	}
}
