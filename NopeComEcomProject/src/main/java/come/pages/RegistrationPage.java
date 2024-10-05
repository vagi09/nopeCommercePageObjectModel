package come.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.BasePage.BasePage;

import freemarker.core.ReturnInstruction.Return;

public class RegistrationPage extends BasePage {

	public By registrationFormBy = By.xpath("//div[@class='page registration-page']");
	public By RegLink = By.xpath("//a[contains(text(),'Register')]");
	public By firstName = By.id("FirstName");
	public By LastName = By.id("LastName");
	public By Email = By.id("Email");
	public By CompanyName = By.id("Company");
	public By Password = By.id("Password");
	public By confirmPassword = By.id("ConfirmPassword");
	public By RegisterButton = By.id("register-button");
	public By dateOfBirthBy = By.xpath("//select[@name='DateOfBirthDay']");
	public By monthOfBirthDay = By.xpath("//select[@name='DateOfBirthMonth']");
	public By yearOfBirthDay = By.xpath("//select[@name='DateOfBirthYear']");
	public By regPageErrorMsgs = By.xpath("//span[@class='field-validation-error']");
	public By genderMaleRadioBtn = By.id("gender-male");
	public By genderFemaleRadioBtn = By.id("gender-female");
	public By nopeComLogo = By.xpath("//img[@alt='nopCommerce demo store']");

	public boolean validateNopeComlogo() {

		return isElementDisplayed(nopeComLogo);

	}

//	public boolean isRegistrationFormVisible(){
//		
//		return isElementVisible(registrationFormBy);
//		
//		
//	}

	public boolean reg_form_isvisible() {

		return isElementVisible(registrationFormBy);
	}

	public String test_page_title() {

		return getPageTitle();

	}

	public void click_reg_link() {

		click(RegLink);
	}

	public void click_male_radiobtn() {

		click(genderMaleRadioBtn);
	}

	public void enter_first_name(String firstname) {

		enterText(firstName, firstname);

	}

	public void enter_last_name(String lastname) {

		enterText(LastName, lastname);

	}

	public void enter_email(String email) {

		enterText(Email, email);

	}

	public void enter_company(String company) {

		enterText(CompanyName, company);

	}

	public void enter_password(String password) {

		enterText(Password, password);

	}

	public void enter_confirm_password(String confPassword) {

		enterText(confirmPassword, confPassword);

	}

	public void click_reg_button() {

		click(RegisterButton);
	}

	public boolean is_registration_button_visible() {

		return isElementVisible(RegisterButton);

	}

	public boolean isGenderRadioBtnSelected() {

		return isElementSelected(genderMaleRadioBtn);

	}

	public void selectBirthDayDate(String date) {

		selectFromDropdown(dateOfBirthBy, date);
	}

	public void selectBirthMonth(String month) {

		selectFromDropdown(monthOfBirthDay, month);
	}

	public void selectBirthYear(String year) {

		selectFromDropdown(yearOfBirthDay, year);
	}

	public List<WebElement> findMultipleElements(By locator) {
		return findElements(locator);
	}

}
