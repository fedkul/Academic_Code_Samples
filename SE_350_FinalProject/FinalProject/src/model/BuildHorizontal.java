package model;

public class BuildHorizontal extends EnemyFactory{

	public BuildHorizontal() {
		super();
	}

	@Override
	Enemy buildEnemy() {
		return new HorizontalEnemy();
	}

}
