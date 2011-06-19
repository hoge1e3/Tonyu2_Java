package jp.tonyu.kernel.device.awt;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

import jp.tonyu.kernel.Boot;
import jp.tonyu.kernel.PlainChar;
import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.CharPattern;
import jp.tonyu.kernel.screen.sprite.ImageSprite;
import jp.tonyu.kernel.screen.sprite.LineSprite;
import jp.tonyu.kernel.screen.sprite.Sprite;
import jp.tonyu.kernel.screen.sprite.TextSprite;

public class AWTScreen extends Frame implements Screen {
	Image buf;
	int width=563,height=386+20;
	int mx,my;
	int[] keys=new int[256];
	Color bgcolor=new Color(20,80,180);
	private List<AWTDrawable> slist = new Vector<AWTDrawable>();
	Boot boot;
	public Boot getBoot() {
		return boot;
	}
	public void setBoot(Boot boot) {
		this.boot = boot;
	}
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
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_F3) {
					if (boot!=null) boot.pause();
				}				
			}
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				mx=e.getX();
				my=e.getY()-titleBar;
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				mx=e.getX();
				my=e.getY()-titleBar;
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				mx=e.getX();
				my=e.getY()-titleBar;

			}

			@Override
			public void mousePressed(MouseEvent e) {
				mx=e.getX();
				my=e.getY()-titleBar;
				doMouseDown(mx,my);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mx=e.getX();
				my=e.getY()-titleBar;

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mx=e.getX();
				my=e.getY()-titleBar;

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mx=e.getX();
				my=e.getY()-titleBar;

			}
		});
	}
	public void doMouseDown(double x,double y) {
		Sprite s = Sprite.checkClick((List)slist, x, y);
		if (s!=null) {
			PlainChar g = s.getGenerator();
			if (g!=null) g.onMouseDown(x,y);
		}
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
		g.translate(0, titleBar);
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
	@Override
	public LineSprite addLineSprite(double sx, double sy, double dx, double dy,
			int color) {
		AWTLineSprite res= new AWTLineSprite(sx, sy, dx, dy, color);
		slist.add(res);
		return res;
	}
	@Override
	public double getMouseX(){return mx;}
	@Override
	public double getMouseY(){return my;}
	@Override
	public int getScreenHeight() {
		return getHeight()-titleBar;
	}
	int titleBar=25;
	@Override
	public int getScreenWidth() {
		return getWidth();
	}
}
