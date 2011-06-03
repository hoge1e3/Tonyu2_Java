package jp.tonyu.samples.ribbon;

public class TBomb extends RPlainChar {
	double vx,vy;
	public TBomb construct_TBomb(double x, double y, double vx,double vy) {
		construct_PlainChar(x, y, null);
		this.vx=vx;
		this.vy=vy;
		return this;
	}

}
