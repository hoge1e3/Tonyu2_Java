package jp.tonyu.kernel.screen.sprite;

public class TextSprite extends Sprite {
	public TextSprite(double x, double y, String text, int color,
			double size, double zOrder) {
		this.x=x;this.y=y;
		this.text=text;
		this.col=color;
		this.size=size;
		this.zOrder=zOrder;
	}
	public String text;
	public String font;
	public int col;
	public double size;
	public double x,y;
}
