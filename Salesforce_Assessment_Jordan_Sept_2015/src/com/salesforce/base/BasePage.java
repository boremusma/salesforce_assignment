/**
 * 
 */
package com.salesforce.base;

import org.openqa.selenium.WebDriver;

import com.salesforce.utils.WebDriverGenerator;

/**
 * @author quickmobile
 *
 */
public abstract class BasePage extends Base {
	protected WebDriver driver;
	protected WebDriver secondDriver;

	/**
	 * Default constructor will launch a default local webdriver driver
	 */
	protected BasePage() {
		this.driver = WebDriverGenerator.getLocalDriver();
	}

	/**
	 * Constructor launches a customized driver
	 * 
	 * @param myDriver
	 */
	protected BasePage(WebDriver myDriver) {
		this.driver = myDriver;
	}

	protected void getSecondDriver() {
		this.secondDriver = WebDriverGenerator.getLocalDriverSecond();
	}
}
