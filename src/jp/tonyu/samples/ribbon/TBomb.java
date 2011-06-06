package jp.tonyu.samples.ribbon;

import jp.tonyu.kernel.MultiThreadChar;

public class TBomb extends RPlainChar implements MultiThreadChar {
	double vx,vy;
	public TBomb construct_TBomb(double x, double y, double vx,double vy) {
		construct_PlainChar(x, y, null);
		this.vx=vx;
		this.vy=vy;
		return this;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
