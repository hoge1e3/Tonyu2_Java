package jp.tonyu.kernel.device.awt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import jp.tonyu.debug.Log;
import jp.tonyu.kernel.screen.sprite.TextSprite;

public class AWTTextSprite extends TextSprite implements AWTDrawable {

	public AWTTextSprite(double x, double y, String text, int color,
			double size, double zOrder) {
		super(x,y,text,color,size,zOrder);
	}

	@Override
	public void draw(Graphics2D g) {
		Font fn = new Font(font, 0, (int)size);
		g.setFont(fn);
		g.setColor(new Color(col));
		g.drawString(text, (int)x, (int)y);
		Log.d(this, col+" x ="+x+"y="+y);
	}

}
