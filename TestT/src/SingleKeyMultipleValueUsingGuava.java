import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SingleKeyMultipleValueUsingGuava {
	public static void main(String[] args) throws IOException{
	Multimap<String, String> multiMap = ArrayListMultimap.create();
	
	//ObjectOutputStream obj=new ObjectOutputStream(new File(""));
	File file=new File("D://temp.txt");
	
	FileOutputStream fstream=new FileOutputStream(file);
	ObjectOutputStream ostream=new ObjectOutputStream(fstream);
	Properties properties = new Properties();
	
	
	multiMap.put("A", "Apple");
	multiMap.put("A", "Aeroplane");
	
// put values into map for B
	multiMap.put("B", "Bat");
	 multiMap.put("B", "Banana");
	
	 // put values into map for C
	 multiMap.put("C", "Cat");
	 multiMap.put("C", "Car");
	 System.out.println("Fetching Keys and corresponding [Multiple] Values n");
	 
	 Set<String> keys = multiMap.keySet();
	 
	 for(String key:keys){
		 System.out.println("Key = "+key.toString());
		 System.out.println("Values = "+multiMap.get(key).toString()+ key.toString());
		 
		 //ostream.writeObject(String.valueOf(key));
		 
	 }
	 
	 
	 
	
			

}
}