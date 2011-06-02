package jp.tonyu.kernel.device.awt;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.tonyu.kernel.resource.Resource;
import jp.tonyu.kernel.screen.pattern.PatternParser;
import jp.tonyu.kernel.screen.pattern.PatternParserFactory;

public class AWTPatternParserFactory implements PatternParserFactory {

	@Override
	public PatternParser newPatternParser(Resource r) throws IOException {
		Image i=ImageIO.read(r.read());
		return new AWTPatternParser(i);
	}

}
