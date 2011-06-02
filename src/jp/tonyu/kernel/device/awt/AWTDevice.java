package jp.tonyu.kernel.device.awt;

import jp.tonyu.kernel.device.Device;
import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.PatternParserFactory;

public class AWTDevice implements Device {
	AWTScreen screen;
	AWTPatternParserFactory f;
	public AWTDevice() {
		screen=new AWTScreen();
		f=new AWTPatternParserFactory();
	}
	@Override
	public Screen getScreen() {
		return screen;
	}

	@Override
	public PatternParserFactory getPatternParserFactory() {
		return f;
	}

}
