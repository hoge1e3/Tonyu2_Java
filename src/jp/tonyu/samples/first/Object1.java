package jp.tonyu.samples.first;

import jp.tonyu.kernel.MultiThreadChar;
import jp.tonyu.kernel.PlainChar;

public class Object1 extends PlainChar implements MultiThreadChar {

	public Object1(int x, int y, Object p) {
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
			if (x % 10==0) p=(Integer)p+1;
			//Log.d(this, "x="+x);
			update();
		}
	}

}
