package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencart.dataprovider.DataProviders;
import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void setUpRegister() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
//	@Test
//	public void userRegisterationTest() {
//		
//		Assert.assertTrue(registerPage.accountRegistration("Tom", "arora",, "99877", "test123", "yes"), Errors.ACCOUNT_FAILED_MSG);
//	}
	
	//	return randomGenerator.nextInt(1000);
		 
		 


//	
//	@DataProvider
//	public Object[][] getRegisterData() {
//		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
//		return data;
//	}
//	dataProvider = "email", dataProviderClass = DataProviders.class
//	dataProvider = "getRegisterData" 
			
	@Test(dataProvider = "getRegisterData", dataProviderClass = DataProviders.class)
	public void userRegsiterationTest(String firstName, String lastName, String phone, 
			String password, String subscribe) {
		
		Assert.assertTrue(registerPage.accountRegistration(firstName, lastName, registerPage.getRandomNumber(), phone, 
							password, subscribe));
	}

}
