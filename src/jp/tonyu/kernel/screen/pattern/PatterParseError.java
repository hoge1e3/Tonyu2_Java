package jp.tonyu.kernel.screen.pattern;

import jp.tonyu.kernel.device.awt.AWTPatternParser;

public class PatterParseError extends Exception {
	final AWTPatternParser parser;
	final int x, y;
	public PatterParseError(AWTPatternParser parser, int x, int y) {
		super();
		this.parser = parser;
		this.x = x;
		this.y = y;
	}
	
}
