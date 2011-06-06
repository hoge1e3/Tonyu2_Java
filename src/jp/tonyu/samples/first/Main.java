package jp.tonyu.samples.first;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import jp.tonyu.coroutine.Scheduler;
import jp.tonyu.kernel.Boot;
import jp.tonyu.kernel.Global;
import jp.tonyu.kernel.device.awt.AWTCharPattern;
import jp.tonyu.kernel.device.awt.AWTDevice;
import jp.tonyu.kernel.device.awt.AWTScreen;
import jp.tonyu.kernel.device.awt.AWTPatternParser;
import jp.tonyu.kernel.resource.FileResource;
import jp.tonyu.kernel.screen.pattern.CharPattern;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*AWTPatternParser p = new AWTPatternParser(ImageIO.read(new File("Ball.png")));
		List<CharPattern> pats = p.parse();*/
		AWTDevice d=new AWTDevice();
		Boot b = new Boot(d, new Global());
		b.getPatternSequencer().add(new FileResource(new File("image/Ball.png")));
		b.getPatternSequencer().add(new FileResource(new File("image/Ribbon.png")));
		b.appear(new Object1() .construct_PlainChar(50,50,4));
		b.appear(new Object1() .construct_PlainChar(150,30,4));
		while (true) {
			b.move(true);
			Thread.sleep(17);
		}
	}

}
