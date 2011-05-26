package jp.tonyu.kernel.screen;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Screen extends Frame {
	Image buf;
	int width=512,height=384;
	Color bgcolor=new Color(20,80,180);
	public Screen() {
		buf=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		clear();
		setSize(width,height);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(1);
			}	
		});
	}
	public Image getBuffer() {
		return buf;
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(buf, 0,0,null);
	}
	public void clear() {
		Graphics g = buf.getGraphics();
		g.setColor(bgcolor);
		g.fillRect(0, 0, width, height);		
	}
	public void redraw() {
		paint(getGraphics());
	}
}
