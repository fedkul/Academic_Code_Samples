package model;

import java.util.Observable;

import controller.ChipsAdventure;

public class Chip extends Observable{
	int x;
	int y;
	boolean lavaBoot;
	boolean waterBoot;
	public ChipsAdventure game;
	
	public Chip(ChipsAdventure game){
		x = 12;
		y = 23;
		this.game = game;
		addObservers();
	}
	
	public void setLavaBoot(boolean set){
		lavaBoot = set;
	}
	
	public void setWaterBoot(boolean set){
		waterBoot = set;
	}
	
	public boolean hasLavaBoot(){
		return lavaBoot;
	}
	
	public boolean hasWaterBoot(){
		return waterBoot;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
		setChanged();
		notifyObservers();
	}
	
	public void setY(int y){
		this.y = y;
		setChanged();
		notifyObservers();
	}
	
	void addObservers(){
		for(Enemy enemy:game.getEnemies()){
			addObserver(enemy);
		}
		addObserver(game);
	}
	
	public void kill(){
		
	}
}