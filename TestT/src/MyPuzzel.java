public class MyPuzzel {
 
	private final int dataSize = (int) (Runtime.getRuntime().maxMemory() * 0.6);
 
	public void f() {
 
		{
			System.out.println("In nonstatic block");
			System.out.println(dataSize);
			byte[] data = new byte[dataSize];
		}
 
		
		  for(int i = 0; i < 10; i++){
		  
		  System.out.println("Please be so kind and release memory");
		  
		  }
		 
 
		System.out.println(dataSize);
		byte[] data2 = new byte[dataSize];
 
	}
 
	public static void main(String[] args) {
 
		MyPuzzel jmp = new MyPuzzel();
//		MyPuzzel mp=new MyPuzzel();
//		mp.f();
		jmp.f();
	}
}