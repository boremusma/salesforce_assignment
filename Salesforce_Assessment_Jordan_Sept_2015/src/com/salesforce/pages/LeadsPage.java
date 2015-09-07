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
public class LeadsPage extends BasePage {
	By leadPageHeader = By.xpath(this.getPropValue("pageHeader"));
	By addToCampaignBtn = By.xpath(this.getPropValue("addToCampaignBtn"));
	By viewSelector = By.xpath(this.getPropValue("viewSelector"));
	By viewSelectorGoBtn = By.xpath(this.getPropValue("viewSelectorGoBtn"));
	By activeNextPageBtn = By.xpath(this.getPropValue("activeNextPageBtn"));

	/**
	 * 
	 */
	public LeadsPage() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param myDriver
	 */
	public LeadsPage(WebDriver myDriver) {
		super(myDriver);
		if (!isOnLeadsPage())
			throw new IllegalStateException("This is not the Leads page");
	}

	/******************************
	 * Methods for View Selector
	 ***************************/
	public void selectAllLeadsView() {
		selectViewDropdown("All Leads");
	}

	private void selectViewDropdown(String opt) {
		ExecutionHelper.selectWhenReady(driver, viewSelector, opt);
		ExecutionHelper.clickWhenReady(driver, viewSelectorGoBtn);
		ExecutionHelper.waitForElementPresent(driver, addToCampaignBtn);
	}

	/******************************
	 * Methods for Active Leads Table
	 ***********************/
	public void checkMarkTheTableRow(String fullName) {
		String xpath = actionLeadsTableRow(fullName, "//input[@type='checkbox']");
		By checkbox = By.xpath(xpath);
		if (isLeadDisplayInTable(fullName))
			ExecutionHelper.clickWhenReady(driver, checkbox);
	}

	public void clickDeleteTheTableRow(String fullName, boolean accept) {
		String xpath = actionLeadsTableRow(fullName, "//span[text()='Del']");
		By delete = By.xpath(xpath);
		if (isLeadDisplayInTable(fullName)) {
			ExecutionHelper.clickWhenReady(driver, delete);
			ExecutionHelper.manageAlert(driver, accept);
		}
	}

	public CampaignsPage clickAddToCampaignButton() {
		ExecutionHelper.clickWhenReady(driver, addToCampaignBtn);
		return new CampaignsPage(this.driver);
	}

	public boolean isLeadDisplayInTable(String fullName) {
		String xpath = actionLeadsTableRow(fullName, "");
		By row = By.xpath(xpath);
		do {
			if (!ValidationHelper.waitForElementInvisible(driver, row))
				return true;
		} while (ValidationHelper.waitForElementPresent(driver, activeNextPageBtn));
		return false;
	}

	private String actionLeadsTableRow(String fullName, String actionPartialXpath) {
		String xpath = "//tr[td[div[a[span[text()='" + fullName + "']]]]]" + actionPartialXpath;
		return xpath;
	}

	private boolean isOnLeadsPage() {
		return ValidationHelper.waitForElementPresent(driver, leadPageHeader);
	}
}
