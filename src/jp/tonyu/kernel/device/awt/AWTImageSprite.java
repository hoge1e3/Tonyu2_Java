package jp.tonyu.kernel.device.awt;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import jp.tonyu.kernel.screen.pattern.CharPattern;
import jp.tonyu.kernel.screen.sprite.ImageSprite;

public class AWTImageSprite extends ImageSprite implements AWTDrawable {
	public AWTImageSprite(double x, double y, CharPattern p) {
		super(x,y,p);
	}

	public AWTImageSprite(double x, double y, CharPattern p, boolean f,
			double zOrder, double angle, double alpha, double scaleX,
			double scaleY) {
		super(x, y, p, f, zOrder, angle, alpha, scaleX, scaleY);
	}
	public static final AffineTransform E=new AffineTransform();
	@Override
	public void draw(Graphics2D g) {
		if (p instanceof AWTCharPattern) {
			AffineTransform a=new AffineTransform();
			a.translate(x, y);
			a.scale(scaleX, scaleY);
			a.rotate(angle*Math.PI*2/360);
			g.setTransform(a);
			g.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, (float)alpha/255));

			AWTCharPattern pp = (AWTCharPattern) p;
			pp.draw(g);
			g.setTransform(E);
		}
	}
}
