package jp.tonyu.samples.ribbon;

import jp.tonyu.debug.Log;
import jp.tonyu.kernel.Array;
import jp.tonyu.kernel.MultiThreadChar;
import jp.tonyu.kernel.PlainChar;
import jp.tonyu.kernel.screen.pattern.CharPattern;

public class ABoot extends RPlainChar implements MultiThreadChar {

	private double ppc;
	private Pend pt;
	private int i;
	// 時間やレベル、ゲームオーバーの処理など、ゲーム全体の管理を行う。
	@Override
	protected void onMouseDown() {
		// ゲームオーバー時に"Replay F9"がクリックされるとゲームを最初から始める
		if (g().time<=0 && !designMode()) {
			g().projectManager.loadPage(g().page_index);
		}
	}
	@Override
	public void run() {
		p=-1;
		ppc=0;
		g().mu=0.95;
		g().ths=new Array();
		// リボンのパーツを配列g().thsにいれる
		for (PlainChar t : g().chars) {
			if (t instanceof Pend){
				Pend p=(Pend)t;
				p.t=null;
				g().ths.add(p);
				if (pt!=null) pt.t=p;
				pt=p;
			}
		} 
		//ft.t=null;
		g().score=0;
		//g().mplayer.play(g().se_Main);
		g().srank=2000;
		g().time=60;
		Log.d(this, g().time);
		g().level=1;
		g().exp=0;

		while(true) {
			update();
			// スコア表示
			drawText(x,y,"Score: "+g().score,color(255,255,255),12,0);
			if (g().tincr!=null) i=g().tincr.i; else i=120;
			// レベル表示
			if (i<60 || (i % 4)<2 )  drawText(400,y, "level:"+g().level,color(155,255,255),12,0);
			// 残り時間表示
			if (g().time>0) {
				drawText(300,y,"Time :"+trunc(g().time),color(255,255,255));
				g().time=g().time-0.017;
			} else {
				// ゲームオーバーならばReplay表示
				drawText(300,y,"Replay F9",color(255,150,155));
				// ハイスコア登録用オブジェクトを画面に表示
				//g().regist.setVisible(1);  
			}
			// 経験値がレベル*5に達するとレベルアップ
			if (g().exp>g().level*5 && g().time>0 ) {
				g().tincr=appear(new TimeIncr().construct_TimeIncr(400,y+20,trunc(g().time)*10*g().level ) );
				g().level+=1;
				g().exp=0;
				// 新しいボールが出現する
				appear(new Star().construct_Star(100,-100,g().pat_star+3,0));
			}
		}

	}

}
