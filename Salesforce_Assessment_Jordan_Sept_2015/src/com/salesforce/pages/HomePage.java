/**
 * 
 */
package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.salesforce.base.BasePage;
import com.salesforce.utils.ExecutionHelper;
import com.salesforce.utils.ValidationHelper;

/**
 * @author quickmobile
 *
 */
public class HomePage extends BasePage {

	String homeUrlSegment = this.getPropValue("homeUrlSegment");
	By tabSetDropDownBtn = By.xpath(this.getPropValue("tabSetDropDownBtn"));
	By leadsTab = By.xpath(this.getPropValue("leadsTab"));
	By tabSetDropDownMenu = By.xpath(this.getPropValue("tabSetDropDownMenu"));

	public HomePage() {
	}

	/**
	 * @param myDriver
	 */
	public HomePage(WebDriver myDriver) {
		super(myDriver);
		if (!isOnHomePage())
			throw new IllegalStateException("This is not the Home page");
	}

	public void selectTabSetSales() {
		selectTabSetDropDownItem("Sales");
	}

	public void selectTabSetMarketing() {
		selectTabSetDropDownItem("Marketing");
	}

	public LeadsPage clickLeadsTab() {
		ExecutionHelper.clickWhenReady(driver, leadsTab);
		return new LeadsPage(this.driver);
	}

	public CampaignsPage clickCampaignsTab() {
		ExecutionHelper.clickWhenReady(driver, leadsTab);
		return new CampaignsPage(this.driver);
	}

	private void selectTabSetDropDownItem(String item) {
		if (!ExecutionHelper.waitForElementPresent(driver, tabSetDropDownBtn).getText().equals(item)) {
			ExecutionHelper.clickWhenReady(driver, tabSetDropDownBtn);
			ExecutionHelper.clickWhenReady(driver,
					By.xpath(this.getPropValue("tabSetDropDownMenu") + "/a[text='" + item + "']"));
		}
	}

	private boolean isOnHomePage() {
		return ValidationHelper.waitForElementPresent(driver, tabSetDropDownBtn);
	}

}
