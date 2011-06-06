package jp.tonyu.kernel;

import java.io.IOException;

public interface Page {
	public void load(Boot boot) throws IOException;
}
