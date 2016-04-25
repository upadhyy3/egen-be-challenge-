import static org.junit.Assert.*;
import json.Address;
import json.Company;
import json.User;

import org.junit.Test;

/**
 * JUNIT test class to check if user exists in the system
 * 
 */
public class ModelJunitTest {

	@Test
	public void UpdateUsertest() {

		DAO junit = new DAO();

		// assert equal condition - User doesn't exist in the system
		User testUser1 = new User();
		testUser1.setId("1630215c-2608-44b9-aad4-9d56d8aafd4c");
		testUser1.setFirstName("Steve");
		testUser1.setLastName("Jobs");
		testUser1.setEmail("abc@hotmail.com");
		Address addressDosntExists = new Address();
		addressDosntExists.setStreet("South Lincoln");
		addressDosntExists.setCity("NYC");
		addressDosntExists.setZip("61801");
		addressDosntExists.setState("NY");
		addressDosntExists.setCountry("US");
		testUser1.setAddress(addressDosntExists);
		testUser1.setDateCreated("2016-03-15T07:02:40.896Z");
		Company companyDosntExists = new Company();
		companyDosntExists.setName("Apple");
		companyDosntExists.setWebsite("http://Apple.com");
		testUser1.setCompany(companyDosntExists);
		testUser1.setProfilePic("http://staveJobs.png");
		boolean result1 = junit.userExist(testUser1);
		assertEquals("Test Case Sucessful", false, result1);

		// assert equal condition - User does exist in the system
		User testUser2 = new User();
		testUser2.setId("1630215c-2608-44b9-aad4-abcdesfgh");
		testUser2.setFirstName("Bill");
		testUser2.setLastName("Gates");
		testUser2.setEmail("upadhyay@gmail.com");
		Address addressDoesExists = new Address();
		addressDoesExists.setStreet("12 South Lincoln");
		addressDoesExists.setCity("San Jose");
		addressDoesExists.setZip("61802");
		addressDoesExists.setState("CA");
		addressDoesExists.setCountry("US");
		testUser2.setAddress(addressDosntExists);
		testUser2.setDateCreated("2016-03-15T09:02:40.896Z");
		Company companyDoesExists = new Company();
		companyDoesExists.setName("Apple");
		companyDoesExists.setWebsite("http://Apple.com");
		testUser2.setCompany(companyDosntExists);
		testUser2.setProfilePic("http://staveJobs.png");
		boolean result2 = junit.userExist(testUser2);
		assertEquals("Test Case Sucessful", true, result2);
	}

}
