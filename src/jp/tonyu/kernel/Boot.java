package jp.tonyu.kernel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Vector;

import jp.tonyu.coroutine.MultiThreadProcess;
import jp.tonyu.coroutine.Process;
import jp.tonyu.coroutine.Scheduler;
import jp.tonyu.coroutine.SingleThreadProcess;
import jp.tonyu.debug.Log;
import jp.tonyu.kernel.screen.Screen;

public class Boot {
	List<PlainChar> chars=new Vector<PlainChar>();
	Screen screen;
	Scheduler scheduler;
	public Boot(Screen g,Scheduler s) {
		screen=g;scheduler=s;
	}
	final Vector<PlainChar> willAppear=new Vector<PlainChar>();
	public void move() {
		scheduler.runAll();
		Vector<PlainChar> willDie=new Vector<PlainChar>();
		//Log.d(this, chars.size());
		screen.clear();
		for (PlainChar c:chars) {
			Process p = c.getPrimaryProcess();
			if (p!=null && p.isKilled()) c.die();
			if (c.isDead()) {
				willDie.add(c);
			}
			c.draw();
		}
		for (PlainChar a:willDie) {
			chars.remove(a);
		}
		for (PlainChar a:willAppear) {
			chars.add(a);
		}
		willAppear.clear();
		screen.redraw();
	}
	public void appear(final PlainChar c) {
		if (c instanceof MultiThreadChar) {
			final MultiThreadChar m = (MultiThreadChar) c;
			MultiThreadProcess pr = new MultiThreadProcess(scheduler) {
				
				@Override
				public void run() {
					m.run();				
				}
			};
			scheduler.add(pr);
			c.setPrimaryProcess(pr);
		}
		if (c instanceof StateChar) {
			final StateChar s = (StateChar) c;
			c.state=new Runnable() {				
				@Override
				public void run() {
					s.main();
				}
			};
			SingleThreadProcess pr = new SingleThreadProcess() {
				@Override
				public void move() {
					if (c.state!=null) c.state.run();
				}
			};
			scheduler.add(pr);
			c.setPrimaryProcess(pr);			
		}
		c.setBoot(this);
		willAppear.add(c);
	}
	public Graphics2D getGraphics() {
		return (Graphics2D)screen.getBuffer().getGraphics();
	}
}
