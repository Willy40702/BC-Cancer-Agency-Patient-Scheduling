package com.mkyong.common.controller;

import java.sql.Date;

/*
 * Represents appointment tuple in database as an object. Used to move around appointment information.
 */

public class Appointment {
	
	public String appointment_type;

	public String provider_name;

	public String building;	

	public int room;

	public Date start_date_time;

	public Date end_date_time;
	
	public int appoint_id;
	
	public Appointment() {
		// initialize all values to 0 or whatever
	}
	
	public Appointment(String type, String name, String build, int room, Date start, Date end, int id) {
		this.appointment_type = type;
		this.provider_name = name;
		this.building = build;
		this.room = room;
		this.start_date_time = start;
		this.end_date_time = end;
		this.appoint_id = id;
	}

}
