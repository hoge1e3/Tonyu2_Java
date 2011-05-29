package jp.tonyu.kernel.screen.sprite;

public abstract class Sprite {
	double zOrder;
	public double getZOrder() {
		return zOrder;
	}
	public void setZOrder(double zOrder) {
		this.zOrder = zOrder;
	}
}