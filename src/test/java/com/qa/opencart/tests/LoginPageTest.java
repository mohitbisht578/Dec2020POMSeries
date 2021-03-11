package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;


//@Listeners(ExtentReportListener.class)

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loiginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists());
	}

	@Test(priority = 3)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);

	}

}
