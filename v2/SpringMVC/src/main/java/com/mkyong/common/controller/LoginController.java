package com.mkyong.common.controller;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
 
@Controller
public class LoginController {
	
	private String name;
 
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(ModelMap model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	
	public String printWelcome(ModelMap model) {
 
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		name = user.getUsername();
	
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security login + database example");
		model.addAttribute("foo", "bar");
		
		
		// use username to return an array list of schedules from the database.
		// this information, which is used to connect to the database, is duplicated in file spring-database.xml
		// I don't know how to transfer information between these two files
		String url =
				 "jdbc:mysql://pharmis.ca:3306/hacklocal";
		String username = "hacklocal";
		String password = "thinkglobal";
		
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;


        try {
        	con = DriverManager.getConnection(url, username, password);
        	st = con.createStatement();

        	rs = st.executeQuery("select a.id, a.appointment_type, a.provider_name, a.building, a.room, a.start_date_time, a.end_date_time from appointments a, users u where u.username = '" + name + "' and u.user_id = a.user_id and a.start_date_time > now()");

        	// get info on ResultSet
        	ResultSetMetaData rsmd = rs.getMetaData();

        	// get number of columns
        	int numCols = rsmd.getColumnCount();

        	System.out.println(numCols);

        	List<Appointment> a_list = new ArrayList<Appointment>();

        	while(rs.next())
        	{
        		System.out.println("an appointment!!!");
        		Appointment app = new Appointment();
        		app.appointment_type = rs.getString("appointment_type");
        		app.provider_name = rs.getString("provider_name");
        		app.building = rs.getString("building");
        		app.room = rs.getInt("room");
        		app.start_date_time = rs.getDate("start_date_time");
        		app.end_date_time = rs.getDate("end_date_time");
        		app.appoint_id = rs.getInt("id");
        		a_list.add(app);
        	}

        	System.out.println(a_list.size());

        	xml_write(a_list);

        } catch (SQLException ex) {
        	System.out.println("Message: " + ex.getMessage());
        }

        return "hello";

	}


	private void xml_write(List<Appointment> alist){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement( "data" );

		Iterator<Appointment> it = alist.iterator();
		while(it.hasNext()){
			
			Appointment ap = it.next();
			System.out.println(ap.start_date_time.toString());
			Element event = root.addElement( "event" )
				.addAttribute( "id", Integer.toString(ap.appoint_id) )
				.addAttribute( "start_date", ap.start_date_time.toString() )
				.addAttribute( "end_date", ap.end_date_time.toString() )
				.addAttribute( "text", ap.provider_name )
				.addAttribute( "details", ap.building );					
		}

		try{
			System.out.println(new File(".").getAbsolutePath());
			
			// lets write to a file
			XMLWriter writer = new XMLWriter(
					new FileWriter( "C:/Users/Punit/git/BC-Cancer-Agency-Patient-Scheduling"
							+"/v2/SpringMVC/src/main/java/com/mkyong/common/controller"
							+"/events.xml" )
					);
			writer.write( document );
			writer.close();

			/*
			// Pretty print the document to System.out
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter( System.out, format );
			writer.write( document );

			// Compact format to System.out
			format = OutputFormat.createCompactFormat();
			writer = new XMLWriter( System.out, format );
			writer.write( document );
			*/
		}
		catch(Exception io){
			io.printStackTrace();
		}
	}

 
	@RequestMapping(value="/notifysettings", method = RequestMethod.GET)
	public String notification(ModelMap model) {
 
		if (name == null) {
			return "login";
		}
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		name = user.getUsername();
		
		model.addAttribute("username", name);
		
		return "notifysettings";
 
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		
		name = null;
 
		return "login";
 
	}
	
}