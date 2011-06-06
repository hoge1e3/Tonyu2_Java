package jp.tonyu.kernel.screen.sprite;

public class LineSprite extends Sprite {
	
	public double sx,sy,dx,dy;
	public int color;
	public LineSprite(double sx, double sy, double dx, double dy, int color) {
		super();
		this.sx = sx;
		this.sy = sy;
		this.dx = dx;
		this.dy = dy;
		this.color = color;
	}

}
