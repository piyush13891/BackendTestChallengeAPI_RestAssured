package com.testcases;

import org.testng.annotations.Test;

import com.businesslayer.CommentsBusinessLogic;
import com.data.DataProviderClass;
import com.data.yaml.YamlTestDataForCommentsAPI1;
import com.pojos.SingleComment;
import com.testcases.testsetup.TestBase;

import static org.testng.Assert.assertNull;

import org.apache.log4j.Logger;

/**
 * This class contains all the test cases related to API1 i.e. Comments API
 */
public class CommentsTestCases extends TestBase{

	private static final Logger LOGGER = Logger.getLogger(CommentsTestCases.class);
	
	@Test(dataProvider = "common_test_data_provider", dataProviderClass = DataProviderClass.class)
	public void testValidateCommentNotExist(YamlTestDataForCommentsAPI1 apitestdata) {
		
		int commentId = apitestdata.getCommnetId();
		
		LOGGER.info("Retrieving data for CommentId - " + commentId);		
		SingleComment singleComment = CommentsBusinessLogic.getSingleCommentDataFor(commentId,builder);
		
		LOGGER.info("No comment Found for CommentId - " +  commentId);
		assertNull(singleComment, "Expected no comment for CommentId - " + commentId);

	}
}
