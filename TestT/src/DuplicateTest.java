import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;




public class DuplicateTest {
	
	public static void main(String[] args) {
		
		String str = "Hello World";
		int len = str.length();
		char[] ch=str.toCharArray();
		
		Map<Character, Integer> numChars = new HashMap<Character, Integer>();

		for (int i = 0; i < len; ++i)
		{
		    /*char charAt = str.charAt(i);

		    if (!numChars.containsKey(charAt))
		    {
		        numChars.put(charAt, 1);
		    }
		    else
		    {
		        numChars.put(charAt, numChars.get(charAt) + 1);
		    }*/
			
			if(numChars.containsKey(ch[i])){
				numChars.put(ch[i], numChars.get(ch[i])+1);
			}else{
				numChars.put(ch[i], 1);
			}
		}

		System.out.println(numChars);
		
		Arrays.sort(ch);

		for(int i=0;i<ch.length;i++){
			System.out.println(ch[i]);
		}

}
}
