package jp.tonyu.samples.ribbon;

import java.io.File;

import jp.tonyu.kernel.Boot;
import jp.tonyu.kernel.device.awt.AWTDevice;
import jp.tonyu.samples.ribbon.page.Index;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*AWTPatternParser p = new AWTPatternParser(ImageIO.read(new File("Ball.png")));
		List<CharPattern> pats = p.parse();*/
		AWTDevice d=new AWTDevice(new File("image"));
		RGlobal g = new RGlobal();
		Boot b = new Boot(d, g);
		d.getScreen().setBoot(b);
		g.page_index=new Index();
		g.projectManager.loadPage(g.page_index);
		b.doLoop();
	}



}
