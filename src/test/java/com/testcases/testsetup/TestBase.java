package com.testcases.testsetup;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;

import com.data.PropertyFileReader;

import io.restassured.builder.RequestSpecBuilder;

public class TestBase {

	protected RequestSpecBuilder builder;
		
	@BeforeClass
	public void setUp(){
		
		Reporter.log("Setting up base for the test");
		
		String baseURL = PropertyFileReader.getPropertyData().getApis().get("homeurl");		
		builder=new RequestSpecBuilder();		
		builder.setBaseUri(baseURL);	
		
		builder.build().log().all();
	}	
}
