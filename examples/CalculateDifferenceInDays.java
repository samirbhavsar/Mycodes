import java.util.Calendar;

/*
 * Here we will learn to calculate the difference in days between two date.
 * There is Java API(JODA Api) available which can also be used to calculate difference between date.
 * But here we will talk about the option available in Java only.
 */
public class CalculateDifferenceInDays {

	public static void main(String args[]) {

		// Create Calendar instance
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		// Set the values for the calendar fields YEAR, MONTH, and DAY_OF_MONTH.
		calendar1.set(2014, 9, 26);
		calendar1.set(2014, 10, 30);

		/*
		 * Use getTimeInMillis() method to get the Calendar's time value in
		 * milliseconds. This method returns the current time as UTC
		 * milliseconds from the epoch
		 */
		long miliSecondForDate1 = calendar1.getTimeInMillis();
		long miliSecondForDate2 = calendar2.getTimeInMillis();

		// Calculate the difference in millisecond between two dates
		long diffInMilis = miliSecondForDate2 - miliSecondForDate1;

		/*
		 * Now we have difference between two date in form of millsecond we can
		 * easily convert it Minute / Hour / Days by dividing the difference
		 * with appropriate value. 1 Second : 1000 milisecond 1 Hour : 60 * 1000
		 * millisecond 1 Day : 24 * 60 * 1000 milisecond
		 */

		long diffInSecond = diffInMilis / 1000;
		long diffInMinute = diffInMilis / (60 * 1000);
		long diffInHour = diffInMilis / (60 * 60 * 1000);
		long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);

//		System.out.println("Difference in Seconds : " + diffInSecond);
//		System.out.println("Difference in Minute : " + diffInMinute);
//		System.out.println("Difference in Hours : " + diffInHour);
		System.out.println("Difference in Days : " + Math.abs(diffInDays));

	}

}