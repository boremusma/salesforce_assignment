/**
 * 
 */
package com.salesforce.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.salesforce.base.BaseTestCase;
import com.salesforce.pages.CampaignsPage;
import com.salesforce.pages.HomePage;
import com.salesforce.pages.LeadsPage;
import com.salesforce.pages.LoginPage;
import com.salesforce.utils.TestBaseInfo;

/**
 * @author Bo Ma
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest extends BaseTestCase {
	static LoginPage loginP;
	static HomePage homeP;
	static LeadsPage leadsP;
	static CampaignsPage campaignP;

	String leadName = this.getPropValue("leadName");
	String campaignName = this.getPropValue("campaignName");

	@Before
	public void init() {
		loginP = new LoginPage();
	}

	@Test
	public void mainTest() {
		try {
			homeP = loginP.login(TestBaseInfo.getUserName(), TestBaseInfo.getPassword());
			homeP.selectTabSetSales();
			leadsP = homeP.clickLeadsTab();
			leadsP.selectAllLeadsView();
			leadsP.checkMarkTheTableRow(leadName);
			campaignP = leadsP.clickAddToCampaignButton();
			leadsP = campaignP.lookedUpCampaign(campaignName);

		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@After
	public void shutdown() {

	}
}
