package com.businesslayer;

import static io.restassured.path.json.JsonPath.from;
import static org.testng.Assert.assertEquals;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.data.PropertyFileReader;
import com.pojos.SingleUser;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;


/**
  * This class will contains all logic related to API /users
 */
public class UserBusinessLogic {
	private static final Logger LOGGER = Logger.getLogger(UserBusinessLogic.class);
	/*
	 * Extract Data for a user from username and populate POJO
	 */
	public static SingleUser getSingleUserDataFor(String username, RequestSpecBuilder builder) {
		
		LOGGER.info("Reading properties for Request - URL and Paths");
		
		try{
			String usersBasePath = PropertyFileReader.getPropertyData().getApis().get("users");
					
			builder.setBasePath(usersBasePath);
			builder.addQueryParam("username", username);
						
			QueryableRequestSpecification queryableRequest = SpecificationQuerier.query(builder.build());
			LOGGER.info("Request URI : " + queryableRequest.getURI());
			LOGGER.info("Request Query Parameters : " + queryableRequest.getQueryParams());
			LOGGER.info("Request Body : " + queryableRequest.getBody());
			
			Response response=null;
			try{
				 response = RestAssured.given().spec(builder.build()).get();
				 LOGGER.info("Response Data : " +response.then().extract().asString());	
			}		
			catch (Exception e) {			
				if(e instanceof java.net.UnknownHostException){
					LOGGER.error("Not able to reach the Host. Please check the URL." );
					Assert.fail("Not able to reach the host. Please check the URL." );
				}
				else{
					LOGGER.error("Getting exception."  +  e.getMessage());
					Assert.fail("Getting exception."  +  e.getMessage());
				}
			}
			
			LOGGER.info("Response code for the request - " + response.getStatusCode());
			assertEquals(response.getStatusCode(), 200, "Response status code validation Failed.");			
			
			if(from(response.getBody().asString()).getList("$").size()==0){
				LOGGER.error("No Users found. Please check the query data."); 			
				return null;
			}				
			
			SingleUser[] singleUser = response.getBody().as(SingleUser[].class);
			LOGGER.info("Retrieved User details - " + singleUser);
			
			return singleUser[0];	
		}finally{
			builder.setBasePath("");
			builder.removeQueryParam("username");
		}
	}	
	
	/*
	 * Extract Data for a user from userId and populate POJO
	 */
	
	public static SingleUser getSingleUserDataFor(int userId, RequestSpecBuilder builder) {
		
		LOGGER.info("Reading properties for Request - URL and Paths");
		try{			
			String usersBasePath = PropertyFileReader.getPropertyData().getApis().get("users");
			
			builder.setBasePath(usersBasePath);
			builder.addQueryParam("id", userId);
			
			QueryableRequestSpecification queryableRequest = SpecificationQuerier.query(builder.build());
			LOGGER.info("Request URI : " + queryableRequest.getURI());
			LOGGER.info("Request Query Parameters : " + queryableRequest.getQueryParams());
			LOGGER.info("Request Body : " + queryableRequest.getBody());
			
			Response response=null;
			try{
				 response = RestAssured.given().spec(builder.build()).get();
				 LOGGER.info("Response Data : " +response.then().extract().asString());	
			}		
			catch (Exception e) {			
				if(e instanceof java.net.UnknownHostException){
					LOGGER.error("Not able to reach the Host. Please check the URL." );
					Assert.fail("Not able to reach the host. Please check the URL." );
				}
				else{
					LOGGER.error("Getting exception."  +  e.getMessage());
					Assert.fail("Getting exception."  +  e.getMessage());
				}
			}
			
			LOGGER.info("Response code for the request - " + response.getStatusCode());
			assertEquals(response.getStatusCode(), 200, "Response status code validation Failed.");

			
			if(from(response.getBody().asString()).getList("$").size()==0){
				LOGGER.error("No Users found. Please check the query data."); 			
				return null;
			}				
			
			SingleUser[] singleUser = response.getBody().as(SingleUser[].class);
			LOGGER.info("Retrieved User details - " + singleUser.toString());
			
			return singleUser[0];	
		}finally{
			builder.setBasePath("");
			builder.removeQueryParam("id");
		}
	}	
}
