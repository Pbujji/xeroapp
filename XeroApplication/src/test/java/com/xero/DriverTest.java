package com.xero;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.xero.*;

public class DriverTest {
	static WebDriver driver;
	@Test
   public void driver() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	String dtTablePath="/Users/jbaisani/Downloads/Test_Softwares/SeleniumScripts.xls";
	String[][] recData=ReusableMethods.readExcel(dtTablePath, "Sheet2");
	String testCase,flag,chrome,firefox;
	
	for(int i=0;i<recData.length;i++){
		flag=recData[i][2];
		if(flag.equalsIgnoreCase("Y")){
			firefox=recData[i][3];
		if(firefox.equalsIgnoreCase("Y")){
		testCase=recData[i][1];
		System.out.println("entered  into   "+testCase);
		ReusableMethods.startReport(testCase,"/Users/jbaisani/Documents/workspace/tekArch/XeroApplication/seleniumReport/");
		System.setProperty("webdriver.gecko.driver","/Users/jbaisani/Downloads/Test_Softwares/geckodriver");
		driver=new FirefoxDriver();
		Method tc=AutomationScript.class.getMethod(testCase);
		System.out.println(tc);
		tc.invoke(tc);
		driver.quit();
		ReusableMethods.bw.close();
	}
		chrome = recData[i][4];
		if(chrome.equalsIgnoreCase("Y")){
			testCase = recData[i][1];
			ReusableMethods.startReport(testCase, "/Users/jbaisani/Documents/workspace/tekArch/XeroApplication/seleniumReport/");
			System.setProperty("webdriver.chrome.driver", "/Users/jbaisani/Downloads/chromedriver");
			driver = new ChromeDriver();
			Method tc = AutomationScript.class.getMethod(testCase);
			tc.invoke(tc);
			driver.quit();

			ReusableMethods.bw.close();
		}
	}
	}
}
	

}
