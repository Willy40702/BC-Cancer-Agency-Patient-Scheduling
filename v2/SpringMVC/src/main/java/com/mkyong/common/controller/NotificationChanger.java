package com.mkyong.common.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationChanger {
	
	private String user;
	private String email = "";
	private int notification;
	
	public NotificationChanger() {
   // invalid
		
	}
	
	public NotificationChanger(String name) {
	  this.user = name;
	  String url = "jdbc:mysql://pharmis.ca:3306/hacklocal";
	  String username = "hacklocal";
	  String password = "thinkglobal";
		
	  Connection con = null;
      Statement st = null;
      ResultSet rs = null;

      try {
          con = DriverManager.getConnection(url, username, password);
          st = con.createStatement();
          rs = st.executeQuery("select email, is_email from notification where user_id in (select user_id from users where username = " + name + ")");
  	  
  	while(rs.next())
	  {
  		System.out.println("well, that worked");
  		notification = rs.getInt("is_email");
  		email = rs.getString("email");
	  }
  	} catch (SQLException ex) {
  		System.out.println("Message: " + ex.getMessage());
  	}
	}
	
	public void setEmail(String email) {
		  String url = "jdbc:mysql://pharmis.ca:3306/hacklocal";
		  String username = "hacklocal";
		  String password = "thinkglobal";
		
		  Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;

	      try {
	          con = DriverManager.getConnection(url, username, password);
	          st = con.createStatement();
	          rs = st.executeQuery("update notification set email = '" + email + "' where user_id in (select user_id from users where username = " + user + ")");
	  	  
	  	  this.email = email;
	  	  
	  	while(rs.next())
		  {
	  		System.out.println("well, that worked");
	  		notification = rs.getInt("is_email");
	  		email = rs.getString("email");
		  }
	  	} catch (SQLException ex) {
	  		System.out.println("Message: " + ex.getMessage());
	  	}		
	}
	
	private void notification(int bool) {
		if (email == "") {
			return;
		}
		if (notification == bool) {
			return;
		}
		String url = "jdbc:mysql://pharmis.ca:3306/hacklocal";
		  String username = "hacklocal";
		  String password = "thinkglobal";
		
		  Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;

	      try {
	          con = DriverManager.getConnection(url, username, password);
	          st = con.createStatement();
	          System.out.println("connection established");

	      } catch (SQLException ex) {
	      	System.out.println("Message: " + ex.getMessage());
	      	System.exit(-1);
	      }

	  	Statement  stmt;
	  	   
	  	try
	  	{
	  	  stmt = con.createStatement();

	  	  rs = stmt.executeQuery("update notification set is_email = " + bool + " where user_id in (select user_id from users where username = " + user + ")");
	  	  
	  	while(rs.next())
		  {
	  		System.out.println("well, that worked");
	  		notification = rs.getInt("is_email");
	  		email = rs.getString("email");
		  }
	  	} catch (SQLException ex) {
	  		System.out.println("Message: " + ex.getMessage());
	  	}
	}
	
	public void setNotification() {
		notification(1);
	}
	
	public void removeNotification() {
		notification(0);
	}

}
