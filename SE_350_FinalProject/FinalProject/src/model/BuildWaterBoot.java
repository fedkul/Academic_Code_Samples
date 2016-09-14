package model;

public class BuildWaterBoot extends EnemyFactory{

	public BuildWaterBoot() {
		super();
	}

	@Override
	Enemy buildEnemy() {
		return new WaterBoot();
	}

}
