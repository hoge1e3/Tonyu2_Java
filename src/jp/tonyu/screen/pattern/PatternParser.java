package jp.tonyu.screen.pattern;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import jp.tonyu.util.Util;

public class PatternParser {
	final int width,height;
	final BufferedImage buf;
	final int base;
	final Image img;
	public PatternParser(Image img) {
		this.img=img;
		height = img.getHeight(null);
		width = img.getWidth(null);
		buf=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		buf.getGraphics().drawImage(img,0,0,null);
		base=buf.getRGB(0,0);
	}
  	public List<CharPattern> parse() {
  		try {
			for (int y=0; y<height ;y++) {
				for (int x=0; x<width ;x++) {
					int c=buf.getRGB(x, y);
					if (c!=base) {
						parse1Pattern(x,y);
					}
					System.out.printf("%08x ",c);
				}
				System.out.println("\n");
			}
  		} catch (PatterParseError p) {
  			return Collections.singletonList(new CharPattern(img));
  		}
		return null;
	}
  	private CharPattern parse1Pattern(int x, int y) throws PatterParseError {
		int trans=buf.getRGB(x, y);
		int dx=x,dy=y;
		while (dx<width) {
			if (buf.getRGB(dx,dy)!=trans) break;
			dx++;
		}
		if (dx>=width || buf.getRGB(dx,dy)!=base) throw new PatterParseError(this,dx,dy);
		while (dy<height) {
			if (buf.getRGB(dx,dy)!=trans) break;
			dy++;
		}
		if (dy>=height || buf.getRGB(dx,dy)!=base) throw new PatterParseError(this,dx,dy);
		int sx=x+1,sy=y+1,w=dx-sx,h=dy-sy;
		BufferedImage i=new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		i.getGraphics().drawImage(buf, 0, 0, w, h, sx, sy, dx, dy, null);
		
	}
	public static void main(String[] args) throws Exception {
  		File src=new File("110209_035228.png");
  		Image img=ImageIO.read(src);
  		new PatternParser(img).parse();
	}
}
