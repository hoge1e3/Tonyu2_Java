package jp.tonyu.kernel.screen.pattern;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import jp.tonyu.kernel.Global;
import jp.tonyu.kernel.resource.Resource;

public class PatternSequencer {
	final Global global;
	final PatternParserFactory factory;
	final Vector<CharPattern> all=new Vector<CharPattern>();
	public PatternSequencer(Global global, PatternParserFactory factory) {
		super();
		this.global = global;
		this.factory = factory;
	}
	public void add(Resource imgRsc) throws IOException {
		PatternParser p = factory.newPatternParser(imgRsc);
		List<CharPattern> pats = p.parse();
		String n = imgRsc.name().replaceAll("\\..*$", "").replaceAll("[^a-zA-Z0-9_]", "");
		global.setGlobal("pat_"+n, all.size());
		for (CharPattern pa:pats) {
			all.add(pa);
		}
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
