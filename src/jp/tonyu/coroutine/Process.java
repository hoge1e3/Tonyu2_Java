package jp.tonyu.coroutine;

public interface Process {
	public boolean usesThread();
	public void suspend();
	public void resume();
	public boolean isKilled();
	public void kill();
}
