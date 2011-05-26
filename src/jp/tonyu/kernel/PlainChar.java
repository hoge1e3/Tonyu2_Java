package jp.tonyu.kernel;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import jp.tonyu.coroutine.Process;
import jp.tonyu.screen.pattern.CharPattern;

public class PlainChar {
	public CharPattern p;
	public double x,y,scaleX=1,scaleY=1,angle=0;
	Process proc;
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

	
	public Graphics getGraphics() {
		return boot.getGraphics();
	}
	public void draw() {
		if (p!=null) {
			Graphics g = getGraphics();
			AffineTransform a=new AffineTransform();
			a.rotate(angle);
			a.scale(scaleX, scaleY);
			a.translate(x, y);
			p.draw(g);
		}
	}
	public <T extends PlainChar> T appear(T c) {
		
		return c;
	}
}
