package jp.tonyu.kernel.screen.sprite;

import java.util.Collection;

import jp.tonyu.kernel.PlainChar;
import jp.tonyu.kernel.screen.TRect;

public abstract class Sprite {
	double zOrder;
	public double getZOrder() {
		return zOrder;
	}
	public void setZOrder(double zOrder) {
		this.zOrder = zOrder;
	}
	TRect bounds;
	public void setTRect(TRect r) {
		bounds=r;
	}
	public TRect getTRect() {
		return bounds;
	}
	PlainChar generator;
	public void setGenerator(PlainChar plainChar) {
		generator=plainChar;
	}
	public PlainChar getGenerator() {
		return generator;
	}
	public static Sprite checkClick(Collection<Sprite> list,double x, double y) {
		for (Sprite s:list) {
			TRect r = s.getTRect();
			if (r!=null && r.inRect(x, y)) {
				return s;
			}
		}
		return null;
	}
}