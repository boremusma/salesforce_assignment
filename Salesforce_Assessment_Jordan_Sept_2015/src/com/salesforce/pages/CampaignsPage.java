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
public class CampaignsPage extends BasePage {
	By campaignsPageHeader = By.xpath(this.getPropValue("pageHeader"));

	/**
	 * @param myDriver
	 */
	public CampaignsPage(WebDriver myDriver) {
		super(myDriver);
		if (!isOnCampaignsPage())
			throw new IllegalStateException("This is not the Campaigns page");
	}

	/**************************
	 * Campaigns Main Page
	 ********************************/
	By campaignLookUpBtn = By.xpath(this.getPropValue("campaignLookUpBtn"));
	By addToCampaignBtn = By.xpath(this.getPropValue("addToCampaignBtn"));
	By doneBtn = By.xpath(this.getPropValue("doneBtn"));

	public void clickCampaignLookUpBtn() {
		ExecutionHelper.clickWhenReady(driver, campaignLookUpBtn);
	}

	public void clickAddToCampaignBtn() {
		String win = driver.getWindowHandle();
		ExecutionHelper.clickWhenReady(driver, addToCampaignBtn);
	}

	public LeadsPage clickDoneBtn() {
		ExecutionHelper.clickWhenReady(driver, doneBtn);
		return new LeadsPage(this.driver);
	}

	public LeadsPage lookedUpCampaign(String campaign) {
		String winHandlePrev = driver.getWindowHandle();
		clickCampaignLookUpBtn();
		getSecondDriver();
		for (String win : driver.getWindowHandles()) {
			if (!win.equals(winHandlePrev))
				secondDriver = driver.switchTo().window(win);
		}
		driver.switchTo().window(winHandlePrev);
		secondDriver.switchTo()
				.frame(ExecutionHelper.waitForElementPresent(driver, By.xpath("//frame[@id='searchFrame']")));
		selectCampaignViewScopeAllCampaigns(secondDriver);
		fillInCampaignSearchTextField(secondDriver, campaign);
		clickCampaignGoBtn(secondDriver);
		secondDriver.switchTo().parentFrame();
		secondDriver.switchTo()
				.frame(ExecutionHelper.waitForElementPresent(driver, By.xpath("//frame[@id='resultsFrame']")));
		clickCampaignSearchResult(secondDriver, campaign);
		clickAddToCampaignBtn();
		return clickDoneBtn();
	}

	/**************************
	 * Campaigns Lookup Popup Page
	 ************************/
	By campaignScopeSelector = By.xpath(this.getPropValue("campaignScopeSelector"));
	By campaignScopeSearchTextField = By.xpath(this.getPropValue("campaignScopeSearchTextField"));
	By campaignScopeGoBtn = By.xpath(this.getPropValue("campaignScopeGoBtn"));

	public void selectCampaignViewScopeAllCampaigns(WebDriver driver) {
		selectCampaignViewScope(driver, "All Campaigns");
	}

	public void fillInCampaignSearchTextField(WebDriver driver, String text) {
		ExecutionHelper.typeWhenReady(driver, campaignScopeSearchTextField, text, true);
	}

	public void clickCampaignGoBtn(WebDriver driver) {
		ExecutionHelper.clickWhenReady(driver, campaignScopeGoBtn);
	}

	public void clickCampaignSearchResult(WebDriver driver, String campaignName) {
		By record = By.xpath("//div[@class='listRelatedObject lookupBlock']//a[text()='" + campaignName + "']");
		ExecutionHelper.clickWhenReady(driver, record);
	}

	private void selectCampaignViewScope(WebDriver driver, String opt) {
		ExecutionHelper.selectWhenReady(driver, campaignScopeSelector, opt);
	}

	private boolean isOnCampaignsPage() {
		return ValidationHelper.waitForElementPresent(driver, campaignsPageHeader);
	}
}
