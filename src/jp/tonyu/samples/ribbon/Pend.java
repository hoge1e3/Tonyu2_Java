package jp.tonyu.samples.ribbon;

import jp.tonyu.kernel.MultiThreadChar;

public class Pend extends RPlainChar implements MultiThreadChar {

	public Pend t;
	private double mu;
	private double ospd;
	private double spd;
	private double thr;
	private double grav;
	private double cspd;
	private double vx;
	private double vy;
	private double tx;
	private double ty;
	private double acc;
	@Override
	public void run() {

		// リボンのパーツ(節点)。このオブジェクトをたくさん並べてリボンを構成する
		mu=0.70;
		ospd=0.1;
		spd=ospd;
		thr=0;
		grav=0.3;
		while(true) {
			cspd=vx*vx+vy*vy;
			// tは次のパーツ。tがない部分はマウスによって位置を指定する。
			if (t!=null) {
				tx=t.x;ty=t.y;
			} else {
				tx=g().mouseX;
				ty=g().mouseY;
			}
			// 次のパーツの位置まで移動しようとする
			vx+=(tx-x)*spd;
			vy+=(ty-y)*spd+grav;
			drawLine(x,y,tx,ty,color(trunc(cspd*20),trunc(255-cspd*20),255) );

			vx=vx*mu;
			vy=vy*mu;

			x+=vx;
			y+=vy;
			if(cspd<thr*thr) {
				spd=ospd;
				acc=rnd()*rnd()*8;
				vx=vx*acc;
				acc=rnd()*rnd()*8;
				vy=vy*acc;
				thr=1.5+rnd();
			}
			p=-1;
			update();
		}

	}

}
