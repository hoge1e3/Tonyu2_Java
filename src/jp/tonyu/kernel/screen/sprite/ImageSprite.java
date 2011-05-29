package jp.tonyu.kernel.screen.sprite;

import jp.tonyu.kernel.screen.pattern.CharPattern;
public class ImageSprite extends Sprite {
	public ImageSprite(double x2, double y2, CharPattern p) {
		this(x2,y2,p,false,1,0,255,1,1);
	}
	public CharPattern p;
	boolean f;
	public double x,y,scaleX,scaleY,angle,alpha;
	// drawDxSprite(x,y,p,f,zOrder,angle,alpha,scaleX,scaleY) 
	public ImageSprite(double x, double y, CharPattern p, boolean f, double zOrder,
			 double angle, double alpha,
			 double scaleX, double scaleY) {
		super();
		this.zOrder= zOrder;
		this.f = f;
		this.p = p;
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.angle = angle;
		this.alpha = alpha;
	}
	
}
