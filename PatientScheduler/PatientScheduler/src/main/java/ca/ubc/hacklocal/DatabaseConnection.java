package ca.ubc.hacklocal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class DatabaseConnection {
	
	private Connection con;
	
	@RequestMapping(value="/database", method = RequestMethod.GET)
	public String database(ModelMap model) {


	      
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
	            /*
	            rs = st.executeQuery("SELECT VERSION()");

	            if (rs.next()) {
	                System.out.println(rs.getString(1));
	            }
	            */
	            System.out.println("connection established");

	        } catch (SQLException ex) {
	        	System.out.println("Message: " + ex.getMessage());
	        	System.exit(-1);
	        }
		
		return "database";
 
	}

}
