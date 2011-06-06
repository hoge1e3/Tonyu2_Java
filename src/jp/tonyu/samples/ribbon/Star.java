package jp.tonyu.samples.ribbon;

import jp.tonyu.debug.Log;
import jp.tonyu.kernel.MultiThreadChar;
import jp.tonyu.kernel.PlainChar;



public class Star extends RPlainChar implements MultiThreadChar {

	public Star construct_Star(double x, double y, Object p,int f) {
		construct_PlainChar(x, y, p);
		return this;
	}


	void crash() {
		int i;
		// ボールを破壊する
		i=0;
		g().exp+=1; // 経験値 +1
		// ランク（マウスの静止時間）によりbad,good,greatを判定する
		if (g().rank.rank<10) {
			// Bad
			// g().mplayer.play(g().se_bad);
			g().preg=0;
			g().gcont=1; 
			// 破片を6個出現させる
			while (i<360) {
				appear(new TBomb().construct_TBomb(x,y,cos(i)*5,sin(i)*5));
				i=i+60;
			}
		} else if (g().rank.rank<20) {
			// Good
			//g().mplayer.play(g().se_good);
			g().preg=0;
			g().gcont=1; 
			// 破片を12個出現させる
			while (i<360) {
				appear(new TBomb().construct_TBomb(x,y,cos(i)*5,sin(i)*5));
				i=i+30;
			}
		}
		else {
			// Great。  画面がフラッシュする
			g().Flsh.doFlash();
			//g().mplayer.play(g().se_great);
			// g().pregが1なら前回もGreatなので、Greatコンボ数(g().gcont)を増やす
			if (g().preg!=0) g().gcont=g().gcont+1; else g().gcont=1;
			g().preg=1;
			// 破片を18個出現させる
			while (i<360) {
				appear(new TBomb().construct_TBomb(x,y,cos(i)*5,sin(i)*5));
				i=i+60;
			}
			i=15; 
			while (i<360) {
				appear(new TBomb().construct_TBomb(x,y,cos(i)*10,sin(i)*10));
				i=i+30;
			}
		} 
		// Bad Good Great(+コンボ数) を表示する
		appear (new TRank2().construct_TRank2(x,y,g().rank.rank,g().gcont));

	}

	boolean crashTo2(PlainChar t) {
		return  (dist(t.x-x,t.y-y)<35);
	}
	double vx,vy;
	public double life;
	public int cy;
	private int bp;
	private int an;
	private int maxs;
	private int maxlife;
	void ath () {
		int i;
		double dl,dm;
		PlainChar t,pt = null;
		// リボンとボールの当たり判定
		i=g().ths.size()-1;
		dl=0;
		while(i>=0) {
			// g().ths(リボンのパーツを格納した配列)の各要素をtに代入しながら繰り返す
			t=(PlainChar) g().ths.get(i);
			if (crashTo2(t)) {
				// tとぶつかっていたら、力を受ける
				vx=vx+(x-t.x)*0.4;
				vy=vy+(y-t.y)*0.4;
				dl=1;
				if (pt!=null) {
					dm=dist(pt.x-t.x,pt.y-y)*0.8+20;
					//print (dm);
					if (dm>dl) dl=dm;
				}
			}
			pt=t;
			i=i-1;
		}
		if (dl>=0) { 
			// ぶつかっていたら、lifeを減らす
			//if (g().kasc==0) g().mplayer.play(g().se_kasuri); else g().mplayer.play(g().se_kasuri2);
			g().kasc=1-g().kasc;
			appear(new TBomb().construct_TBomb(x,y,-vx,-vy));
			life=life-dl*0.15;
			g().score=g().score+1;
		}
	}
	@Override
	public void run() {
		cy=g().level;
		bp=(Integer)p;
		an=0;
		g().mu=0.99;
		maxs=6;
		maxlife=100;
		life=maxlife*0.5;
		vx=0;vy=-1;
		y=-150;
		Log.d(this,g().time);
		/*while (p!=null) {
			p=3;
			x=100;
			y=100;
			drawText(x, y, "AAAA"+scaleX+" "+scaleY+" "+alpha+" ", color(255,255,255));
			update();
		}*/
		while (g().time>0.8) {
			//Log.d(this, "x="+x+" y="+y+" p="+p);
			// アニメーション
			an=an+1;
			if (an>2) an=0;
			p=bp+an;
			// 画面端にぶつかったら跳ね返る
			if (x<0) vx=abs(vx);
			if (x>g().screenWidth) vx=-abs(vx);
			if (y>g().screenHeight) vy=-abs(vy);
			if (y<-110) vy=1;
			// 空気抵抗を受ける。g().muは抵抗係数
			x+=vx;vx=vx*g().mu;
			y+=vy;vy=vy*g().mu;
			// 当たり判定
			ath();
			// 落下
			vy=vy+0.03;
			if (vy>maxs) vy=maxs;
			if (vy<-maxs) vy=-maxs;
			if (vx>maxs) vx=maxs;
			if (vx<-maxs) vx=-maxs;
			// ライフが少しずつ回復する
			if (life<maxlife) life+=0.5;
			// ライフゲージ表示
			drawLine(0,g().screenHeight-10-cy,life,g().screenHeight-10-cy,color(0,255-cy*20,0));
			if (life<0) {
				// ライフが0になったら破壊される。画面上方に移動
				crash();
				life=maxlife;
				//appear(new PTS(x,y+30,g().rank.rank*4,g().gcont));
				y=-30;vy=0;
				g().score=g().score+50+g().rank.rank*4*g().gcont;
				g().time=g().time+g().rank.rank*0.05*g().gcont;
			}
			if (y>g().screenHeight) {
				// 下に落ちたらゲームオーバー
				y=-30;vy=0;
				g().time=g().time*0;//g().time-1-g().score/1500;
				//g().mplayer.stop();
			} 
			update();
		}

		g().rank.rank=0;
		crash();
	}

}
