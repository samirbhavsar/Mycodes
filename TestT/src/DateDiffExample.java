
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
                        firstDate.set(2012, 9, 5);       
                        secondDate.set(2014, 10, 27);
 
                        // Get the difference between two dates in milliseconds    
                       diffInMillisec = firstDate.getTimeInMillis() - secondDate.getTimeInMillis();     
 
                       // Get difference between two dates in days
                       diffInDays = Math.abs(diffInMillisec / (24 * 60 * 60 * 1000));   
                       System.out.println((diffInDays/30)/12);
 
                    }catch(Exception ex)
                    {
                                ex.printStackTrace();
                     }             
           }
 }
