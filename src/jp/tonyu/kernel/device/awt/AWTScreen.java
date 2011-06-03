package jp.tonyu.kernel.device.awt;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.CharPattern;
import jp.tonyu.kernel.screen.sprite.ImageSprite;
import jp.tonyu.kernel.screen.sprite.TextSprite;

public class AWTScreen extends Frame implements Screen {
	Image buf;
	int width=512,height=384;
	int mx,my;
	int[] keys=new int[256];
	Color bgcolor=new Color(20,80,180);
	private List<AWTDrawable> slist = new Vector<AWTDrawable>(); 
	public AWTScreen() {
		buf=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		clearSprites();
		setSize(width,height);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(1);
			}	
		});
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mx=e.getX();
				my=e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				mx=e.getX();
				my=e.getY();				
			}
		});
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mx=e.getX();
				my=e.getY();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				mx=e.getX();
				my=e.getY();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				mx=e.getX();
				my=e.getY();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				mx=e.getX();
				my=e.getY();
						
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				mx=e.getX();
				my=e.getY();
				
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
	@Override
	public void clearSprites() {
		/*Graphics g = buf.getGraphics();
		g.setColor(bgcolor);
		g.fillRect(0, 0, width, height);		*/
		slist.clear();
	}
	public void redraw() {
		paint(getGraphics());
	}
	@Override
	public void drawSprites() {
		Graphics2D g = (Graphics2D) buf.getGraphics();
		g.setColor(bgcolor);
		g.fillRect(0, 0, width, height);		
		//Log.d(this,slist.size());
		for (AWTDrawable s:slist) {
			s.draw(g);
		}
		redraw();
	}
	@Override
	public ImageSprite addImageSprite(double x, double y, 
			 CharPattern p, boolean f, double zOrder,
			 double angle, double alpha,
			 double scaleX, double scaleY) {
		AWTImageSprite res = new AWTImageSprite(x,y,p,f,zOrder,angle,alpha,scaleX,scaleY);
		slist.add(res);
		return res;
	}
	@Override
	public TextSprite addTextSprite(double x, double y, String text, int color,
			double size, double zOrder) {
		AWTTextSprite res= new AWTTextSprite(x,y,text,color,size,zOrder);
		slist.add(res);
		return res;
	}
}
