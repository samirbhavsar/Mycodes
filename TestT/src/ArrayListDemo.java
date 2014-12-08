import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ArrayListDemo {
	
	public static void main(String[] args) {
		
		String[] arr={"abc","xyz"};
		
		ArrayList<String> alist=new ArrayList<String>(Arrays.asList(arr));
		ArrayList<String> aslist=new ArrayList<String>();
		
		for(String a:alist){
			System.out.println(a);
		}
		
		for(String array:arr){
			
			aslist.add(array);
		}
		
		for (String a:aslist) {
			System.out.println(a);
		}
	}

}
