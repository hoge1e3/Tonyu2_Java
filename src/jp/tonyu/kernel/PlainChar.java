package jp.tonyu.kernel;

import jp.tonyu.coroutine.Process;

public class PlainChar {
	Process proc;
	Boot boot;
	public void update() {
		onUpdate();
		proc.suspend();
	}
	protected void onUpdate() {
		
	}
	boolean isDead=false;
	public boolean isDead() {
		return isDead;
	}
	public void die() {
		isDead=true;
	}
	public void run() {
		
	}
	public <T extends PlainChar> T appear(T c) {
		
		return c;
	}
}
