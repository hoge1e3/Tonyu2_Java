package jp.tonyu.kernel.device.awt;

import java.awt.Color;
import java.awt.Graphics2D;

import jp.tonyu.kernel.screen.sprite.LineSprite;

public class AWTLineSprite extends LineSprite implements AWTDrawable {

	public AWTLineSprite(double sx, double sy, double dx, double dy, int color) {
		super(sx, sy, dx, dy, color);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(color));
		g.drawLine((int)sx, (int)sy, (int)dx, (int)dy);

	}

}
