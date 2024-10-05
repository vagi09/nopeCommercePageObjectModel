package com.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.BasePage.BasePage;

import come.pages.RegistrationPage;

public class RegistrationPageTest extends BasePage {

	RegistrationPage registrationpage;

	public RegistrationPageTest() {

		super();
	}

	@BeforeMethod
	public void SetUp() {

		initializeDriverTimeouts();
		navigateToURL();
		registrationpage = new RegistrationPage();

	}

	@Test(priority = 6)

	public void testNopeComLogo() {

		boolean flag = registrationpage.validateNopeComlogo();

		Assert.assertTrue(flag);

		Assert.assertTrue(registrationpage.validateNopeComlogo(), "CRM logo is not Displayed");

	}

	@Test(priority = 1, enabled = true)
	public void test_reg_form() {

		registrationpage.click_reg_link();

		Assert.assertTrue(registrationpage.reg_form_isvisible(), "Registration form is visible.");

	}

	@Test(priority = 2, enabled = true)

	public void test_Webpage_title() {

		String actualPageTitle = registrationpage.test_page_title();

		System.out.println("Title of the web page is: " + actualPageTitle);

		String expectedPageTitle = "nopCommerce demo store";
		String invalidPageTitle = "nopCommerce demo";

		Assert.assertEquals(actualPageTitle, expectedPageTitle, "Invalid Page title");
		Assert.assertNotEquals(actualPageTitle, invalidPageTitle);

	}

	@Test(priority = 3, enabled = true)
	public void test_registration_new_user() throws InterruptedException {

		registrationpage.click_reg_link();
		Thread.sleep(2000);
		registrationpage.click_male_radiobtn();
		Assert.assertTrue(registrationpage.isGenderRadioBtnSelected(), "Male Radio Button is not selected");
		registrationpage.enter_first_name("Tom");
		registrationpage.enter_last_name("Hanks");
		registrationpage.selectBirthDayDate("9");
		Thread.sleep(2000);
		registrationpage.selectBirthMonth("March");
		Thread.sleep(2000);
		registrationpage.selectBirthYear("1990");
		registrationpage.enter_email("tomHanks@hollywood.com");
		Thread.sleep(2000);
		registrationpage.enter_company("prichett closets");
		registrationpage.enter_password("1234567890");
		registrationpage.enter_confirm_password("1234567890");
		registrationpage.click_reg_button();

	}

	@Test(priority = 4, enabled = true)

	public void test_reg_button() {

		registrationpage.click_reg_link();

		Assert.assertTrue(registrationpage.is_registration_button_visible(), "Registration button is not visible.");

	}

	@Test(priority = 5, enabled = true)
	public void test_regPage_errorMsgs() {
		registrationpage.click_reg_link();

		registrationpage.click_reg_button();

		List<String> expectedErrorMessages = new ArrayList<>();
		expectedErrorMessages.add("First name is required.");
		expectedErrorMessages.add("Last name is required.");
		expectedErrorMessages.add("Email is required.");
		expectedErrorMessages.add("Password is required.");
		expectedErrorMessages.add("Password is required.");

		List<WebElement> elements = registrationpage.findMultipleElements(registrationpage.regPageErrorMsgs);
		// Print the text of each found element
		// Validate or assert against the actual messages
		for (int i = 0; i < Math.min(elements.size(), expectedErrorMessages.size()); i++) {
			String actualMessage = elements.get(i).getText();
			System.out.println(actualMessage);
			String expectedMessage = expectedErrorMessages.get(i);

			Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch at index " + i);
		}

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
