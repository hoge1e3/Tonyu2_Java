package jp.tonyu.coroutine;

public abstract class SingleThreadProcess implements Process {
	boolean killed;
	@Override
	public boolean isKilled() {
		return killed;
	}
	public void kill() {
		killed=true;
	}
	@Override
	public void suspend() {
	}
	@Override
	public void resume() {
		move();
	}
	public abstract void move();


	@Override
	public boolean usesThread() {
		return false;
	}
	@Override
	public boolean isKillCompleted() {
		return killed;
	}
}
