package jp.tonyu.kernel.screen.pattern;

import java.io.IOException;

public interface PatternParserFactory {
	PatternParser newPatternParser(Object r) throws IOException;
}
