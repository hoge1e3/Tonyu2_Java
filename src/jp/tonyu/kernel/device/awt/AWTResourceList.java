package jp.tonyu.kernel.device.awt;

import java.io.File;

import jp.tonyu.kernel.device.ResourceList;

public class AWTResourceList implements ResourceList {
	File images;
	public AWTResourceList(File images) {
		this.images=images;
	}

	@Override
	public Object getImageResource(String name) {
		File f=new File(images,name+".png");
		return f;
	}

}
