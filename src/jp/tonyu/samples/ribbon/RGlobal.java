package jp.tonyu.samples.ribbon;

import java.io.IOException;

import jp.tonyu.kernel.Array;
import jp.tonyu.kernel.Boot;
import jp.tonyu.kernel.Global;
import jp.tonyu.kernel.Page;
import jp.tonyu.samples.ribbon.page.Index;

/**
 * This class defines "RibbonMania"-specific global variables
 * @author hoge1e3
 *
 */

public class RGlobal extends Global {

	public Flash Flsh;
	public double time;
	public Page page_index;
	public double mu;
	public Array ths;
	public int score;
	public int srank;
	public int level;
	public int exp;
	public TimeIncr tincr;
	public int pat_star;
	public TRank1 rank;
	public int preg;
	public int gcont;
	public int kasc;
	public ABoot aBoot;
	public Star Star;
	@Override
	public void loadPatterns(Boot boot) throws IOException {
		super.loadPatterns(boot);
		pat_star=boot.loadPattern("ribbon");
	}
	@Override
	public void start(Boot boot) {
		page_index=new Index();
		projectManager.loadPage(page_index);
	}
	
}
