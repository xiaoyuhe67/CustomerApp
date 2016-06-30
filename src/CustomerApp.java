import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerApp {
	
	private static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException
	{
		String input="";
		while(true)
		{
			System.out.println("Please input lastname for search or totals for report:");
			input=sc.next();
				
			CustomerDatabase cd=new CustomerDatabase();
			
			if(input.equals("totals"))
			{
				System.out.println(cd.totalReport());
			}
			else
			{	
			List<Customer> customers = new ArrayList<Customer>();
			
			customers=cd.searchdatabase(input);
			if(customers.size()==0)
			{
				Customer newcustomer=new Customer();
				
				System.out.println("There is no one named "+input);
				System.out.println("Press 1 to create a new customer, Press 2 to search another customer:");
				int create=sc.nextInt();
				if(create ==1)
				{
					System.out.println("Please create a new customer:");
					
					System.out.println("Please enter the title of the customer:");
					sc.nextLine();
					String title=sc.nextLine();
					
					System.out.println("Please enter the first name of the customer:");
					String firstname=sc.nextLine();
					
					System.out.println("Please enter the last name of the customer:");
					String lastname=sc.nextLine();
					System.out.println("Please enter the street address of the customer:");
					String streetaddress=sc.nextLine();
					System.out.println("Please enter the city of the customer:");
					String city=sc.nextLine();
					System.out.println("Please enter the state of the customer:");
					String state=sc.nextLine();
					System.out.println("Please enter the zipcode of the customer:");
					String zipcode=sc.nextLine();	
					System.out.println("Please enter the Email address of the customer:");
					String Emailaddress=sc.nextLine();
					System.out.println("Please enter the position of the customer:");
					String position=sc.nextLine();
					System.out.println("Please enter the company of the customer:");
					String company=sc.nextLine();
					
					
					newcustomer.setTitle(title);
					newcustomer.setFirstName(firstname);
					newcustomer.setLastName(lastname);
					newcustomer.setStreetAddress(streetaddress);
					newcustomer.setCity(city);
					newcustomer.setState(state);
					newcustomer.setZipCode(zipcode);
					newcustomer.setEmailAddress(Emailaddress);
					newcustomer.setPosition(position);
					newcustomer.setCompany(company);
					
					cd.createNewCustomer(newcustomer);
					
					System.out.println("The new customer is created successfully!");
					
					System.out.println(cd.printcustomer(newcustomer));
				}
				else if(create==2)
				{
					continue;
				}		
				
			}
			else
			{
				System.out.println(cd.printdatabase(customers));
				
				System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address.");
				int update=sc.nextInt();
				if(update==2)
				{
					System.out.println("Please update the customer's address: ");
					System.out.println("Please input the customer id:");
					int customerid=sc.nextInt();
					Customer myowncustomer=new Customer();
					myowncustomer=cd.searchdatabase(customerid);
					System.out.println(cd.printcustomer(myowncustomer));
					
					System.out.println("Please enter the street address of the customer:");
					sc.nextLine();
					String streetaddress=sc.nextLine();
					System.out.println("Please enter the city of the customer:");
					String city=sc.nextLine();
					System.out.println("Please enter the state of the customer:");
					String state=sc.nextLine();
					System.out.println("Please enter the zipcode of the customer:");
					String zipcode=sc.nextLine();	
					cd.updatecustomer(customerid, streetaddress, city, state, zipcode);
					
					System.out.println("The customer is updated successfully!");
					
					myowncustomer=cd.searchdatabase(customerid);
					
					System.out.println(cd.printcustomer(myowncustomer));
					
				}
				else if(update ==1)
				{
					continue;
				}
				
			}
			
			
			}	
		}
	}
	
	

}
