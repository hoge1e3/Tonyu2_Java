package jp.tonyu.kernel;

import java.util.List;
import java.util.Vector;

import jp.tonyu.coroutine.MultiThreadProcess;
import jp.tonyu.coroutine.Process;
import jp.tonyu.coroutine.Scheduler;
import jp.tonyu.coroutine.SingleThreadProcess;
import jp.tonyu.debug.Log;
import jp.tonyu.kernel.device.Device;
import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.PatternSequencer;

public class Boot {
	List<PlainChar> chars=new Vector<PlainChar>();
	Device device;
	Scheduler scheduler=new Scheduler();;
	Global global;
	PatternSequencer patternSequencer;
	public Boot(Device dev, Global g) {
		device=dev;
		global=g;
		patternSequencer=new PatternSequencer(global, device.getPatternParserFactory());
		startup();
	}
	public PatternSequencer getPatternSequencer() {
		return patternSequencer;
	}
	private void startup() {
	
		global.projectManager=new ProjectManager(this);
		global.chars=chars;
		global.screenWidth=getScreen().getWidth();
		global.screenHeight=getScreen().getHeight();
		Log.d(this, global.screenHeight);
		
	}
	public Global getGlobal() {
		return global;
	}
	final Vector<PlainChar> willAppear=new Vector<PlainChar>();
	public void move() {
		getScreen().clearSprites();
		scheduler.runAll();
		Vector<PlainChar> willDie=new Vector<PlainChar>();
		getGlobal().mouseX=getScreen().getMouseX();
		getGlobal().mouseY=getScreen().getMouseY();
		//Log.d(this, chars.size());
		for (PlainChar c:chars) {
			Process p = c.getPrimaryProcess();
			if (p!=null && p.isKilled()) c.die();
			if (c.isDead()) {
				willDie.add(c);
			}
			c.draw();
			//getScreen().addTextSprite(c.x, c.y, c+"" , 0xffffffff, 12 , 0);
		}
		for (PlainChar a:willDie) {
			chars.remove(a);
		}
		for (PlainChar a:willAppear) {
			chars.add(a);
		}
		willAppear.clear();
		getScreen().drawSprites();
	}
	public <T extends PlainChar> T appear(final T c) {
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
		} else if (c instanceof StateChar) {
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
		} else {
			Log.die(c+" is neigher StateChar not MultthreadChar");
		}
		c.setBoot(this);
		willAppear.add(c);
		return c;
	}
	/*public Graphics2D getGraphics() {
		return (Graphics2D)screen.getBuffer().getGraphics();
	}*/
	public Screen getScreen() {
		return device.getScreen();
	}
}
