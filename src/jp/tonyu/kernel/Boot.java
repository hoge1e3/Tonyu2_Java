package jp.tonyu.kernel;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

import jp.tonyu.coroutine.MultiThreadProcess;
import jp.tonyu.coroutine.Process;
import jp.tonyu.coroutine.Scheduler;
import jp.tonyu.coroutine.SingleThreadProcess;

public class Boot {
	List<PlainChar> chars=new Vector<PlainChar>();
	Graphics grp;
	Scheduler scheduler;
	public Boot(Graphics g,Scheduler s) {
		grp=g;scheduler=s;
	}
	public void run() {
		
	}
	public void appear(final PlainChar c) {
		if (c instanceof MultiThreadChar) {
			final MultiThreadChar m = (MultiThreadChar) c;
			scheduler.add(new MultiThreadProcess(scheduler) {
				
				@Override
				public void run() {
					m.run();				
				}
			});
			
		}
		if (c instanceof StateChar) {
			final StateChar s = (StateChar) c;
			c.state=new Runnable() {				
				@Override
				public void run() {
					s.main();
				}
			};
			scheduler.add(new SingleThreadProcess() {
				@Override
				public void move() {
					if (c.state!=null) c.state.run();
				}
			});
			
		}
	}
	public Graphics getGraphics() {
		return grp;
	}
}
