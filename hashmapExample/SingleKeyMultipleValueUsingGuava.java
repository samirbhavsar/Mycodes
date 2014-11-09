import java.util.Set;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SingleKeyMultipleValueUsingGuava {
	public static void main(String[] args){
	Multimap<String, String> multiMap = ArrayListMultimap.create();
	
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
		 System.out.println("Key = "+key);
		 System.out.println("Values = "+multiMap.get(key)+ key);
	 }
	
			

}
}