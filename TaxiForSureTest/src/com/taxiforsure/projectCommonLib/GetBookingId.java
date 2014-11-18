package com.taxiforsure.projectCommonLib;

import java.util.ArrayList;
import java.util.List;

public class GetBookingId {
	
	public static List<String> bookingId=new ArrayList<String>();

	public static List<String> getBookingId() {
		return bookingId;
	}

	public static void setBookingId(List<String> bookingId) {
		GetBookingId.bookingId = bookingId;
	}

	

	
}
