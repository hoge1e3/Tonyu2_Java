package jp.tonyu.screen.pattern;

import java.awt.Graphics;
import java.awt.Image;

public class SpritePattern implements CharPattern {
	Image img;
	public SpritePattern(Image img) {
		this.img=img;
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, -img.getWidth(null)/2 ,-img.getHeight(null)/2 ,null );		
	}
}
