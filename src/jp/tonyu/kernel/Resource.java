package jp.tonyu.kernel;

import java.io.InputStream;
import java.io.OutputStream;

public interface Resource {
	public InputStream read();
	public String name();
	public OutputStream write();
}
