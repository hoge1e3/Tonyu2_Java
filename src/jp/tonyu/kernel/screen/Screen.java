package jp.tonyu.kernel.screen;

import jp.tonyu.kernel.screen.pattern.CharPattern;
import jp.tonyu.kernel.screen.sprite.ImageSprite;

public interface Screen {
	public ImageSprite addImageSprite(double x, double y, 
			 CharPattern p, boolean f, double zOrder,
			 double angle, double alpha,
			 double scaleX, double scaleY) ;

	void drawSprites();

	void clearSprites();
}
