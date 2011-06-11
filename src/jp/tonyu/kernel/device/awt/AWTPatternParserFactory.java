package jp.tonyu.kernel.device.awt;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.tonyu.kernel.screen.pattern.PatternParser;
import jp.tonyu.kernel.screen.pattern.PatternParserFactory;

public class AWTPatternParserFactory implements PatternParserFactory {

	@Override
	public PatternParser newPatternParser(Object r) throws IOException {
		if (r instanceof File) {
			File f = (File) r;
			Image i=ImageIO.read(f);
			return new AWTPatternParser(i);
			
		}
		throw new RuntimeException("Cannot handle resource "+r);
	}

}
