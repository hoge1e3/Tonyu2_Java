package jp.tonyu.samples.ribbon;

import jp.tonyu.kernel.Page;
import jp.tonyu.kernel.PlainChar;
import jp.tonyu.kernel.screen.pattern.CharPattern;

public class RPlainChar extends PlainChar {
	public Page page_index;
	public RPlainChar(int x, int y, CharPattern p) {
		super(x, y, p);
		// TODO Auto-generated constructor stub
	}
	@Override
	public RGlobal g() {
		return (RGlobal)getBoot().getGlobal();
	}
}
