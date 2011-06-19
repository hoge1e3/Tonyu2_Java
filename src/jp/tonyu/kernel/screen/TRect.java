package jp.tonyu.kernel.screen;

public class TRect {
	public final double x,y,w,h;
	public boolean inRect(double px,double py) {
		return px>=x && py>=y && px<x+w && py<y+h; 
	}
	public TRect(double x, double y, double w, double h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
}
