import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Test {

	@org.junit.Test
	public void test() {
		CustomerDatabase cd=new CustomerDatabase();
		List<Customer> customers = new ArrayList<Customer>();
		
		customers=cd.searchdatabase("Smith");
		
		System.out.println(cd.printdatabase(customers));
		Customer mycustomer=new Customer();
		
		mycustomer=cd.searchdatabase(3842);
		System.out.println(cd.printcustomer(mycustomer));
		
		cd.createNewCustomer(mycustomer);

		customers=cd.searchdatabase("Smith");
		System.out.println(cd.printdatabase(customers));
		
		cd.updatecustomer(mycustomer.getCustomerID(), mycustomer.getStreetAddress(), mycustomer.getCity(), mycustomer.getState(), mycustomer.getZipCode());
		
		mycustomer=cd.searchdatabase(mycustomer.getCustomerID());
		
		System.out.println(cd.printcustomer(mycustomer));
	}

}
