package com.taxiforsure.projectCommonLib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.taxiforsure.commonLib.Assertions;
import com.taxiforsure.commonLib.BDriver;
import com.taxiforsure.commonLib.DoOperation;
import com.taxiforsure.commonLib.WebDriverCommonLib;
import com.taxiforsure.pagelib.AirportTransferPage;
import com.taxiforsure.pagelib.BookingConfirmedPage;
import com.taxiforsure.pagelib.BookingsPage;
import com.taxiforsure.pagelib.CommonPage;
import com.taxiforsure.pagelib.HalfOrFullDayPage;
import com.taxiforsure.pagelib.Login;
import com.taxiforsure.pagelib.OutstationPage;
import com.taxiforsure.pagelib.SelectCityPage;
import com.taxiforsure.pagelib.SelectLocation;
import com.taxiforsure.pagelib.SelectTaxiPage;
import com.taxiforsure.utility.ReadDataFromExcel;

@SuppressWarnings("unused")
public class ProjectCommonUtils extends WebDriverCommonLib {

	public ProjectCommonUtils(WebDriver driver) {
		super(driver);
	}

	// WebElement myAccount = driver.findElement(By
	// .xpath(".//*[@id='topList']/li[3]/a/em"));
	
	
	
	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	
	int currMonth = localCalendar.get(Calendar.MONTH) + 1;
	
	//List<String> cities=Arrays.asList("Mysore","Bangalore");
	
	static String bookingConfirmMsg="Booking Confirmed";
	
	static SoftAssert softAssert=new SoftAssert();
	
	Login loginPage = PageFactory.initElements(driver, Login.class);
	SelectCityPage cityPage = PageFactory.initElements(driver,
			SelectCityPage.class);
	Calendar cal = Calendar.getInstance();

	SelectLocation selLocation = PageFactory.initElements(driver,
			SelectLocation.class);
	SelectTaxiPage selTaxi = PageFactory.initElements(driver,
			SelectTaxiPage.class);
	ChoseCity cCity = new ChoseCity();
	AirportTransferPage atp = PageFactory.initElements(driver,
			AirportTransferPage.class);
	HalfOrFullDayPage hfd = PageFactory.initElements(driver,
			HalfOrFullDayPage.class);
	OutstationPage op = PageFactory.initElements(driver,
			OutstationPage.class);
	static BookingConfirmedPage bcf=PageFactory.initElements(driver, 
			BookingConfirmedPage.class);
	BookingsPage bPage=PageFactory.initElements(driver, BookingsPage.class);
	
	Properties properties = new Properties();
	SoftAssert softassert=new SoftAssert();
	
	 
	 
	 static Map<String, List<String>> map = new HashMap<String, List<String>>();
	 
	 static List<String> confirmedBooking = new ArrayList<String>();
	 static List<String> PendingBooking = new ArrayList<String>();
	 
	//static  Multimap<String, String> multiMap = ArrayListMultimap.create();
	
	public void openUrl() throws IOException {
		FileInputStream fin = new FileInputStream(
				System.getProperty("user.dir")+"/resources/loginInfo.properties");
		properties.load(fin);
		String url = properties.getProperty("url");
		//driver.manage().deleteAllCookies();
		driver.get(url);
		
		//driver.manage().window().maximize();

	}

	public void loginToApp() throws InvalidFormatException, IOException, InterruptedException {

		// String cityName = ExcelUtils.getExcelData("city", 1, 0);
		// cCity.SelectCity(cityName);
		// driver.manage().window().maximize();
		FileInputStream fin = new FileInputStream(
				"D:\\Projects\\TaxiForSureTest\\resources\\loginInfo.properties");
		properties.load(fin);
		waitForPageToLoad(driver);
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		waitForWebElementFluently(loginPage.loginLink());
		//loginPage.loginLink().click();
		DoOperation.click(driver, loginPage.loginLink());
		// waitForPageToLoad();
		// loginPage.getUserNameEdt().clear();
		//loginPage.getUserNameEdt().sendKeys(username);
		DoOperation.type(driver, loginPage.getUserNameEdt(), username);
		// loginPage.getPasswordEdt().clear();
		loginPage.getPasswordEdt().sendKeys(password);
		DoOperation.type(driver, loginPage.getPasswordEdt(), password);
		
		
		//loginPage.getLoginBtn().click();
		
		DoOperation.click(driver, loginPage.getLoginBtn());
		//loginPage.login(username, password);
		Thread.sleep(10000);
		
		//waitForPageToLoad(driver);

	}

	public void logOut() {
		CommonPage.logout();
		//waitForPageToLoad(driver);

	}

	public void pickUpList(String pickupArea) {
		try{
		List<WebElement> pickList = selLocation.pickUpAreaList();
		if(pickList.size()>0){
		for (WebElement pickList1 : pickList) {
			if (pickList1.getText().contains(pickupArea)) {
				pickList1.click();
				break;
			}
		}
		}else{
			//Assert.fail("Pickup list is not avaible");
		}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

	public void pickUpList1(String pickupArea) {
		try{
		List<WebElement> pickList = atp.pickUpList();
		if(pickList.size()>0){
		for (WebElement pickList1 : pickList) {
			if (pickList1.getText().contains(pickupArea)) {
				pickList1.click();
				break;
			}
		}
		}else{
			Assertions.fail("Pickup list is not avaible");
		}
		}catch(Exception e){
			e.getMessage();
			softassert.fail("Pickup list is not avaible");
		}

	}

	public void pickUpListHalfFull(String pickupArea) {
		try{
		List<WebElement> pickList = hfd.pickUpAreaList();
		for (WebElement pickList1 : pickList) {
			if (pickList1.getText().contains(pickupArea)) {
				pickList1.click();
				break;
			}else{
				//Assertions.fail("Package is not avaible");
			}
		}
		}catch(Exception e){
			softassert.fail("Pick up area is not avaible");
		}
	}

	public void dropList(String pickupArea) {
		List<WebElement> dropList = selLocation.dropAreaList();
		for (WebElement dropList1 : dropList) {
			if (dropList1.getText().contains(pickupArea)) {
				dropList1.click();
				break;
			}
		}
	}

	public void dropList1(String dropArea) {
		try{
		List<WebElement> dropList = atp.dropList1();
		if(dropList.size()>0){
		for (WebElement dropList1 : dropList) {
			if (dropList1.getText().contains(dropArea)) {
				dropList1.click();
				break;
			}
		}
		}else{
			
		}
		
		}catch(Exception e){
			e.getMessage();
			softassert.fail("Drop area is not available");
		}
	}

	public void selectDAte(String pickDate) {

		List<WebElement> selDate = selLocation.dateSelect();
		int firstIndex = pickDate.indexOf("/");
		int lastIndex = pickDate.lastIndexOf("/");

		String day = pickDate.substring(0, firstIndex);
		//String month=pickDate.substring(0, lastIndex);
		String month = pickDate.substring(firstIndex + 1, lastIndex);
		int month_=Integer.parseInt(month);
		
		// targetDay = Integer.parseInt(month);
		for (WebElement date : selDate) {
			
			if ((date.isEnabled())
					&& (date.getAttribute("class").contains("infocus"))) {
				
				// int dateT = Integer.parseInt(date.getText());
				if (date.getText().equals(day)) {
					// System.out.println(date.getText()+"  "+day);
					date.click();
					break;
				}
			}

		}

	}
	
	/*public void jumpMonthsBy(String dateString){
		int count=0;
		int firstIndex = dateString.indexOf("/");
		int lastIndex = dateString.lastIndexOf("/");

		String day = dateString.substring(0, firstIndex);
		//String month=pickDate.substring(0, lastIndex);
		String month = dateString.substring(firstIndex + 1, lastIndex);
		int month_=Integer.parseInt(month);
		
		if((month_-currMonth)>0){
			count=month_-currMonth;
		}else{
			count=month_-currMonth;
			increment=false;
		}
		
		
	}*/

	public void selectDate2(String returnDate) {

	}

	public void selectTime(String Seltime) {

		List<WebElement> selTime = selLocation.selectTime();
		for (int i = 0; i < selTime.size(); i++) {
			if (selTime.get(i).getText().equalsIgnoreCase(Seltime)) {
				selTime.get(i).click();
				break;
			}
		}
	}

	public void selectTaxi(String taxiName) {
		try{
		List<WebElement> taxiSel = selTaxi.carList();
		List<WebElement> taxigroup = selTaxi.selectCar();
		// int count=taxiSel.size();
		if(taxigroup.size()>0){
		for (int i = 0; i < taxigroup.size(); i++) {

			if(taxigroup.size()!=0){
			if (taxiSel.get(i).getText().contains(taxiName)) {
				taxigroup.get(i).click();
				break;
			}
			}else{
			//	softassert.fail("");
				
			}
		}
		}else{
			//Assertions.fail("Taxi list is not avaible: "+taxigroup.size());
		}
		}catch(Exception e){
			e.getMessage();
			Assertions.fail("Taxi list is not avaible ");
		}

	}
	public void selectTaxi(String city,String taxiName,String fare){
		
		List <WebElement>carList=atp.taxiflatFare();
		if(city.equalsIgnoreCase("Bangalore") && fare.equalsIgnoreCase("flat")){
			
			for(WebElement car:carList){
				if(car.getText().equals(taxiName)){
					car.click();
					break;
				}else if(city.equalsIgnoreCase("Bangalore") && !fare.equalsIgnoreCase("flat")) {
					
					for(WebElement e:atp.taxiListNoFlatFare()){
						if(taxiName.equalsIgnoreCase(e.getText())){
							e.click();
						}
					}
				}
					
			}
		}
	}

	public void selectTaxi(List<WebElement> element,String taxiName){
		
		
		for(WebElement taxi:element){
			if(taxiName.contains(taxi.getText())){
				taxi.click();
				break;
			}
		}
	}
	
	public void selectAirport(String airPortName) {
		
		//System.out.println(airPortName);
		List<WebElement> airPrtName = atp.getAllFlightList();
		
		for (int i = 0; i < airPrtName.size();i++) {
			//org.testng.Assertions.AssertionsEquals(airPortName, airPrtName.get(i).getText());
			//airPrtName.get(i).click();
			//break;
			System.out.println(airPrtName.get(i).getText());
			if (airPrtName.get(i).getText().contains(airPortName)) {
				
				airPrtName.get(i).click();
				break;
			}
			else{
				
				//softassert.fail("Found "+airPortName+" but Expected "+airPrtName.get(i).getText());
				
			}
		}
		
		
	}

	public void selectAirport(){
		
	}
	
	public void selectPackage(String packageType) {
		
	try{
		List<WebElement> packageList = hfd.getPackageList();
		
		for (int i = 0; i < packageList.size(); i++) {
			if (packageList.get(i).getText().contains(packageType)) {
				packageList.get(i).click();
				break;
			}
		}

	}catch(Exception e){
		e.getMessage();
		softassert.fail("Package list is not avaible.");
	}
	}
	
	public static void checkBookConfirm(){
		
		if(bcf.bookingConfirmMsg().getText().contains(bookingConfirmMsg)){
		//	GetBookingId.setBookingId(bcf.bookingIdTxt().get);
			//System.out.println("Your Booking is confirmed "+bcf.bookingIdTxt().getText());
			confirmedBooking.add(bcf.bookingIdTxt().getText());
			map.put("Confirmed", confirmedBooking);
			
		//	multiMap.put("Confirmed", bcf.bookingIdTxt().getText());
			
		}else if(bcf.bookingConfirmMsg().getText().contains("Progress")){
			
			String getUrl=driver.getCurrentUrl();
			String[] str=getUrl.split("/");
			String bookingId=str[str.length-1];
			//System.out.println("Your booking is in progress: "+bookingId);
			//multiMap.put("Pending", bookingId);
			PendingBooking.add(bookingId);
			map.put("Pending", PendingBooking);
			
			
		}else{
			Assert.fail("Booking Not confirmed");
		}
		
		/*for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            //System.out.println("Key = " + key+" "+"Values= "+values );
            System.out.println("BookingId: "+values+" Status: "+key);
            //System.out.println("Values = " + values + "n");
        }*/
		
		/*Set<String> keys=multiMap.keySet();
		for(String key:keys){
			System.out.println("Bookingid: "+multiMap.get(key)+" "+key);
		}*/
		
	}
	
	public void confirmedBookings() throws InvalidFormatException, IOException{
		
		
		
//		List<WebElement> inProgressBooking=bPage.inProgressBookingIds();
//		List<WebElement>confirmedBookId=bPage.confirmedBookingId();
//		List<WebElement> pastBookingId=bPage.pastBookingIds();
		
		/*for(int i=1;i<=bPage.confirmedBookingId().size();i++){
			//ReadDataFromExcel.setExcelData("BookingId", i, 0, bPage.confirmedBookingId().get(i).getText());
			bookingId.add(bPage.confirmedBookingId().get(i).getText());
		}*/
		System.out.println("Total confirmed bookings: "+bPage.confirmedBookingId().size());
		System.out.println("Total no. of pending bookings:  "+bPage.inProgressBookingIds().size());
		System.out.println("Total bookings till date:  "+bPage.pastBookingIds().size());
	}
	
public void inProgressBookings() throws InvalidFormatException, IOException{
		
		/*List<WebElement> inProgressBooking=bPage.inProgressBookingIds();
		//List<WebElement>confirmedBookId=bPage.confirmedBookingId();
		//List<WebElement> pastBookingId=bPage.pastBookingIds();
		
		for(int i=1;i<=bPage.inProgressBookingIds().size();i++){
			ReadDataFromExcel.setExcelData("BookingId", i, 1, bPage.inProgressBookingIds().get(i).getText());
		}*/
	}

	public void selectTrip(String tripType, String date, String cityName) {
		
		

		if (tripType.equalsIgnoreCase("OneWay")) {
			waitForWebElementFluently(op.interCityOneWay());
			op.interCityOneWay().click();

		} else if (tripType.equalsIgnoreCase("Round")
				&& cityName.equalsIgnoreCase("Bangalore")
				|| cityName.equalsIgnoreCase("Mysore")) {

			op.interCityRoundTrip().click();
			waitForWebElementFluently(op.datePicker2());
			op.datePicker2().click();
			op.calendarNxt().click();
			selectDAte(date);
		} else {
			op.datePicker2().click();
			op.calendarNxt().click();
			selectDAte(date);
		}

	}
	
	
	
	public void  selectTripOutStation(String tripType ,String date) throws InterruptedException {
		if(op.interCityOption.isDisplayed()&&tripType.equalsIgnoreCase("OneWay")){
			
				op.interCityOneWay().click();
			}else if(op.interCityOption.isDisplayed() && tripType.equalsIgnoreCase("Round")){
				op.interCityRoundTrip().click();
				waitForWebElementFluently(op.datePicker2());
				op.datePicker2().click();
				op.calendarNxt().click();
				selectDAte(date);
				//selectDateFromCal(date, "/");
			}else{
				op.datePicker2().click();
				op.calendarNxt().click();
				selectDAte(date);
				//selectDateFromCal(date, "/");
			}
		}

	

	public void cancelBooking(String bookingId) {

		String xpath1 = "//div[div[div[div[text()='BOOKING ID']/preceding-sibling::div[text()='";
		String xpath2 = "']]]]/following-sibling::div/descendant::ul/li[2]/span/i";

		String finalXpath = xpath1 + bookingId + xpath2;

		String cancelBookingIcon = "//div[div[div[div[text()='BOOKING ID']/preceding-sibling::div]]]/following-sibling::div/descendant::ul/li[2]/span/i";
		bPage.txtBx_SearchBooking().sendKeys("");

	}


	public void viewBookingId() throws IOException{
		
		/*
		 Set<String> keys = multiMap.keySet();
		 System.out.println("================================");
		 System.out.println("Total  bookings after script run:");
		 System.out.println("================================");
		
		 if(!multiMap.isEmpty()){
		 for(String key:keys){
			 System.out.println("Status = "+key);
			 System.out.println("BookingId = "+multiMap.get(key));
			
		 }
		
		 }else{
			 System.out.println("Total bookings after script run: "+multiMap.size());
		 }*/
		 System.out.println("================================");
		 System.out.println("Total  bookings :");
		 System.out.println("================================");
		if(!map.isEmpty())
		 for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	            String key = entry.getKey();
	            List<String> values = entry.getValue();
	            //s.writeObject(entry.getValue().toString());
	            //s.close();
	            System.out.println("Status = " + key);
	            System.out.println("BookingId = " + values);
	        }
	}
	
	
	public  void selectDateFromCal(String date, String pattern)
			throws InterruptedException {

		List<String> list = Arrays.asList("January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December");
		
		String calMonth = null;
		String calYear = null;
		boolean dateNotFound = true;
		int firstIndex = date.indexOf(pattern);
		int lastIndex = date.lastIndexOf(pattern);
		
		String day = date.substring(0, firstIndex);
		int month = Integer.parseInt(date.substring(firstIndex + 1, lastIndex));
		

		
		int year = Integer
				.parseInt(date.substring(lastIndex + 1, date.length()));

		
		while (dateNotFound) {
			calMonth = selLocation.calMonth().getText();
			calYear = selLocation.calYear().getText();
			
			if (list.indexOf(calMonth) + 1 == month
					&& (year == Integer.parseInt(calYear))) {
				selectDate(day);
				dateNotFound = false;
			} else if (list.indexOf(calMonth) + 1 < month
					&& (year == Integer.parseInt(calYear))
					|| year > Integer.parseInt(calYear)) {
				selLocation.monthNext().click();
			}
		}
		Thread.sleep(3000);
	}

	public void selectDate(String date) {

		WebElement dateWidget = selLocation.dateWidgate_Box();
		List<WebElement> rows = dateWidget.findElements(By.tagName("tr"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("div"));

		for (WebElement cell : columns) {
			// Selects Date
			if (Integer.parseInt(date) == (Integer.parseInt(cell.getText()))
					&& cell.getAttribute("class").contains("infocus")
					&& !cell.getAttribute("class").contains("disabled")) {
				cell.click();
				break;
			}
		}
	}
	
	public long dateDifference(String date, String pattern) {

		int firstIndex = date.indexOf(pattern);
		int lastIndex = date.lastIndexOf(pattern);

		int day = Integer.parseInt(date.substring(0, firstIndex));
		int month = Integer.parseInt(date.substring(firstIndex + 1, lastIndex));
		int year = Integer
				.parseInt(date.substring(lastIndex + 1, date.length()));
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

		int currMonth = localCalendar.get(Calendar.MONTH) + 1;
		int currDate = localCalendar.get(Calendar.DAY_OF_MONTH);
		int curryear = localCalendar.get(Calendar.YEAR);
		// Set the values for the calendar fields YEAR, MONTH, and DAY_OF_MONTH.
		calendar1.set(curryear, currMonth, currDate);
		calendar2.set(year, month, day);
		
		long miliSecondForDate1 = calendar1.getTimeInMillis();
		long miliSecondForDate2 = calendar2.getTimeInMillis();
		
		long diffInMilis = miliSecondForDate2 - miliSecondForDate1;
		
		long diffInDays = Math.abs(diffInMilis / (24 * 60 * 60 * 1000));
		
		
		return diffInDays;
	}
	
	public String getcarCostDay(String carname){
		
		
		String xpath1="//div[@id='dayFare']/ul/li[1]/label[1]/following-sibling::div/descendant::td[div[@class='taxiInfo']/div/p[text()='";
		String xpath2="']]/following-sibling::td/div[1]";
		
		String xpath3=xpath1+carname+xpath2;
		
		String  carcost=driver.findElement(By.xpath(xpath3)).getAttribute("value");
		
		
		return carcost;
		
	}
public String getcarCostNight(String carname){
		
		
		String xpath1="//div[@id='nightFare']/ul/li[1]/label[1]/following-sibling::div/descendant::td[div[@class='taxiInfo']/div/p[text()='";
		String xpath2="']]/following-sibling::td/div[1]";
		
		String xpath3=xpath1+carname+xpath2;
		
		String  carcost=driver.findElement(By.xpath(xpath3)).getAttribute("value");
		
		;
		return carcost;
		
	}

}


