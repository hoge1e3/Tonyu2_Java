package jp.tonyu.samples.ribbon;

import jp.tonyu.kernel.MultiThreadChar;

public class TRank2 extends RPlainChar implements MultiThreadChar {


	int rank,gcont;
	public TRank2 construct_TRank2(double x, double y, int rank, int gcont) {
		construct_PlainChar(x,y,null);
		this.rank=rank;
		this.gcont=gcont;
		return this;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
