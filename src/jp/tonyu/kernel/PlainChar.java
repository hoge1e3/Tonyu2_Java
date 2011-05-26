package jp.tonyu.kernel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import jp.tonyu.coroutine.Process;
import jp.tonyu.debug.Log;
import jp.tonyu.kernel.screen.pattern.CharPattern;

public class PlainChar {
	public CharPattern p;
	public double x,y,scaleX=1,scaleY=1,angle=0;
	public PlainChar(int x,int y,CharPattern p) {
		this.x=x;
		this.y=y;
		this.p=p;
	}
	Process proc;
	public void setPrimaryProcess(Process p) {
		proc=p;
	}
	public Boot getBoot() {
		return boot;
	}
	public void setBoot(Boot boot) {
		this.boot = boot;
	}
	public Process getPrimaryProcess() {
		return proc;
	}
	Boot boot;
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

	
	public Graphics2D getGraphics() {
		return Log.notNull(boot, "boot is null").getGraphics();
	}
	public void draw() {
		if (p!=null) {
			Graphics2D g = getGraphics();
			AffineTransform a=new AffineTransform();
			a.translate(x, y);
			a.scale(scaleX, scaleY);
			a.rotate(angle*Math.PI*2/360);
			g.transform(a);
			p.draw(g);
			//Log.d(this,"Draw!"+p);
		}
	}
	public <T extends PlainChar> T appear(T c) {
		
		return c;
	}
}
