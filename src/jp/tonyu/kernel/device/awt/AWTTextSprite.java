package jp.tonyu.kernel.device.awt;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import jp.tonyu.debug.TLog;
import jp.tonyu.kernel.screen.TRect;
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
		FontMetrics fm = g.getFontMetrics();
		g.setColor(new Color(col));
		g.drawString(text, (int)x, (int)y+fm.getAscent());
		double h=fm.getAscent();
		double w=fm.stringWidth(text);
		setTRect(new TRect(x, y, w, h));
		//Log.d(this, col+" x ="+x+"y="+y);
	}

}
