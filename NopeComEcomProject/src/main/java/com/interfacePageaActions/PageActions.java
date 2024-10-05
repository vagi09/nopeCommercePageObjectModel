package com.interfacePageaActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface PageActions {

	void navigateToURL();

	String getPageTitle();

	void enterText(By locator, String text);

	String getText(By locator);

	void click(By locator);

	void selectFromDropdown(By locator, String visibleText);
	
//	void selectFromDropdownUsingIndex(By locator, int index);

	List<WebElement> findElements(By locator);

	boolean isElementSelected(By locator);

	boolean isElementVisible(By locator);

	boolean isElementEnabled(By locator);

	boolean isElementDisplayed(By locator);

	void handleAlert(boolean accept);

	void dragAndDrop(By sourceLocator, By targetLocator);

	void performKeyboardAction(String action);

	public String getTimestamp();

	void takeScreenshot(String fileName);

}
