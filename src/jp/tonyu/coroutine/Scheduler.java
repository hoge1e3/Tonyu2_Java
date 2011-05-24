package jp.tonyu.coroutine;

import java.util.List;
import java.util.Vector;


public class Scheduler  {
	public List<Process> procs=new Vector<Process>();
	public List<Process> willAdd=new Vector<Process>();
	public synchronized void resume() {
		notify();
	}
	public void add(Process a) {
		willAdd.add(a);
	}
	public synchronized void runAll() {
		Vector<Process> willDie = new Vector<Process>();
		for (Process a:procs) {
			if (a.isKilled()) {
				willDie.add(a);
				continue;
			}
			try {
				a.resume();
				if (a.usesThread()) wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		for (Process a:willDie) {
			procs.remove(a);
		}
		for (Process a:willAdd) {
			procs.add(a);
		}
		willAdd.clear();
	}
}
