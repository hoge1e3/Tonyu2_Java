package jp.tonyu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class R {
	public static Object add(Object a, Object b) {
		if (a instanceof Number && b instanceof Number) {
			return toNumber(a)+toNumber(b);
		}
		return ""+a+b;			
	}
	public static Object sub(Object a, Object b) {
		return toNumber(a)-toNumber(b);
	}
	public static double toNumber(Object a) {
		if (a instanceof Number) {
			Number n = (Number) a;
			return n.doubleValue();
		}
		try {
			return Double.parseDouble(a+"");
		} catch (Exception e) {
			return 0;
		}
	}
	public static Object mul(Object a, Object b) {
		return toNumber(a)*toNumber(b);
		
	}
	public static Object div(Object a, Object b) {
		return toNumber(a)/toNumber(b);
		
	}
	public static Object mod(Object a, Object b) {
		return toNumber(a)%toNumber(b);
		
	}
	public static boolean isTrue(Object a) {
		if (a==null) return false;
		if (a instanceof Boolean) {
			Boolean b = (Boolean) a;
			return b;
		}
		if (a instanceof Number) {
			Number n = (Number) a;
			return n.doubleValue()!=0;
		}
		return true;
	}
	public static Method getMethodDyn(Class<?> klass,String name, Object... params) throws NoSuchMethodException {
		for (Method m : klass.getMethods()) {
			Class<?>[] parameterTypes = m.getParameterTypes();
			if (m.getName().equals(name) && parameterTypes.length==params.length) {
				boolean ok=true;
				for (int i=0 ; i<params.length; i++) {
					ok &= parameterTypes[i].isAssignableFrom(params[i].getClass());
					if (!ok) break;
				}
				if (ok) return m;
			}
		}
		throw new NoSuchMethodException(klass+"::"+name+"("+params+")");
		//return null;
	}
	public static <T> Constructor<T> getConstructorDyn(Class<T> klass, Object... params) throws NoSuchMethodException {
		for (Constructor<?> m : klass.getConstructors()) {
			Class<?>[] parameterTypes = m.getParameterTypes();
			if (parameterTypes.length==params.length) {
				boolean ok=true;
				for (int i=0 ; i<params.length; i++) {
					ok &= parameterTypes[i].isAssignableFrom(params[i].getClass());
					if (!ok) break;
				}
				if (ok) return (Constructor<T>)m;
			}
		}
		throw new NoSuchMethodException(klass+"::new"+"("+params.length+")");
		//return null;
	}
}
