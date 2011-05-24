package jp.tonyu.coroutine;

class Ball extends MultiThreadProcess {
	public Ball(Scheduler s) {
		super(s);
	}

	@Override
	public void run() {
		for (int i=0 ; i<10 ; i++) {
			System.out.println(this+":" +i);
			if (i==5) throw new RuntimeException("BREAK");
			suspend();
		}
	}
}
class Ball2 extends SingleThreadProcess {
	int i=0;
	@Override
	public void move() {
		System.out.println(this+": "+i);
		i++;
	}
}
public class Test {
	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		s.add(new Ball(s));
		s.add(new Ball(s));
		s.add(new Ball2());
		//synchronized (s) {
			for (int i=0 ; i<20 ; i++) {
				System.out.println("Frame "+i);
				s.runAll();
			}
			
		//}
	}
}
