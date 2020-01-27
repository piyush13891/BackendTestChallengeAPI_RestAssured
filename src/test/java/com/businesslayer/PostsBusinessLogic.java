package com.businesslayer;

import static io.restassured.path.json.JsonPath.from;
import static org.testng.Assert.assertEquals;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.data.PropertyFileReader;
import com.pojos.SinglePost;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;

/**
  * This class will contains all logic related to API /posts
 */
public class PostsBusinessLogic {

	private static final Logger LOGGER = Logger.getLogger(PostsBusinessLogic.class);

	/**
	 * Fetch list of all PostIds from the userId
	 */
	
	public static List<Integer> getListOfPostIdsFromUserId(int userId, RequestSpecBuilder builder) {
		
		LOGGER.info("Reading properties for Request - URL and Paths");
		try{		
			String postsPath = PropertyFileReader.getPropertyData().getApis().get("posts");
			
			builder.setBasePath(postsPath);
			builder.addQueryParam("userId", userId);			
			
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
				return from(response.getBody().asString()).getList("$");
			}
			
			List<Integer> listOfElements = from(response.getBody().asString()).getList("id");
			LOGGER.info("Posts Ids for the userId - " +  userId + " is/are " + listOfElements);	
			
			return listOfElements;
		}finally{
			builder.setBasePath("");
			builder.removeQueryParam("userId");
		}
	}

	/**
	 * Fetch post data from the postId and populate POJO
	 */
	public static SinglePost getSinglePostDataFor(int postId, RequestSpecBuilder builder) {
		
		LOGGER.info("Reading properties for Request - URL and Paths");
		
		try{
			String postsPath = PropertyFileReader.getPropertyData().getApis().get("posts");
			
			builder.setBasePath(postsPath);
			builder.addQueryParam("id", postId);
			
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
			assertEquals(response.statusCode(), 200, "Response Status code validation failed");
					
			if(from(response.getBody().asString()).getList("$").size()==0){
				LOGGER.error("No Posts found. Please check the query data."); 
			    return null;
			}		
			
			SinglePost[] singlePost = response.getBody().as(SinglePost[].class);
			LOGGER.info("Single Post : " + singlePost);
				
			return singlePost[0];	
		}finally{
			builder.setBasePath("");
			builder.removeQueryParam("id");
		}
	}
}
