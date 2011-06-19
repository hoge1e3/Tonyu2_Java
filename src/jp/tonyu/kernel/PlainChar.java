package jp.tonyu.kernel;

import jp.tonyu.coroutine.Process;
import jp.tonyu.debug.Log;
import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.TRect;
import jp.tonyu.kernel.screen.pattern.CharPattern;
import jp.tonyu.kernel.screen.sprite.Sprite;

public class PlainChar {
	public Object p;
	public double x,y,scaleX=1,scaleY=1,angle=0,alpha=255,zOrder=0;
	public boolean f=false;
	public TRect bounds;

	public boolean designMode() {
		return false;
	}
	public void onAppear() {
	}
	public int color(int r,int g,int b,int a) {
		if (r<=0) r=0; if(r>255) r=255;
		if (g<=0) g=0; if(g>255) g=255;
		if (b<=0) b=0; if(b>255) b=255;
		if (a<=0) a=0; if(a>255) a=255;
		if (a>=128) a=a-256;
		int k=1;
		int res=0;
		res+=b*k; k*=256;
		res+=g*k; k*=256;
		res+=r*k; k*=256;
		res+=a*k;
		return res;
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
		if (proc.isKilled()) {
			die();
			throw new RuntimeException("Process is killed");
		}
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
		proc.kill();
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
			if (p instanceof Integer && (Integer)p>=30) {
				//Log.d(this, " x="+x+"  y="+y+" pp="+pp+" p="+p);
			}
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
		if (g().doDraw) {
			Sprite s=getScreen().addTextSprite(x, y, text, col, size, zOrder);
			s.setGenerator(this);
		}
	}
	public void drawText(double x, double y, String string, int color) {
		if (g().doDraw) drawText(x,y,string,color,12,0);
	}
	public void drawLine(double sx,double sy,double dx,double dy,int color) {
		if (g().doDraw) getScreen().addLineSprite(sx, sy, dx, dy, color);
	}
	public double rnd() {
		return Math.random();
	}
}
