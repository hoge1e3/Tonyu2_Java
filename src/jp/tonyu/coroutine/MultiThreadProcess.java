package jp.tonyu.coroutine;



public abstract class MultiThreadProcess implements Process {
	Scheduler scheduler;
	int cnt=0;
	public MultiThreadProcess(Scheduler s) {
		scheduler=s;
	}
	boolean started=false;
	boolean killed=false, killCompleted=false;

	@Override
	public boolean usesThread() {
		return true;
	}
	public synchronized void suspend() {
		try {
			scheduler.resume();
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public abstract void run();
	public synchronized void kill() {
		killed=true;
	}
	@Override
	public synchronized void resume() {
		if (started) {
			notify();
		} else {
			started=true;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						MultiThreadProcess.this.run();
					} catch (ProcessKilledException e) {

					} finally {
						kill();
						killCompleted=true;
						scheduler.resume();
					}
				}
			}).start();
		}
	}
	@Override
	public boolean isKilled() {
		return killed;
	}
	@Override
	public boolean isKillCompleted() {
		return killCompleted;
	}
}
