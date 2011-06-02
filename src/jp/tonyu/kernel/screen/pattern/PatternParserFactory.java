package jp.tonyu.kernel.screen.pattern;

import java.io.IOException;

import jp.tonyu.kernel.resource.Resource;

public interface PatternParserFactory {
	PatternParser newPatternParser(Resource r) throws IOException;
}
