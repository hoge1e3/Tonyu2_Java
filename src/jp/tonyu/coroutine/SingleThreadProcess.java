package jp.tonyu.coroutine;

public abstract class SingleThreadProcess implements Process {
	boolean kill;
	@Override
	public boolean isKilled() {
		return kill;
	}
	public void kill() {
		kill=true;
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

}
