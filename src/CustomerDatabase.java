import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBUtility.DBUtility;

public class CustomerDatabase {
	
	private Connection con=null;
	 
	private  String select="select customers.customerid,"
	 		+ "customers.firstname, customers.lastname, customers.title, "
	 		+ "customers.FirstName || ' ' || customers.LastName AS \"Full Name\","
	 		+ "customers.streetaddress,"
	 		+ "cities.cities,states.state, customers.zipcode,"
	 		+ "customers.emailaddress, "
	 		+ "positions.position, companies.company "
	 		+ "from customers , companies, cities, states , positions "
	 		+ "where customers.companyid=companies.companyid "
	 		+ "and customers.cityid=cities.cityid "
	 		+ "and customers.stateid=states.stateid "
	 		+ "and positions.positionid =customers.positionid";

	
	 	
	public List<Customer> searchdatabase(String lastname)
	{	
		List<Customer> customers = new ArrayList<Customer>();
        
        String searchselect=select+" and customers.lastname='"+lastname+"' order by customerid";
	
		Statement stmt = null;
	    ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeQuery(searchselect);
			
			while (rs.next()) {
				
			    Customer mycustomer = new Customer();
			    mycustomer.setCustomerID(Integer.parseInt(rs.getString("CUSTOMERID")));
     		    mycustomer.setTitle(rs.getString("TITLE"));
			    mycustomer.setFirstName(rs.getString("FIRSTNAME"));
			    mycustomer.setLastName(rs.getString("LASTNAME"));
			    mycustomer.setFullName(rs.getString("Full Name"));
			    mycustomer.setStreetAddress(rs.getString("STREETADDRESS"));
			    mycustomer.setCity(rs.getString("CITIES"));
			    mycustomer.setState(rs.getString("STATE"));
			    mycustomer.setZipCode(rs.getString("ZIPCODE"));
			    mycustomer.setEmailAddress(rs.getString("EMAILADDRESS"));
     		    mycustomer.setPosition(rs.getString("POSITION"));
			    mycustomer.setCompany(rs.getString("COMPANY"));
			    customers.add(mycustomer);	    
			}
					
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return customers;
	
	}
	
	public Customer searchdatabase(int customerid)
	{	
		
        Customer mycustomer = new Customer();
        String searchselect=select+" and customers.customerid='"+customerid+"'";
	
		Statement stmt = null;
	    ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeQuery(searchselect);
			
			while (rs.next()) {
				
			    
			    mycustomer.setCustomerID(Integer.parseInt(rs.getString("CUSTOMERID")));;
			    mycustomer.setTitle(rs.getString("TITLE"));
			    mycustomer.setFirstName(rs.getString("FIRSTNAME"));
			    mycustomer.setLastName(rs.getString("LASTNAME"));
			    mycustomer.setFullName(rs.getString("Full Name"));
			    mycustomer.setStreetAddress(rs.getString("STREETADDRESS"));
			    mycustomer.setCity(rs.getString("CITIES"));
			    mycustomer.setState(rs.getString("STATE"));
			    mycustomer.setZipCode(rs.getString("ZIPCODE"));
			    mycustomer.setEmailAddress(rs.getString("EMAILADDRESS"));
     		    mycustomer.setPosition(rs.getString("POSITION"));
			    mycustomer.setCompany(rs.getString("COMPANY"));	    	    
			}
					
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return mycustomer;
	
	}
	
	public String printdatabase(List<Customer> customers)
	{
		String content="";
		for (int i = 0; i < customers.size(); i++) {
		    Customer mycustomer = customers.get(i);
		    content+="Customer Number: "+mycustomer.getCustomerID()+"\n"
		    		+mycustomer.getTitle()+" "+mycustomer.getFullName()+"\n"
		    		+mycustomer.getStreetAddress()+"\n"
		    		+mycustomer.getCity()+", "+mycustomer.getState()+" "+mycustomer.getZipCode()+"\n"
		    		+mycustomer.getEmailAddress()+"\n"
		    		+mycustomer.getPosition()+" at "+mycustomer.getCompany()+"\n"+"\n";		 
		   
		}
		return content;
	}
	
	public String printcustomer(Customer mycustomer)
	{
		String content="";
	    content+="Customer Number: "+mycustomer.getCustomerID()+"\n"
	    		 +mycustomer.getTitle()+" "+mycustomer.getFullName()+"\n"
		    		+mycustomer.getStreetAddress()+"\n"
		    		+mycustomer.getCity()+", "+mycustomer.getState()+" "+mycustomer.getZipCode()+"\n"
		    		+mycustomer.getEmailAddress()+"\n"
		    		+mycustomer.getPosition()+" at "+mycustomer.getCompany()+"\n"+"\n";		 
		   		
		return content;
	}
	
	
	public void createNewCustomer(Customer newCustomer)
	{
		Statement stmt = null;
		Statement stmtcreate = null;
	    ResultSet rs = null;
	    ResultSet rscreate = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmtcreate=con.createStatement();
			rs = stmt.executeQuery(select);
			
			rs.last();
			int customerid=rs.getRow()+1;
				
			String Title=newCustomer.getTitle();
			String firstname=newCustomer.getFirstName();
			String lastname=newCustomer.getLastName();
			String streetaddress=newCustomer.getStreetAddress();
			String zipcode=newCustomer.getZipCode();
			String emailaddress=newCustomer.getEmailAddress();
			int cityid=searchcityid(newCustomer.getCity());
			int stateid=searchstateid(newCustomer.getState());
			int positionid=searchpositionid(newCustomer.getPosition());
			int companyid=searchcompanyid(newCustomer.getCompany());
			
			String create="INSERT INTO Customers (Title, firstname, lastname, streetaddress, zipcode, emailaddress, companyid, cityid, stateid, positionid, customerid) "
					     +"VALUES ('"+Title+"','"+firstname+"', '"+lastname+"','"+streetaddress+"','"+zipcode+"', '"+emailaddress+"',"+companyid+","+cityid+","+stateid+","+positionid+","+customerid+")";
			
			rscreate = stmtcreate.executeQuery(create);
							
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				rscreate.close();
				stmtcreate.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public int searchcityid(String city)
	{
        int cityid=0;
		String searchcity="select cityid from cities where cities='"+city+"'";
		Statement stmt = null;
	    ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeQuery(searchcity);
			
			while (rs.next()) {
			        cityid=Integer.parseInt(rs.getString("CITYID"));  
			}
					
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return cityid;
	}
	public int searchstateid(String state)
	{
		int stateid=0;
		String searchstate="select stateid from states where state='"+state+"'";
		Statement stmt = null;
	    ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeQuery(searchstate);
			
			while (rs.next()) {
			        stateid=Integer.parseInt(rs.getString("STATEID"));  
			}
					
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return stateid;
	}
	public int searchpositionid(String position)
	{
		int positionid=0;
		String searchposition="select positionid from positions where position='"+position+"'";
		Statement stmt = null;
	    ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeQuery(searchposition);
			
			while (rs.next()) {
			        positionid=Integer.parseInt(rs.getString("POSITIONID"));  
			}
					
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return positionid;
	}
	public int searchcompanyid(String company)
	{
		int companyid=0;
		String searchcompany="select companyid from companies where company='"+company+"'";
		Statement stmt = null;
	    ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeQuery(searchcompany);
			
			while (rs.next()) {
			        companyid=Integer.parseInt(rs.getString("COMPANYID"));  
			}
					
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return companyid;
	}
	public void updatecustomer(int customerid, String StreetAddress, String City, String State, String Zipcode)
	{
		
		
		int cityid=searchcityid(City);
		int stateid=searchstateid(State);
		
		String update="update customers c "
				+ "set c.streetaddress='"+StreetAddress+"', "
				+ "c.cityid='"+cityid+"',"
				+ "c.stateid='"+stateid+"',"
				+ "c.zipcode='"+Zipcode+"'"
				+ "where c.customerid='"+customerid+"'";
		Statement stmt = null;
	    ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                       //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeQuery(update);
			    			
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	public String totalReport() throws SQLException
	 {
		 List<String> mylist = new ArrayList<String>();
		 
		 String query="select c.NumberofCustomers as \"Number of Customers\", "
				+ "d.NumberofCompanies as \"Number of Companies\", "
						+ "e.NumberofStates as \"Number of States\""
								+ "from"
								+ "(select count(distinct customerid) as NumberofCustomers from customers) c,"
								+ "(select count(distinct companyid) as NumberofCompanies from companies) d,"
								+ "(select count(distinct stateid) as NumberofStates from states) e";
		
		 mylist=DBUtility.select(query);
		 int col=DBUtility.columnnum(query);
		 int row=mylist.size()/col;
		 
		 String content="";
		 content+=columnname(query)+"\n";
		 content+=printlist(mylist, col)+"\n";
		 
		 return content;
	 }
	public String columnname(String query) throws SQLException
	{
		
		ResultSet rs;
		String content="";
		rs=DBUtility.getResultSet(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int col=DBUtility.columnnum(query);
		
		for(int i=1; i<=col;i++)
		{
			content+=rsmd.getColumnLabel(i)+"   ";
		}
		content+="\n";
		for(int i=0; i<col;i++)
		{
			content+="----------";
		}
		return content;
	}
	public String printlist(List<String> mylist, int col)
	{
		String content="";      
		int i=1;
		for(String d:mylist) {	
            
            if((i%col)==0)
            {
            	content+=d+"   "+"\n";
            }
            else
            {
            	content+=d+"   ";
            }
            i++;
            
        }
		return content;
		
	}
	
	
	
	
	

}
