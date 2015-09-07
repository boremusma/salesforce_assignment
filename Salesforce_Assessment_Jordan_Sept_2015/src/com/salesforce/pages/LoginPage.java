/**
 * 
 */
package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.salesforce.base.BasePage;
import com.salesforce.utils.ExecutionHelper;
import com.salesforce.utils.TestBaseInfo;
import com.salesforce.utils.ValidationHelper;

/**
 * @author Bo Ma
 *
 */
public class LoginPage extends BasePage {

	// Define the By for the element on page
	By userNameField = By.xpath(this.getPropValue("userNameField"));
	By passwordField = By.xpath(this.getPropValue("passwordField"));
	By submitBtn = By.xpath(this.getPropValue("submitBtn"));
	By errorMsg = By.xpath(this.getPropValue("errorMsg"));
	String baseUrl = this.getPropValue("baseUrl");

	private static HomePage hp;

	/**
	 * The default constructor. Launch Login Page with default local driver
	 * Check Url to confirm on login page.
	 */
	public LoginPage() {
	}

	/**
	 * Constructor that allow passing in another driver. Check Url to confirm on
	 * login page.
	 * 
	 * @param myDriver
	 */
	public LoginPage(WebDriver myDriver) {
		super(myDriver);
		if (!isOnLoginPage())
			throw new IllegalStateException("This is not the login page");
	}

	public void typeLoginPageUrl() {
		String loginUrl = TestBaseInfo.getBaseURL();
		logger.info("Direct to Login Url " + loginUrl);
		driver.get(loginUrl);
	}

	public void fillInUserNameField(String userName) {
		logger.info("Fill in Login Page UserName field with value " + userName);
		ExecutionHelper.typeWhenReady(driver, userNameField, userName, true);
	}

	public void fillInPasswordField(String password) {
		logger.info("Fill in Login Page Password field with value " + password);
		ExecutionHelper.typeWhenReady(driver, passwordField, password, true);
	}

	public HomePage submitLogin() {
		logger.info("Click Submit button and expect to jump to HomePage.");
		clickLoginBtn();
		return new HomePage(this.driver);
	}

	public LoginPage submitInvalidLogin() {
		logger.info("Click Submit button and expect to stay on Login Page.");
		clickLoginBtn();
		return new LoginPage(this.driver);
	}

	public boolean isErrorMsgDisplay() {
		return ValidationHelper.waitForElementVisible(driver, errorMsg);
	}

	public HomePage login(String userName, String password) {
		typeLoginPageUrl();
		fillInUserNameField(userName);
		fillInPasswordField(password);
		return submitLogin();
	}

	private void clickLoginBtn() {
		ExecutionHelper.clickWhenReady(driver, submitBtn);

	}

	private boolean isOnLoginPage() {
		return ValidationHelper.checkUrl(driver, baseUrl);
	}
}
