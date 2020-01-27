package com.testcases.integrationTests;

import com.Util.*;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businesslayer.CommentsBusinessLogic;
import com.businesslayer.PostsBusinessLogic;
import com.businesslayer.UserBusinessLogic;
import com.data.DataProviderClass;
import com.data.yaml.YamlTestDataForUsersAPI1;
import com.pojos.SingleUser;
import com.testcases.testsetup.TestBase;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This class contains the test cases involving multiple APIs -  e.g. users-posts-comments
 */
public class IntegrationTestsCommentsValidations extends TestBase{

	private static final Logger LOGGER = Logger.getLogger(IntegrationTestsCommentsValidations.class);
	
	SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "common_test_data_provider", dataProviderClass = DataProviderClass.class)
	public void testValidateCommentsEmailByUserName(YamlTestDataForUsersAPI1 apitestdata) {

		String userName = apitestdata.getUser();			
		SingleUser singleUser = UserBusinessLogic.getSingleUserDataFor(userName, builder);	
		
		LOGGER.info("Single user detail retrieved.");
		Reporter.log("| Single user details retrieved |");
		
		assertNotNull(singleUser, "Expected not null for singleUser - " + singleUser);
			
		LOGGER.info("UserId for the username " +  userName + " is " + singleUser.getId());						
			
		List<Integer> postIds = PostsBusinessLogic.getListOfPostIdsFromUserId(singleUser.getId(),builder);		
			
		LOGGER.info("postIds detail retrieved.");
		Reporter.log("| postIds details retrieved |");
			
		assertNotEquals(postIds.size(), 0,  "No Posts there for the userId - " + singleUser.getId());			
							
		Iterator<Integer> postIdIterator = postIds.iterator();				
				
		while (postIdIterator.hasNext()) 
		{				
			int tempPostId=postIdIterator.next();	
			
			LOGGER.info("Retrieving CommentIds for PostID - " +  tempPostId);
			Reporter.log("| Retrieving CommentIds for PostID |");
			
			List<Integer> commentIds = CommentsBusinessLogic.getListOfCommentIdsFromPostId(tempPostId,builder);
			
			LOGGER.info("CommentIds detail retrieved.");			
			
			assertNotEquals(commentIds.size(), 0,  "No Posts there for the postId  - " + tempPostId);
			
			Iterator<Integer> commentIdIterator = commentIds.iterator();
			
			Reporter.log("| Retrieving and Validating emails for comment Ids |");
			while (commentIdIterator.hasNext()) {
										
				int commentId=commentIdIterator.next();
				
				LOGGER.info("Retrieving Email for CommentId - " +  commentId);
				String tempEmail=CommentsBusinessLogic.getEmailIdFromCommentId(commentId,builder);
				
				//Validate Email					
				CommonFunctions commonFun=new CommonFunctions();
				boolean isEmailValid = commonFun.validateEmailApache(tempEmail);
				
				if(isEmailValid){
					LOGGER.info("Email for CommentId " +  commentId + " is Valid - " + tempEmail);
				}
				else{
					LOGGER.error("Email for CommentId " +  commentId + " is not Valid - " + tempEmail);
				}
				softAssert.assertTrue(isEmailValid, "Email for CommentId " +  commentId + " is not Valid - " + tempEmail);														
			}	
		}
		softAssert.assertAll();
	}	
}
