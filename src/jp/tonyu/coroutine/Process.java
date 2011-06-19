package jp.tonyu.coroutine;

public interface Process {
	public boolean usesThread();
	public void suspend();
	public void resume();
	public boolean isKilled();
	public boolean isKillCompleted();
	public void kill();
}
