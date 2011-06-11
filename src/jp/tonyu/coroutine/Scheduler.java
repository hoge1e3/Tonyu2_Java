package jp.tonyu.coroutine;

import java.util.List;
import java.util.Vector;

import jp.tonyu.debug.Log;


public class Scheduler  {
	private List<Process> procs=new Vector<Process>();
	private List<Process> willAdd=new Vector<Process>();
	public synchronized void resume() {
		notify();
	}
	public void add(Process a) {
		willAdd.add(a);
	}
	public synchronized void runAll() {
		Vector<Process> willKilled = new Vector<Process>();
		//Log.d(this, "Procs "+procs);
		for (Process a:procs) {
			if (a.isKilled()) {
				willKilled.add(a);
				continue;
			}
			try {
				a.resume();
				if (a.usesThread()) wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (Process a:willKilled) {
			procs.remove(a);
		}
		for (Process a:willAdd) {
			procs.add(a);
		}
		willAdd.clear();
	}
}
