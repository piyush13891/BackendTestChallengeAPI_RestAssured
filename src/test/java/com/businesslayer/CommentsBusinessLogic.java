package com.businesslayer;

import static io.restassured.path.json.JsonPath.from;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.data.PropertyFileReader;
import com.pojos.SingleComment;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;

/**
  * This class will contains all logic related to API /comments
 */

public class CommentsBusinessLogic {

	private static final Logger LOGGER = Logger.getLogger(CommentsBusinessLogic.class);

	/**
	 * To fetch the EmailId for CommentId
	 */
	
	public static String getEmailIdFromCommentId(int commentId, RequestSpecBuilder builder) {
		
		LOGGER.info("Reading properties for Request - URL and Paths");
		try{
			
			String commentsPath = PropertyFileReader.getPropertyData().getApis().get("comments");		
	
			builder.setBasePath(commentsPath);
			builder.addQueryParam("id", commentId);
			
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
			assertEquals(response.statusCode(), 200, "Status code validation failed");	
						
			if(from(response.getBody().asString()).getList("$").size()==0){
				LOGGER.error("No Comments found. Please check the query data.");  
			    return null;
			}
			
			SingleComment[] singleComment = response.getBody().as(SingleComment[].class);
			LOGGER.info("Emails for Comment Id - " +  commentId + " is/are " + singleComment[0].getEmail());
			
			return singleComment[0].getEmail();
		}finally{
			builder.setBasePath("");
			builder.removeQueryParam("id");
		}
	}

	/**
	 * Fetch list of all commentIds from the postId
	 */
	public static List<Integer> getListOfCommentIdsFromPostId(int postId, RequestSpecBuilder builder) {
		
		LOGGER.info("Reading properties for Request - URL and Paths");
			try{
			String commentsPath = PropertyFileReader.getPropertyData().getApis().get("comments");
	
			builder.setBasePath(commentsPath);
			builder.addQueryParam("postId", postId);
			
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
					LOGGER.error("Not able to reach the Host. Please check the URL.");
					Assert.fail("Not able to reach the host. Please check the URL.");
				}
				else{
					LOGGER.error("Getting exception."  +  e.getMessage());
					Assert.fail("Getting exception."  +  e.getMessage());
				}
			}
			
			LOGGER.info("Response code for the request - " + response.getStatusCode());		
			assertEquals(response.statusCode(), 200, "Comments - Status code validation");
			
			if(from(response.getBody().asString()).getList("$").size()==0){
				LOGGER.error("No Comments found. Please check the query data."); 
			    return from(response.getBody().asString()).getList("$");
			}
			
			List<Integer> listOfElements = from(response.getBody().asString()).getList("id");
			LOGGER.info("Comment Ids of the PostId - " +  postId + " is/are " + listOfElements);
			
			return listOfElements;		
			}finally{
				builder.setBasePath("");
				builder.removeQueryParam("postId");
			}
	}	
	
	/**
	 * Fetch comment data from the commentId and populate POJO
	 */
	public static SingleComment getSingleCommentDataFor(int commentId, RequestSpecBuilder builder) {
		
		LOGGER.info("Reading properties for Request - URL and Paths");
		
		try{
			String commentPath = PropertyFileReader.getPropertyData().getApis().get("comments");
			
			builder.setBasePath(commentPath);
			builder.addQueryParam("id", commentId);
			
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
				LOGGER.error("No Comments found. Please check the query data."); 
			    return null;
			}		
			
			SingleComment[] singleComments = response.getBody().as(SingleComment[].class);
			LOGGER.info("Single Comment :" + singleComments);
				
			return singleComments[0];	
		}finally{
			builder.setBasePath("");
			builder.removeQueryParam("id");
		}
	}
}
