package jp.tonyu.kernel;

import java.awt.Color;

import jp.tonyu.coroutine.Process;
import jp.tonyu.debug.Log;
import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.CharPattern;

public class PlainChar {
	public Object p;
	public double x,y,scaleX=1,scaleY=1,angle=0,alpha=255,zOrder=0;
	public boolean f=false;
	
	public boolean designMode() {
		return false;
	}
	public int color(int r,int g,int b,int a) {
		return new Color(r,g,b,a).getRGB();
	}
	public int color(int r,int g,int b) {
		return color(r,g,b,255);
	}
	public double sin(double deg) {
		return Math.sin(deg/360*2*Math.PI);
	}
	public double cos(double deg) {
		return Math.cos(deg/360*2*Math.PI);
	}
	public double abs(double v) {
		return Math.abs(v);
	}

	public double dist(double x,double y) {
		return Math.sqrt(x*x+y*y);
	}
	public int trunc(double d) {
		return Math.round((float)d);
	}
	/*public PlainChar(double x,double y,Object p) {
		this.x=x;
		this.y=y;
		this.p=p;
	}*/
	public PlainChar construct_PlainChar(double x,double y,Object p) {
		this.x=x;
		this.y=y;
		this.p=p;
		return this;
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
			CharPattern pp = getBoot().getPatternSequencer().convert(p);
			getScreen().addImageSprite(x,y,pp,f,zOrder,angle,alpha,scaleX,scaleY);
			
			//Log.d(this,"Draw!"+p);
		}
	}
	public Screen getScreen() {
		return getBoot().getScreen();
	}
	public <T extends PlainChar> T appear(T c) {
		getBoot().appear(c);
		return c;
	}
	protected void onMouseDown() {
		// TODO Auto-generated method stub
		
	}
	public void drawText(double x, double y,String text,int col,double size, double zOrder) {
		getScreen().addTextSprite(x, y, text, col, size, zOrder);
	}
	public void drawText(double x, double y, String string, int color) {
		drawText(x,y,string,color,12,0);
	}
	public void drawLine(double sx,double sy,double dx,double dy,int color) {
		getScreen().addLineSprite(sx, sy, dx, dy, color);
	}
}
