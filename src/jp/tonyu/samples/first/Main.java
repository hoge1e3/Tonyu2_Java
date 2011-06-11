package jp.tonyu.samples.first;

import java.io.File;

import jp.tonyu.kernel.Boot;
import jp.tonyu.kernel.Global;
import jp.tonyu.kernel.device.awt.AWTDevice;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*AWTPatternParser p = new AWTPatternParser(ImageIO.read(new File("Ball.png")));
		List<CharPattern> pats = p.parse();*/
		AWTDevice d=new AWTDevice(new File("image"));
		Boot b = new Boot(d, new Global());
		b.getPatternSequencer().add(d.getResourceList().getImageResource("ball"));
		b.getPatternSequencer().add(d.getResourceList().getImageResource("ribbon"));
		b.appear(new Object1() .construct_PlainChar(50,50,4));
		b.appear(new Object1() .construct_PlainChar(150,30,4));
		while (true) {
			b.move(true);
			Thread.sleep(17);
		}
	}

}
