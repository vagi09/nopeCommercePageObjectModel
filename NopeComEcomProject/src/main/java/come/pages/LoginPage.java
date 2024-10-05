package come.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.BasePage.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[contains(text(),'Log in')]")
	WebElement loginPageLink;

	@FindBy(name = "Email")
	WebElement email;

	@FindBy(name = "Password")
	WebElement password;

	@FindBy(xpath = "//button[text()='Log in']")
	WebElement loginbtn;

////	By loginPageLink = By.xpath("//a[contains(text(),'Log in')]");
//	By email = By.name("Email");
//	By name = By.name("Password");
//	By loginbtn = By.xpath("//button[text()='Log in']");

	public void clickLoginLink() {

		loginPageLink.click();

	}

	public void enterEmail(String un) {

		email.sendKeys(un);

	}

	public void enterPassword(String pw) {

		password.sendKeys(pw);

	}

	public void clickLoginButton() {

		loginbtn.click();

	}

}
