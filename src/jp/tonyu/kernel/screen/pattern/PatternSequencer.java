package jp.tonyu.kernel.screen.pattern;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import jp.tonyu.debug.TLog;
import jp.tonyu.kernel.Global;

public class PatternSequencer {
	final Global global;
	final PatternParserFactory factory;
	final Vector<CharPattern> all=new Vector<CharPattern>();
	public PatternSequencer(Global global, PatternParserFactory factory) {
		super();
		this.global = global;
		this.factory = factory;
	}
	public int add(Object imgRsc) throws IOException {
		TLog.d("tonyu", "PatSeq:start...");
		PatternParser p = factory.newPatternParser(imgRsc);
		List<CharPattern> pats = p.parse();
		//String n = imgRsc.name().replaceAll("\\..*$", "").replaceAll("[^a-zA-Z0-9_]", "");
		int res = all.size();
		//global.setGlobal("pat_"+n, res);
		TLog.d("tonyu", "PatSeq:done sz="+res);
		for (CharPattern pa:pats) {
			all.add(pa);
		}
		TLog.d("tonyu", "PatSeq:done...");
		return res;
	}
	public void clear() {
		all.clear();
	}
	public CharPattern convert(Object p) {
		if (p instanceof CharPattern) {
			CharPattern pp = (CharPattern) p;
			return pp;
		}
		if (p instanceof Number) {
			Number n = (Number) p;
			int i=n.intValue();
			if (i>=0 && i<all.size()) return all.get(i);
		}
		return null;
	}
}
