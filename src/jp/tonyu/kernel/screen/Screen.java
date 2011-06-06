package jp.tonyu.kernel.screen;

import jp.tonyu.kernel.screen.pattern.CharPattern;
import jp.tonyu.kernel.screen.sprite.ImageSprite;
import jp.tonyu.kernel.screen.sprite.LineSprite;
import jp.tonyu.kernel.screen.sprite.TextSprite;

public interface Screen {
	public ImageSprite addImageSprite(double x, double y, 
			 CharPattern p, boolean f, double zOrder,
			 double angle, double alpha,
			 double scaleX, double scaleY) ;
	public TextSprite addTextSprite(double x, double y,String text,int color,double size,double zOrder);
	public LineSprite addLineSprite(double sx, double sy, double dx, double dy, int color);
	void drawSprites();

	void clearSprites();
	
	public int getWidth();
	public int getHeight();
	int getMouseX();
	int getMouseY();
}
