
public class StaticclassDemo {
	
	static class Audi{
		public void start(){
			System.out.println("Audi");
		}
		
	}
	static class Bmw{
		
		public void start(){
			System.out.println("BMW");
		}
		
		
	}
	class test{
		public void run(){
			System.out.println("Run");
		}
	}
	public static void main(String[] args) {
		
		StaticclassDemo.Audi a=new StaticclassDemo.Audi();
		Audi ad=new Audi();
		ad.start();
		Bmw bw=new Bmw();
		bw.start();
		StaticclassDemo.Bmw b=new StaticclassDemo.Bmw();
		a.start();
		b.start();
		
		StaticclassDemo st=new StaticclassDemo();
		StaticclassDemo.test t=st.new test();
		t.run();
	}

}
