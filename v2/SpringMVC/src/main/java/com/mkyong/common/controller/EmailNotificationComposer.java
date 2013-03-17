package com.mkyong.common.controller;

public class EmailNotificationComposer {

	String DEFAULT_GREETING = "Hello,";
	String DEFAULT_MESSAGE = "This is a reminder of your upcoming appointment - please see the details below or log in to your PatientScheduler account for more information.";
	String message;
	
	public String compose( String recipientName, String appointmentType, String date, String time, String location ) {
		message = DEFAULT_GREETING + "\n\n" + DEFAULT_MESSAGE + "\n"
					+ "\nAppointment: " + appointmentType
					+ "\nDate: " + date + " " + time
					+ "\nLocation: " + location;
		
		return message;
	}
}

