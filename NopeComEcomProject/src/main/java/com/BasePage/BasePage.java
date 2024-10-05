package com.BasePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.Alert;

import com.interfacePageaActions.PageActions;
import com.utils.TestUtils;

public class BasePage implements PageActions {

	public static WebDriver driver;
	public static Properties prop;

	public BasePage() {

		try {

			prop = new Properties();
			FileInputStream ip = new FileInputStream("src/main/java/com/config/properties" + "/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initializeDriverTimeouts() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {

			driver = new FirefoxDriver();

		} else if (browserName.equals("edge")) {

			driver = new EdgeDriver();

		} else if (browserName.equals("safari")) {

			driver = new SafariDriver();

		} else {
			System.out.println("No Such Browser Exist");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(TestUtils.SCRIPT_TIMEOUT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICIT_WAIT));
	}

	// Method to navigate to a specific URL
	public void navigateToURL() {
		driver.get(prop.getProperty("url"));
	}

	// Method to get page Title
	public String getPageTitle() {

		return driver.getTitle();
	}

	/*
	 * Method to test if element is visible w/o explicit wait
	 * 
	 * public boolean isElementVisible(By locator) {
	 * 
	 * WebElement element = driver.findElement(locator);
	 * 
	 * return element.isDisplayed(); }
	 * 
	 */

	public boolean isElementVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayed(By locator) {

		WebElement element = driver.findElement(locator);

		return element.isDisplayed();

	}

	public boolean isElementSelected(By locator) {

		WebElement element = driver.findElement(locator);

		return element.isSelected();

	}

	public boolean isElementEnabled(By locator) {

		WebElement element = driver.findElement(locator);

		return element.isEnabled();

	}

	// Method to enter text into a web element
	public void enterText(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	// Method to get text from a web element
	public String getText(By locator) {
		WebElement element = driver.findElement(locator);
		return element.getText();
	}

	// Method to click on a web elements like `Buttons`, `Radio buttons`, `Check
	// Boxes`
	public void click(By locator) {
		WebElement element = driver.findElement(locator);
		element.click();
	}

	// Method to select an option from a dropdown by visible text
	public void selectFromDropdown(By locator, String visibleText) {
		WebElement dropdownElement = driver.findElement(locator);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(visibleText);
	}

	// Method to find multiple elements by a given locator
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	// Method to Handle Alerts
	public void handleAlert(boolean accept) {
		try {
			Alert alert = driver.switchTo().alert();
			// get the alert text
			System.out.println("ALERT TEXT: " + alert.getText());

			if (accept) {
				alert.accept(); // To accept the alert
			} else {
				alert.dismiss(); // To dismiss the alert
			}

		} catch (NoAlertPresentException e) {
			// Handle the case when no alert is present
			System.out.println("No alert present.");
		}
	}

	// Method to perform drag-and-drop
	public void dragAndDrop(By sourceLocator, By targetLocator) {
		WebElement sourceElement = driver.findElement(sourceLocator);
		WebElement targetElement = driver.findElement(targetLocator);

		Actions actions = new Actions(driver);
		actions.dragAndDrop(sourceElement, targetElement).build().perform();
	}

	// Method to perform keyboard actions
	public void performKeyboardAction(String action) {
		Actions actions = new Actions(driver);

		switch (action.toLowerCase()) {
		case "pressenter":
			actions.sendKeys(Keys.ENTER).build().perform();
			break;
		case "pressescape":
			actions.sendKeys(Keys.ESCAPE).build().perform();
			break;
		case "presstab":
			actions.sendKeys(Keys.TAB).build().perform();
			break;
		// Add more cases for other keyboard actions as needed
		default:
			System.out.println("Unsupported keyboard action: " + action);
		}
	}

	// Method to take a screenshot
	public void takeScreenshot(String fileName) {
		if (driver instanceof TakesScreenshot) {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
			String downloadsFolder = "C:/Users/manas/Downloads/Selenium_Training/SeleniumSyllabus/";
			Path destinationPath = new File(downloadsFolder).toPath();
			File destinationFile = new File(destinationPath + getTimestamp() + "_" + fileName);

			try {
				Files.copy(sourceFile.toPath(), destinationFile.toPath());
				System.out.println("Screenshot captured: " + destinationFile.getAbsolutePath());
			} catch (IOException e) {
				System.err.println("Error while taking screenshot: " + e.getMessage());
			}
		} else {
			System.err.println("Driver does not support taking screenshots.");
		}
	}

	// Method to generate a timestamp for screenshot file names
	public String getTimestamp() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	}

}
