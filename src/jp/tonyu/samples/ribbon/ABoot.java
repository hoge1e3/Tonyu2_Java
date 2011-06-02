package jp.tonyu.samples.ribbon;

import jp.tonyu.kernel.PlainChar;

public class ABoot extends RPlainChar {

	private double ppc;
	// 時間やレベル、ゲームオーバーの処理など、ゲーム全体の管理を行う。
	@Override
	void onMouseDown() {
	  // ゲームオーバー時に"Replay F9"がクリックされるとゲームを最初から始める
	  if (g().time<=0 && !designMode()) {
	   g().projectManager.loadPage(g().page_index);
	 }
	}
	protected void run() {
		p=-1;
		ppc=0;
		g().mu=0.95;
		g().ths=new Array();
		// リボンのパーツを配列g().thsにいれる
		for (PlainChar t : g().chars) {
		 if (t is Pend){
		  t.t=null;
		  g().ths.add(t);
		  if (pt) pt.t=t;
		  pt=t;
		 }
		} 
		//ft.t=null;
		g().score=0;
		g().mplayer.play(g().se_Main);
		g().srank=2000;
		g().time=60;
		g().level=1;
		g().exp=0;

		while(1) {
		 update();
		 // スコア表示
		 drawText(x,y,"Score: "+g().score,color(255,255,255));
		 if (g().tincr) i=g().tincr.i; else i=120;
		 // レベル表示
		 if (i<60 || (i % 4)<2 )  drawText(400,y,strcat("level:",trunc(g().level)),color(155,255,255));
		 // 残り時間表示
		 if (g().time>0) {
		  drawText(300,y,"Time :"+trunc(g().time),color(255,255,255));
		  g().time=g().time-0.017;
		 } else {
		   // ゲームオーバーならばReplay表示
		   drawText(300,y,"Replay F9",color(255,150,155));
		   // ハイスコア登録用オブジェクトを画面に表示
		   g().regist.setVisible(1);  
		 }
		 // 経験値がレベル*5に達するとレベルアップ
		 if (g().exp>g().level*5 && g().time>0 ) {
		    g().tincr=appear(new TimeIncr(400,y+20,trunc(g().time)*10*g().level ) );
		    g().level+=1;
		    g().exp=0;
		    // 新しいボールが出現する
		    appear(new Star(100,-100,g().pat_star+3,0));
		 }
		}

	}

}
