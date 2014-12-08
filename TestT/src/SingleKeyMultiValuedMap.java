import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class SingleKeyMultiValuedMap {

	public static void main(String[] args) {
		
		
		Map<String, List<String>> map=new HashMap<String, List<String>>();
//		Map<String,HashMap<String,String>> map2=new HashMap<String, HashMap<String,String>>();
//		HashMap<String, String> m1=new HashMap<String,String>();
//		
//		
//		m1.put("a", "b1");
//		
//		map2.put("x", m1);
		
		
		ArrayList<String> al=new ArrayList<String>(); 
		List<String> s1=new ArrayList<String>();
		List<String> s2=new ArrayList<String>();
		
		s1.add("x");
		s1.add("y");
		//map.put("value1", s1);
		
		s2.add("a");
		s2.add("b");
		
		map.put("value1", s1);
		map.put("value2", s2);
		
		
		al.add("Viru");  
		al.add("Saurav");  
		al.add("Mukesh");  
		al.add("Tahir"); 
		
		Collections.sort(al);
		
		Iterator<String> itr=al.iterator();  
		while(itr.hasNext()){  
		System.out.println(itr.next());  
		 }  
		
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            System.out.println("Key = " + key);
            for(int i=0;i<values.size();i++){
            	
                System.out.println("Values = " + values + "n");
            }

	}

}
}
