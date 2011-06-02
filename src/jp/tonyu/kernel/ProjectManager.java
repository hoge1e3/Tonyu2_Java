package jp.tonyu.kernel;

public class ProjectManager {
	public final Boot boot;
	public ProjectManager(Boot boot) {
		super();
		this.boot = boot;
	}
	public void loadPage(Page p) {
		p.load(boot);
	}
}
