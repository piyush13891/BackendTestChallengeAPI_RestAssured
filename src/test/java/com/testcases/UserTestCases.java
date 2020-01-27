package com.testcases;

import org.testng.annotations.Test;
import com.businesslayer.UserBusinessLogic;
import com.data.DataProviderClass;
import com.data.yaml.YamlTestDataForUsersAPI2;
import com.pojos.SingleUser;
import com.testcases.testsetup.TestBase;

import static org.testng.Assert.assertNull;

import org.apache.log4j.Logger;

/**
 * This class contains all the test cases related to API1 i.e. Users API
 */
public class UserTestCases extends TestBase {

	private static final Logger LOGGER = Logger.getLogger(UserTestCases.class);
	
	@Test(dataProvider = "common_test_data_provider", dataProviderClass = DataProviderClass.class)
	public void testValidateUserNotExist(YamlTestDataForUsersAPI2 apitestdata) {
		
		int userId = apitestdata.getUserId();
		
		LOGGER.info("Retrieving data for UserID - " + userId);		
		SingleUser singleUser = UserBusinessLogic.getSingleUserDataFor(userId,builder);		

		assertNull(singleUser, "Expected no users for userId - " + userId);
		LOGGER.info("No Users Found for UserId - " +  userId);			
	}	
}
