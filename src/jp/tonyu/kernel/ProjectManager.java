package jp.tonyu.kernel;

import java.io.IOException;

public class ProjectManager {
	public final Boot boot;
	public ProjectManager(Boot boot) {
		super();
		this.boot = boot;
	}
	public void loadPage(Page p) {
		try {
			p.load(boot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
