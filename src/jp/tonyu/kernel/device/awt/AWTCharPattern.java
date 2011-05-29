package jp.tonyu.kernel.device.awt;

import java.awt.Graphics;
import java.awt.Image;

import jp.tonyu.kernel.screen.pattern.CharPattern;

public class AWTCharPattern implements CharPattern {
	private Image img;
	public AWTCharPattern(Image img) {
		this.img=img;
	}
	public void draw(Graphics g) {
		g.drawImage(getImg(), -getImg().getWidth(null)/2 ,-getImg().getHeight(null)/2 ,null );		
	}

	public Image getImg() {
		return img;
	}
}
