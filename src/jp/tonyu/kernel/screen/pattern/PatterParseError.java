package jp.tonyu.kernel.screen.pattern;

public class PatterParseError extends Exception {
	final PatternParser parser;
	final int x, y;
	public PatterParseError(PatternParser parser, int x, int y) {
		super();
		this.parser = parser;
		this.x = x;
		this.y = y;
	}
	
}
