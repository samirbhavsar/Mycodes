import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SingleKeyMultipleValueUsingList {
	
	
	public static void main(String[] args) throws IOException {
		 
        // create map to store
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Properties properties=new Properties();
        // create list one and store values
        List<String> valSetOne = new ArrayList<String>();
        valSetOne.add("Apple");
        valSetOne.add("Aeroplane");
 
        // create list two and store values
        List<String> valSetTwo = new ArrayList<String>();
        valSetTwo.add("Bat");
        valSetTwo.add("Banana");
 
        // create list three and store values
        List<String> valSetThree = new ArrayList<String>();
        valSetThree.add("Cat");
        valSetThree.add("Car");
 
        // put values into map
        map.put("A", valSetOne);
        map.put("B", valSetTwo);
        map.put("C", valSetThree);
 
        // iterate and display values
        System.out.println("Fetching Keys and corresponding [Multiple] Values n");
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for(int i=0;i<values.size();i++){
            	// s.writeObject(values.get(i));
            	
            	System.out.println("Key = " + key);
                System.out.println("Values = " + values + "n");
            }
         
        }
        for (Map.Entry<String,List<String>> entry : map.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
           // properties.setProperty(entry.getKey(), entry.getValue());
        }
        
        
        
        
        
        

        
        
    }

}
