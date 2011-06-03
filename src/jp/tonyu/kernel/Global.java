package jp.tonyu.kernel;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.List;

public class Global {
	public double mouseX,mouseY;
	public ProjectManager projectManager;
	public List<PlainChar> chars;
	Hashtable<String, Object> hash=new Hashtable<String, Object>();
	public int screenWidth,screenHeight;
	public Object getGlobal(String name) {
		try {
			Class<?> class1 = getClass();
			Field f=class1.getField(name);
			return f.get(this);
		} catch (NoSuchFieldException e) {
			return hash.get(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setGlobal(String name,Object value) {
		try {
			Class<?> class1 = getClass();
			Field f=class1.getField(name);
			f.set(this, value);
		} catch (NoSuchFieldException e) {
			hash.put(name,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
