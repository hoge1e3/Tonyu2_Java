package jp.tonyu.kernel;

import jp.tonyu.coroutine.Process;
import jp.tonyu.debug.Log;
import jp.tonyu.kernel.screen.pattern.CharPattern;

public class PlainChar {
	public CharPattern p;
	public double x,y,scaleX=1,scaleY=1,angle=0,alpha=255,zOrder=0;
	public boolean f=false;
	public PlainChar(int x,int y,CharPattern p) {
		this.x=x;
		this.y=y;
		this.p=p;
	}
	public Global g() {
		return getBoot().getGlobal();
	}
	Process proc;
	public void setPrimaryProcess(Process p) {
		proc=p;
	}
	public Boot getBoot() {
		return Log.notNull(boot, "boot is null");
	}
	public void setBoot(Boot boot) {
		this.boot = boot;
	}
	public Process getPrimaryProcess() {
		return proc;
	}
	private Boot boot;
	Runnable state;
	public void update() {
		onUpdate();
		proc.suspend();
	}
	protected void onUpdate() {
		
	}
	boolean isDead=false;
	public boolean isDead() {
		return isDead;
	}
	public void die() {
		isDead=true;
	}

	
	/*public Graphics2D getGraphics() {
		return getBoot().getGraphics();
	}*/
	public void draw() {
		if (p!=null) {
			/*Graphics2D g = getGraphics();
			
			AffineTransform a=new AffineTransform();
			a.translate(x, y);
			a.scale(scaleX, scaleY);
			a.rotate(angle*Math.PI*2/360);
			g.transform(a);
			g.setComposite(AlphaComposite.getInstance(
				      AlphaComposite.SRC_OVER, (float)alpha/255));
			p.draw(g);*/
			getBoot().getScreen().addImageSprite(x,y,p,f,zOrder,angle,alpha,scaleX,scaleY);
			
			//Log.d(this,"Draw!"+p);
		}
	}
	public <T extends PlainChar> T appear(T c) {
		getBoot().appear(c);
		return c;
	}
}
