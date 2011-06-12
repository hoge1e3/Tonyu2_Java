package jp.tonyu.samples.ribbon;

import jp.tonyu.kernel.MultiThreadChar;
import jp.tonyu.kernel.PlainChar;


public class TimeIncr extends RPlainChar implements MultiThreadChar {

	public int i;
	private int sc;

	public TimeIncr construct_TimeIncr(double x, double y, int s) {
		  construct_PlainChar(x,y,-1);
		  sc=s;
		  return this;
	}
	@Override
	public void draw() {
		  drawText(x,y,"Bonus:"+sc,color(200,240,100));

	}
	@Override
	public void run() {

		i=120;
		while (i>0) {
		  i-=1;
		  update();
		}
		while (sc>0) {
		  g().score+=10;
		  sc-=10;
		  update();
		}
	}

}
