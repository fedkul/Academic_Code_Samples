package model;

import java.util.Observable;

public class WaterBoot extends Enemy{

	public WaterBoot() {
		name = "Water Boot";
	}

	@Override
	public void doAction(Observable obj) {
		Chip tmp = (Chip) obj;
		tmp.setWaterBoot(true);
		//move into score panel (hopefully)
		//this.p.setLocation(tmp.game.scorePanel.getHeight()/4, tmp.game.scorePanel.getX()+35);
		tmp.game.scorePanel.paintWaterBoot();
		try {
			Thread.currentThread().join(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void move() {
		// no moving!
	}

}