package jp.tonyu.kernel.device;

import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.PatternParserFactory;

public interface Device {
	public Screen getScreen();
	public PatternParserFactory getPatternParserFactory();
}
