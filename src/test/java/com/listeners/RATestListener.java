package com.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class RATestListener implements ITestListener {
	private static final Logger LOGGER = Logger.getLogger(RATestListener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		LOGGER.info("************ STARTING EXECUTION OF TEST METHOD  " + result.getName()+ " ************");	
		Reporter.log("************ STARTING EXECUTION OF TEST METHOD  " + result.getName()+ " ************<br>");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOGGER.info("************ FINISHED EXECUTION OF TEST METHOD  " + result.getName()+ " ************");	
		Reporter.log("************ FINISHED EXECUTION OF TEST METHOD  " + result.getName()+ " ************<br>");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOGGER.info("************ FAILED EXECUTION OF TEST METHOD  " + result.getName()+ " ************");
		Reporter.log("************ FAILED EXECUTION OF TEST METHOD  " + result.getName()+ " ************<br>");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("************ SKIPPED EXECUTION OF TEST METHOD  " + result.getName()+ " ************");
		Reporter.log("************ SKIPPED EXECUTION OF TEST METHOD  " + result.getName()+ " ************<br>");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		LOGGER.info("************ TEST FAILED - PASSING PERCENTAGE " + result.getName() + " ************");
		Reporter.log("************ TEST FAILED - PASSING PERCENTAGE " + result.getName()+ " ************<br>");
	
	}

	@Override
	public void onStart(ITestContext context) {
		LOGGER.info("************ STARTING EXECUTION OF TEST " + context.getName() + " ************");
		Reporter.log("************ STARTING EXECUTION OF TEST  " + context.getName()+ " ************<br>");
	}

	@Override
	public void onFinish(ITestContext context) {
		LOGGER.info("************ FINISHED EXECUTION OF TEST " + context.getName() + " ************");
		Reporter.log("************ FINISHED EXECUTION OF TEST  " + context.getName()+ " ************<br>");
	}

}
