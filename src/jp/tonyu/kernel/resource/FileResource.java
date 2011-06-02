package jp.tonyu.kernel.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileResource implements Resource {
	File f;
	public FileResource(File f) {
		this.f=f;
	}
	@Override
	public InputStream read() throws IOException {
		return new FileInputStream(f);
	}

	@Override
	public String name() {
		return f.getName();
	}

	@Override
	public OutputStream write() throws IOException {
		return new FileOutputStream(f);
	}
	@Override
	public long lastUpdate() {
		return f.lastModified();
	}
}
