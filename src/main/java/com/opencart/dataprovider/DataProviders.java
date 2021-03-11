package com.opencart.dataprovider;

import org.testng.annotations.DataProvider;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class DataProviders {
	
	
	@DataProvider()
	public Object[][] getRegisterData() {
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
	
	
	

}
