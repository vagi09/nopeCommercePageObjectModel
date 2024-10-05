package com.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.BasePage.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import come.pages.LoginPage;

public class LoginPageTest extends BasePage {

	LoginPage loginPage;
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent-report.html");

	@BeforeMethod
	public void SetUp() {

		initializeDriverTimeouts();
		navigateToURL();
		loginPage = new LoginPage(driver);

	}

	@Test
	public void validLogin() throws InterruptedException {
		extent.createTest("Test Case: Valid Login");
		extent.attachReporter(spark);
		extent.createTest("MyFirstTest").log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");

		loginPage.clickLoginLink();
		Thread.sleep(2000);
		loginPage.enterEmail("tomHanks@hollywood.com");
		Thread.sleep(2000);
		loginPage.enterPassword("1234567890");
		Thread.sleep(2000);
		loginPage.clickLoginButton();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
		extent.flush();
	}

}
