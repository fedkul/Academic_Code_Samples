package model;

public class BuildLavaBoot extends EnemyFactory{

	public BuildLavaBoot() {
		super();
	}

	@Override
	Enemy buildEnemy() {
		return new LavaBoot();
	}

}
