package model;

import java.util.Observable;

public class LavaBoot extends Enemy{

	public LavaBoot() {
		name = "Lava Boot";
	}

	@Override
	public void doAction(Observable obj) {
		Chip tmp = (Chip) obj;
		tmp.setLavaBoot(true);
		
	}

	@Override
	public void move() {
		// no moving!
		
	}

}
