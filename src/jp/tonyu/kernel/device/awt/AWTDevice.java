package jp.tonyu.kernel.device.awt;

import java.io.File;

import jp.tonyu.kernel.device.Device;
import jp.tonyu.kernel.device.ResourceList;
import jp.tonyu.kernel.screen.Screen;
import jp.tonyu.kernel.screen.pattern.PatternParserFactory;

public class AWTDevice implements Device {
	AWTScreen screen;
	AWTPatternParserFactory f;
	
	public AWTDevice(File images) {
		screen=new AWTScreen();
		f=new AWTPatternParserFactory();
		awtResourceList = new AWTResourceList(images);
	}
	final AWTResourceList awtResourceList;
	@Override
	public Screen getScreen() {
		return screen;
	}

	@Override
	public PatternParserFactory getPatternParserFactory() {
		return f;
	}
	@Override
	public ResourceList getResourceList() {
		return awtResourceList;
	}
}
