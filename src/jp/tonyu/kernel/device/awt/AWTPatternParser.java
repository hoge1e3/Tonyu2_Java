package jp.tonyu.kernel.device.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import jp.tonyu.debug.Log;
import jp.tonyu.kernel.resource.FileResource;
import jp.tonyu.kernel.screen.pattern.CharPattern;
import jp.tonyu.kernel.screen.pattern.PatterParseError;
import jp.tonyu.kernel.screen.pattern.PatternParser;
import jp.tonyu.util.SPrintf;

public class AWTPatternParser implements PatternParser {
	final int width,height;
	final BufferedImage buf;
	final int base;
	final Image img;
	public AWTPatternParser(Image img) {
		this.img=img;
		height = img.getHeight(null);
		width = img.getWidth(null);
		buf=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		buf.getGraphics().drawImage(img,0,0,null);
		base=buf.getRGB(0,0);
	}
	@Override
  	public List<CharPattern> parse() {
  		try {
  			Vector<CharPattern> res=new Vector<CharPattern>();
			for (int y=0; y<height ;y++) {
				for (int x=0; x<width ;x++) {
					int c=buf.getRGB(x, y);
					if (c!=base) {
						res.add(parse1Pattern(x,y));
					}
					//System.out.printf("%08x ",c);
				}
				//System.out.println("\n");
			}
			return res;
  		} catch (PatterParseError p) {
  			return Collections.singletonList((CharPattern)new AWTCharPattern(img));
  		}
	}
  	private AWTCharPattern parse1Pattern(int x, int y) throws PatterParseError {
		int trans=buf.getRGB(x, y);
		int dx=x,dy=y;
		while (dx<width) {
			if (buf.getRGB(dx,dy)!=trans) break;
			dx++;
		}
		if (dx>=width || buf.getRGB(dx,dy)!=base) throw new PatterParseError(this,dx,dy);
		dx--;
		while (dy<height) {
			if (buf.getRGB(dx,dy)!=trans) break;
			dy++;
		}
		if (dy>=height || buf.getRGB(dx,dy)!=base) throw new PatterParseError(this,dx,dy);
		Log.d(this, SPrintf.sprintf("x=%d y=%d dx=%d dy=%d",x,y,dx,dy));
		dy--;
		int sx=x+1,sy=y+1,w=dx-sx,h=dy-sy;
		if (w*h==0) throw new PatterParseError(this, dx, dy); 
		for (int ey=sy ; ey<dy ; ey++) {
			for (int ex=sx ; ex<dx ; ex++) {
				if (buf.getRGB(ex, ey)==trans) {
					buf.setRGB(ex, ey, 0);
				}
			}
		}
		BufferedImage i=new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Log.d(this, SPrintf.sprintf("%d %d %d %d %d %d",w,h,sx,sy,dx,dy));
		i.getGraphics().drawImage(buf, 0, 0, w, h, sx, sy, dx, dy, null);
		Graphics g = buf.getGraphics();
		g.setColor(new Color(base,true));
		g.fillRect(x,y, w+2, h+2);
		return new AWTCharPattern(i);
	}
	public static void main(String[] args) throws Exception {
  		File src=new File("bukiset.png");
  		Image img=ImageIO.read(src);
  		List<CharPattern> pats = new AWTPatternParser(img).parse();
		System.out.println( pats );
  		AWTScreen s = new AWTScreen();
  		Graphics2D g = (Graphics2D) s.getBuffer().getGraphics();
  		for (double r=0 ; r<1 ; r+=0.01) {
  			AffineTransform t=new AffineTransform();
  			t.translate(100,100);
  			t.rotate(r);
  			t.translate(-100,-100);
  			t.translate(r*100, r*100);
  			//t.scale(r+1, r+1);
  			g.setTransform(t);
  			s.clearSprites();
  	  		int x=30,y=50;
  			for (CharPattern p:pats) {
  				g.drawImage(((AWTCharPattern)p).getImg(),x,y,null);
  				x+=20;
  				y+=10;
  			}
  			s.redraw();
  			Thread.sleep(17);
  		}
	}
}
