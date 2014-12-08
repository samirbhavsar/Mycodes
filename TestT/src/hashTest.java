import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class hashTest {
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) {
		try {
			HashMap<Integer, data> map = new HashMap<Integer, data>();
			for (int i = 0; i < 20; i++) {
				map.put(i, new data("data " + i, i * 10, i * 100, i * 1000));
			}
			ObjectOutputStream obj = new ObjectOutputStream(
					new FileOutputStream("D://map.txt"));
			obj.writeObject(map);
			obj.close();
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			ObjectInputStream obj = new ObjectInputStream(new FileInputStream(
					"map.txt"));
			HashMap<Integer, data> map = (HashMap<Integer, data>) obj
					.readObject();
			for (data d : map.values())
				System.out.println(d.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class data implements Serializable {
	private static final long serialVersionUID = 1L;
	String s;
	int a, b, c;

	public data(String s, int a, int b, int c) {
		this.s = s;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return s + " " + a + " " + b + " " + c;
	}
}
