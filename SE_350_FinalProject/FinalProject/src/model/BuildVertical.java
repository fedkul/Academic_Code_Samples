package model;

public class BuildVertical extends EnemyFactory{

	public BuildVertical() {
		super();
	}

	@Override
	Enemy buildEnemy() {
		return new VerticalEnemy();
	}

}
