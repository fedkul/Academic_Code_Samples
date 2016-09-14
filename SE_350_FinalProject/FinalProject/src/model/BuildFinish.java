package model;

public class BuildFinish extends EnemyFactory{

	public BuildFinish() {
		super();
	}

	@Override
	Enemy buildEnemy() {
		return new FinishTile();
		}

}
