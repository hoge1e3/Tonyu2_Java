package jp.tonyu.samples.ribbon.page;

import java.io.IOException;

import jp.tonyu.kernel.Boot;
import jp.tonyu.kernel.Page;
import jp.tonyu.kernel.device.ResourceList;
import jp.tonyu.samples.ribbon.ABoot;
import jp.tonyu.samples.ribbon.Flash;
import jp.tonyu.samples.ribbon.Pend;
import jp.tonyu.samples.ribbon.RGlobal;
import jp.tonyu.samples.ribbon.Star;
import jp.tonyu.samples.ribbon.TRank1;

public class Index implements Page {

	@Override
	public void load(Boot boot) throws IOException {
		RGlobal g=(RGlobal)boot.getGlobal();
//<cpattern name="$pat_star" src="star.bmp"/>
		ResourceList rlist = boot.getDevice().getResourceList();
		boot.getPatternSequencer().add(rlist.getImageResource("ball"));
		g.pat_star=boot.getPatternSequencer().add(rlist.getImageResource("ribbon"));
		
//<generator className="Pend" name="$pend_22" inst_p="31" inst_x="120" inst_y="101"/> *
		for (int i=0 ; i<15; i++) {
			boot.appear(new Pend().construct_PlainChar(30+i*5, 30+i*5, 11));
		}
//<generator className="ABoot" name="$boot_1" inst_p="3" inst_x="32" inst_y="32"/>
		g.aBoot=(ABoot) boot.appear(new ABoot().construct_PlainChar(32, 32, 5));
//<generator className="Star" name="$star" inst_p="$pat_star+3" inst_x="179" inst_y="7"/>
		g.Star=boot.appear(new Star().construct_Star(179, 7, g.pat_star+3, 0));
//<generator className="TRank1" name="$rank" inst_p="3" inst_x="456" inst_y="341"/>
		g.rank=(TRank1) boot.appear(new TRank1().construct_PlainChar(456,341 , 6));
//<generator className="Flash" name="$Flsh" inst_p="3" inst_x="60" inst_y="67"/>
		g.Flsh=(Flash) boot.appear(new Flash().construct_PlainChar(60, 67, 7));
//<generator className="regist" name="$regist" inst_col="$clWhite" inst_text="%22Register HiScore%22" inst_size="15" inst_x="331" inst_y="63"/>
		
	}
	/*
<resourcelist name="usr">
 <resourcelist name="patterns">
<cpattern name="$pat_star" src="star.bmp"/>
 </resourcelist>
 <resourcelist name="classes">
  <class name="Pend" src="Pend.tonyu" category=""/>
  <class name="ABoot" src="ABoot.tonyu" category=""/>
  <class name="Star" src="Star.tonyu" category=""/>
  <class name="TBomb" src="TBomb.tonyu" category=""/>
  <class name="TRank1" src="TRank1.tonyu" category=""/>
  <class name="TRank2" src="TRank2.tonyu" category=""/>
  <class name="TRank0" src="TRank0.tonyu" category=""/>
  <class name="Flash" src="Flash.tonyu" category=""/>
  <class name="TimeIncr" src="TimeIncr.tonyu" category=""/>
  <class name="PTS" src="PTS.tonyu" category=""/>
  <class name="regist" src="regist.tonyu" category=""/>
 </resourcelist>
 <resourcelist name="gen">
<generator className="Pend" name="$pend_22" inst_p="31" inst_x="120" inst_y="101"/>
<generator className="ABoot" name="$boot_1" inst_p="3" inst_x="32" inst_y="32"/>
<generator className="Pend" name="$pend_23" inst_p="31" inst_x="303" inst_y="226"/>
<generator className="Pend" name="$pend_24" inst_p="31" inst_x="173" inst_y="256"/>
<generator className="Pend" name="$pend_25" inst_p="31" inst_x="244" inst_y="87"/>
<generator className="Pend" name="$pend_26" inst_p="31" inst_x="106" inst_y="183"/>
<generator className="Pend" name="$pend_27" inst_p="31" inst_x="341" inst_y="159"/>
<generator className="Pend" name="$pend_28" inst_p="31" inst_x="191" inst_y="219"/>
<generator className="Pend" name="$pend_29" inst_x="309" inst_y="114" inst_p="31"/>
<generator className="Pend" name="$pend_30" inst_p="31" inst_x="366" inst_y="205"/>
<generator className="Pend" name="$pend_31" inst_p="31" inst_x="172" inst_y="137"/>
<generator className="Pend" name="$pend_32" inst_p="31" inst_x="284" inst_y="186"/>
<generator className="Pend" name="$pend_33" inst_p="31" inst_x="262" inst_y="267"/>
<generator className="Star" name="$star" inst_p="$pat_star+3" inst_x="179" inst_y="7"/>
<generator className="TRank1" name="$rank" inst_p="3" inst_x="456" inst_y="341"/>
<generator className="Pend" name="$pend_34" inst_p="31" inst_x="222" inst_y="147"/>
<generator className="Flash" name="$Flsh" inst_p="3" inst_x="60" inst_y="67"/>
<generator className="regist" name="$regist" inst_col="$clWhite" inst_text="%22Register HiScore%22" inst_size="15" inst_x="331" inst_y="63"/>
 </resourcelist>
 <resourcelist name="sounds">
<SoundEffect name="$se_bad" src="bad.wav"/>
<SoundEffect name="$se_good" src="good.wav"/>
<SoundEffect name="$se_great" src="great.wav"/>
<SoundEffect name="$se_kasuri" src="kasuri.wav"/>
<BGM name="$se_Main" src="Main.mzo"/>
<SoundEffect name="$se_kasuri2" src="kasuri2.wav"/>
 </resourcelist>
 <resourcelist name="global">
  <globalvalue name="$screenWidth" value="563"/>
  <globalvalue name="$screenHeight" value="386"/>
 </resourcelist>
<Ranking name="$ranking">
 <resinst type="string" name="browserURL" value="http://tonyu.kake.info.waseda.ac.jp/TRank/hiscore.cgi"/></Ranking>
</resourcelist>
*/
}
