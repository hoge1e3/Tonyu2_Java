package jp.tonyu.samples.first;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import jp.tonyu.coroutine.Scheduler;
import jp.tonyu.kernel.Boot;
import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.PatternParser;
import jp.tonyu.kernel.screen.pattern.SpritePattern;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		PatternParser p = new PatternParser(ImageIO.read(new File("Ball.png")));
		List<SpritePattern> pats = p.parse();
		Screen scr = new Screen();
		Scheduler sch = new Scheduler();
		Boot b = new Boot(scr, sch);
		b.appear(new Object1(50,50,pats.get(3)));
		while (true) {
			b.move();
			Thread.sleep(17);
		}
	}

}
