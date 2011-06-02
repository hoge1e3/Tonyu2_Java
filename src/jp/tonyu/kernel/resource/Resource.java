package jp.tonyu.kernel.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Resource {
	public InputStream read() throws IOException;
	public String name();
	public OutputStream write() throws IOException;
	public long lastUpdate();
}
