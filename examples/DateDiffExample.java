
import java.util.Calendar;
 
public class DateDiffExample
{  
    public static void main(String[] args)
   {
                long diffInMillisec=0;
                long diffInDays=0;
               Calendar firstDate =null;
               Calendar secondDate =null;
                try{
                         // Create two calendars instances
                         firstDate = Calendar.getInstance();
                         secondDate = Calendar.getInstance();
                      
                        //Set the dates
                        firstDate.set(2014, 9, 26);       
                        secondDate.set(2014, 10, 31);
 
                        // Get the difference between two dates in milliseconds    
                       diffInMillisec = firstDate.getTimeInMillis() - secondDate.getTimeInMillis();     
 
                       // Get difference between two dates in days
                       diffInDays = diffInMillisec / (24 * 60 * 60 * 1000);   
                       System.out.println(diffInDays);
 
                    }catch(Exception ex)
                    {
                                ex.printStackTrace();
                     }             
           }
 }
