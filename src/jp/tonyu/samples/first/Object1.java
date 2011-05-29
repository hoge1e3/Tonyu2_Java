package jp.tonyu.samples.first;

import jp.tonyu.debug.Log;
import jp.tonyu.kernel.MultiThreadChar;
import jp.tonyu.kernel.PlainChar;
import jp.tonyu.kernel.screen.pattern.CharPattern;

public class Object1 extends PlainChar implements MultiThreadChar {

	public Object1(int x, int y, CharPattern p) {
		super(x, y, p);
	}

	@Override
	public void run() {
		while (x<300) {
			x++;
			angle++;
			scaleX=x/100;
			scaleY=x/100;
			alpha--;
			//Log.d(this, "x="+x);
			update();
		}
	}

}
