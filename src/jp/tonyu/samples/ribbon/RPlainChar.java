package jp.tonyu.samples.ribbon;

import jp.tonyu.kernel.Page;
import jp.tonyu.kernel.PlainChar;

public class RPlainChar extends PlainChar {
	public Page page_index;
	@Override
	public RGlobal g() {
		return (RGlobal)getBoot().getGlobal();
	}
}
