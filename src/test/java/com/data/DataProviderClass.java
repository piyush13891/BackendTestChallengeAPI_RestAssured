package com.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;

import com.data.yaml.AllTestCaseData;
import com.data.yaml.TestData;

/**
 * Here we have single DP method for any type of data present in all_test_data.yaml
 */
public class DataProviderClass {
	
	private static final Logger LOGGER = Logger.getLogger(DataProviderClass.class);

	@DataProvider(name="common_test_data_provider")
	public Object[][] getDataFromYamlFile(Method method)
	{
		Yaml yaml = new Yaml();
		AllTestCaseData allTestCaseData = null; 
		String testCasePath = ".//src//test//resources//testdata//all_test_data.yaml";
		
		LOGGER.info("Test data file path - " +  testCasePath);
		
		try {
			allTestCaseData = yaml.loadAs(new FileReader(new File(testCasePath)), AllTestCaseData.class);
		} catch (FileNotFoundException e) {
			LOGGER.info("Please validate test data file path. Path not found - " +  testCasePath);
			Assert.fail("Please validate test data file path. Path not found - " +  testCasePath);
		}
		
		String testCaseName = method.getName();
		List<TestData> testDataSets = allTestCaseData.getAllTestCaseDataMap().get(testCaseName);  //list will be specific for @test method						
	
		Object[][] data = null;
		
		try{
			data = new Object[testDataSets.size()][1]; //on runtime, rows will be decided, column will always be 1 in all the cases
		}catch(NullPointerException e) {
			LOGGER.info("No TestData found for test case - " + testCaseName );
			Assert.fail("No TestData found for test case - " + testCaseName );
		}
		
		//on runtime Object[n][1] array will be populated based on list size
		for (int i = 0; i < testDataSets.size(); i++) {
			data[i][0] = testDataSets.get(i);
		}
	
		return data;
	}
	
	
}
