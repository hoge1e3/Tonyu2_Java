package jp.tonyu.kernel.screen.pattern;

import jp.tonyu.kernel.device.awt.AWTPatternParser;

public class PatterParseError extends Exception {
	final PatternParser parser;
	final int x, y;
	final String mesg;
	public PatterParseError(PatternParser parser, int x, int y, String mesg) {
		super();
		this.parser = parser;
		this.x = x;
		this.y = y;
		this.mesg=mesg;
	}
	@Override
	public String toString() {
		return "(PatterParseError:"+mesg+" "+x+","+y+")";
	}

}
