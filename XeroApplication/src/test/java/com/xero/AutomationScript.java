package com.xero;
import static org.testng.Assert.expectThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
public class AutomationScript extends ReusableMethods {
	static Properties prop;
	//static WebDriver driver;
	public static void logDetails(){
		File file = new File("/Users/jbaisani/git/GitSetUp/XeroApplication/src/test/java/login.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public static void loginWebDriver() throws IOException{
	driver.get("https://www.xero.com/us/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	WebElement loginClick=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[2]/a"));
	click(loginClick,"Login to Xero Application");
}
public static void navigate_To_Xero_A() throws IOException, InterruptedException {	
	loginWebDriver();	
	logDetails();
	WebElement username=driver.findElement(By.id("email"));
	enterText(username, prop.getProperty("username"), "username");
	WebElement password=driver.findElement(By.id("password"));
	enterText(password, prop.getProperty("password"), "password");
	
	WebElement login=driver.findElement(By.id("submitButton"));
	click(login,"user successfully login is");
	Thread.sleep(2000);
}
public static void navigate_To_Xero_B() throws IOException{
	loginWebDriver();
	logDetails();
	WebElement username=driver.findElement(By.xpath(".//*[@id='email']"));
	enterText(username, prop.getProperty("username"), "username");
	WebElement password=driver.findElement(By.xpath(".//*[@id='password']"));
	enterText(password, prop.getProperty("errorPassword"), "password");
	WebElement login=driver.findElement(By.xpath(".//*[@id='submitButton']"));
	click(login,"Your Email  or password is Incorrect");
}
public static void navigate_To_Xero_C() throws IOException{
	loginWebDriver();
	logDetails();
	WebElement username=driver.findElement(By.xpath(".//*[@id='email']"));
	enterText(username, prop.getProperty("errorUsername"), "username");
	WebElement password=driver.findElement(By.xpath(".//*[@id='password']"));
	enterText(password, prop.getProperty("password"), "password");
	WebElement login=driver.findElement(By.xpath(".//*[@id='submitButton']"));
	click(login,"Your Email  or password is Incorrect");
}
public static void navigate_To_Xero_D() throws IOException{
	loginWebDriver();
	logDetails();
	WebElement forgotpassword=driver.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/a"));
	click(forgotpassword,"clicked forgot password");
	WebElement username=driver.findElement(By.xpath(".//*[@id='UserName']"));
	enterText(username, prop.getProperty("username"), "username");
	WebElement sendlink=driver.findElement(By.xpath(".//*[@id='submitButton']/a/span"));
	click(sendlink, "send link got clicked and user will receive an email");
	WebElement msg=driver.findElement(By.xpath(".//*[@id='contentTop']/div/p[1]"));
	Assert.assertEquals(msg.getText(), "A link to reset your password has been sent to:"+"\n"+prop.getProperty("username"));
}
public static void signUp_To_XSDC_A() throws IOException, InterruptedException{
	driver.get("https://www.xero.com/us/");
	WebElement freetrail=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
	click(freetrail, "freeTrail got clicked");
	WebElement firstName=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[2]/label/input"));
	enterText(firstName, "userA", "First Name");
	WebElement lastName=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[3]/label/input"));
	enterText(lastName, "lastB", "Last Name");
	WebElement email=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[4]/label/input"));
	enterText(email, "abc@gmail.com", "email Address");
	WebElement phone=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[5]/label/input"));
	enterText(phone, "23243545", "Phone Number");
	WebElement country=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[6]/label/span/select"));
	selectValue(country, "United States", "coutry got seleted");
	Thread.sleep(30000);
	WebElement termsAndConditions=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/input"));
	click(termsAndConditions, "Terms and Conditions got checked");
	WebElement getStarted=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[9]/button"));
	click(getStarted, "Get Started got clicked");
	}

public static void signUp_To_XSDC_B() throws IOException, InterruptedException{
	driver.get("https://www.xero.com/us/");
	WebElement freetrail=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
	click(freetrail, "freeTrail got clicked and it displayed 30days free trail page");
	Thread.sleep(3000);
	WebElement getStarted=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[9]/button"));
	click(getStarted, "Get Started got clicked");
	Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='signup-form-error-messege-1']")).getText(), "First name can't be empty");
	Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='signup-form-error-messege-2']")).getText(), "Last name can't be empty");
	Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='signup-form-error-messege-3']")).getText(), "Email address can't be empty");
	Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='signup-form-error-messege-4']")).getText(), "Phone number can't be empty");
	WebElement email=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[4]/label/input"));
	enterText(email, "abc@gmail.com", "email Address");
	checkfieldValue(email, "abd","@");
	Thread.sleep(30000);
	click(getStarted, "Get Started got clicked");
	WebElement termsAndConditions=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/input"));
	//need to check hight light pending
}
public static void signUp_To_XSDC_C() throws IOException{
	driver.get("https://www.xero.com/us/");
	WebElement freetrail=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
	click(freetrail, "freeTrail got clicked");
	WebElement termsOfUse=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[1]"));
	click(termsOfUse, "Terms Of Use link got clicked");
	WebElement privacy=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[2]"));
	click(privacy, "Privacy got clicked");
	WebElement offerDetails=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[3]"));
}
public static void signUp_To_XSDC_D() throws IOException{
	driver.get("https://www.xero.com/us/");
	WebElement freetrail=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
	click(freetrail, "freeTrail got clicked");
	WebElement offerDetails=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[3]"));
    click(offerDetails, "offer Details got clicked");
}
public static void signUp_To_XSDC_E() throws IOException{
	driver.get("https://www.xero.com/us/");
	WebElement freetrail=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
	click(freetrail, "freeTrail got clicked");
	WebElement accountantOrBookkeeper=driver.findElement(By.xpath("html/body/div[6]/main/div[2]/div/div/div/p/a"));
	click(accountantOrBookkeeper, "accountant or Bookkeeper got clicked");
}
public static void test_All_Tab_Page() throws IOException, InterruptedException{
	navigate_To_Xero_A();
	Thread.sleep(3000);
	WebElement dashBoard=driver.findElement(By.xpath(".//*[@id='Dashboard']"));
	click(dashBoard, "Dash Board got clicked");
	WebElement accounts=driver.findElement(By.xpath(".//*[@id='Accounts']"));
	click(accounts, "Accounts got clicked");
	WebElement reports=driver.findElement(By.xpath(".//*[@id='Reports']"));
	click(reports, "Reports got clicked");
	WebElement contacts=driver.findElement(By.xpath(".//*[@id='Contacts']"));
	click(contacts,"Contacts got Clicked");
	WebElement settings=driver.findElement(By.xpath(".//*[@id='Settings']"));
	click(settings, "Settings got Clicked");
	WebElement newOrPlus=driver.findElement(By.xpath(".//*[@id='quicklaunchTab']"));
	click(newOrPlus, "New Tab or + got Clicked");
	WebElement files=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[2]/a"));
	click(files, "Files icon got clicked");
	WebElement notification=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[3]/a"));
	click(notification, "Notification Icon got clicked");
	WebElement search=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[4]/a"));
	click(search, "Search Icon got clicked");
	WebElement help=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[5]/a"));
	click(help, "Help Icon got clicked");
	WebElement menuHelp=driver.findElement(By.xpath(".//*[@id='menu_help']"));
	checkfield(menuHelp, "menu Help displayed");
	WebElement helpCenter=driver.findElement(By.xpath(".//*[@id='rt']/ul/li[1]/a"));
	checkfield(helpCenter, "Help Center got displayed");
	WebElement getHelp=driver.findElement(By.xpath(".//*[@id='get_help']"));
	checkfield(getHelp, "Get Help for this page is Displayed");
	WebElement hide=driver.findElement(By.xpath(".//*[@id='rt']/ul/li[3]/a"));
	checkfield(hide, "Hide Getting Started is displayed");
}
public static void test_Logout() throws IOException, InterruptedException{
	navigate_To_Xero_A();
	WebElement usermenu=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[2]/a"));
	click(usermenu, "Usermenu arrow got clicked");
	WebElement logout=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[2]/div/ul/li[3]/a"));
	click(logout, "User logged out");
	WebElement username=driver.findElement(By.xpath(".//*[@id='email']"));
	checkfieldValue(username," username field  ","^[a-zA-Z]@^[a-z]");
	
}
public static void UploadProfilePage() throws IOException, InterruptedException{
	navigate_To_Xero_A();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	WebElement usermenu=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[2]/a"));
	click(usermenu, "Usermenu arrow got clicked");
	WebElement profile=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[2]/div/ul/li[1]/a"));
	click(profile,"Profile got clicked");
	WebElement upload=driver.findElement(By.xpath(".//*[@id='button-1041-btnInnerEl']"));//button-1041-btnEl
	click(upload,"Upload button got clicked");
	Thread.sleep(2000);
	WebElement browser=driver.findElement(By.id("filefield-1174-button"));
	//click(browse,"Browse button got clicked to upload image");
	
	enterText(browser, "/Users/jbaisani/Downloads/navodaya.jpg", "Browser");
	WebElement uploadImage=driver.findElement(By.xpath(".//*[@id='button-1178-btnInnerEl']"));
	click(uploadImage, "upload button got clicked");
}
public static void trail_Plan() throws IOException, InterruptedException{
	navigate_To_Xero_A();
	WebElement organisation=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/h2/a"));
	click(organisation, "Organisation got clicked");
	WebElement myXero=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/div/div/a"));
	click(myXero, "My Xero got clicked");
	WebElement addOrganisation=driver.findElement(By.xpath(".//*[@id='ext-gen1042']"));
	click(addOrganisation,"Add Organisation got selected");
	WebElement nameOrganisation=driver.findElement(By.xpath(".//*[@id='text-1022-inputEl']"));
	enterText(nameOrganisation, "self", "Enter name of Organization");
	WebElement payTaxes=driver.findElement(By.xpath(".//*[@id='countryCmb-inputEl']"));
	payTaxes.clear();
	enterText(payTaxes, "US", "Organisation pay taxes");
	WebElement timeZone=driver.findElement(By.xpath(".//*[@id='ext-gen1100']"));
	WebElement pacificTime=timeZone.findElement(By.xpath(".//*[@id='cmbTimeZone-boundlist-listEl']/ul/li[128]"));
	click(pacificTime, "Pacific time got selected");
	WebElement business=driver.findElement(By.xpath(".//*[@id='industrysearchcombofield-1025-inputEl']"));
	enterText(business, "Accounting", "What does Organisation Do?");
	WebElement startTrail=driver.findElement(By.xpath(".//*[@id='simplebutton-1035']"));
	click(startTrail, "Start Trail got clicked");
	
}
public static void paid_Version() throws IOException, InterruptedException{
	navigate_To_Xero_A();
	WebElement organisation=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/h2/a"));
	click(organisation, "Organisation got clicked");
	WebElement myXero=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/div/div/a"));
	click(myXero, "My Xero got clicked");
	WebElement addOrganisation=driver.findElement(By.xpath(".//*[@id='ext-gen1042']"));
	click(addOrganisation,"Add Organisation got selected");
	WebElement nameOrganisation=driver.findElement(By.xpath(".//*[@id='text-1022-inputEl']"));
	enterText(nameOrganisation, "self", "Enter name of Organization");
	WebElement payTaxes=driver.findElement(By.xpath(".//*[@id='countryCmb-inputEl']"));
	payTaxes.clear();
	enterText(payTaxes, "US", "Organisation pay taxes");
	WebElement timeZone=driver.findElement(By.xpath(".//*[@id='ext-gen1100']"));
	WebElement pacificTime=timeZone.findElement(By.xpath(".//*[@id='cmbTimeZone-boundlist-listEl']/ul/li[128]"));
	click(pacificTime, "Pacific time got selected");
	WebElement business=driver.findElement(By.xpath(".//*[@id='industrysearchcombofield-1025-inputEl']"));
	enterText(business, "Accounting", "What does Organisation Do?");
	WebElement buyNow=driver.findElement(By.xpath(".//*[@id='simplebutton-1036']"));
	click(buyNow,"Buy Now got clicked");
}
public static void starterPlan() throws IOException, InterruptedException{
	paid_Version();
	WebElement starter=driver.findElement(By.xpath(".//*[@id='PRODUCTOPTION/ORG/SOLO']/div[1]/label"));
	click(starter, "Starter got clicked");
	WebElement continueToBill=driver.findElement(By.xpath(".//*[@id='frmMain']/div/div[2]/div/main/div[10]/button"));
	click(continueToBill, "Continue To Billing details got clicked");
	WebElement address=driver.findElement(By.xpath(".//*[@id='POAddress']"));
	enterText(address, "3450 granda ave", "Street Address");
	WebElement city=driver.findElement(By.xpath(".//*[@id='POCity']"));
	enterText(city, "SantaClara", "Town or City got");
	WebElement state=driver.findElement(By.xpath(".//*[@id='PORegionDropdown']"));
	enterText(state, "CA", "State");
	WebElement zipCode=driver.findElement(By.xpath(".//*[@id='POPostalCode']"));
	enterText(zipCode, "95051", "Zip Code");
	WebElement continueToreview=driver.findElement(By.xpath(".//*[@id='frmMain']/div/div/div/main/div[4]/div/div[2]/div/button"));
	click(continueToreview, "Continue to Review And Pay clicked");
	WebElement cardNumber=driver.findElement(By.xpath(".//*[@id='card-element']"));
	enterText(cardNumber, "4539 2519 9996 7590","Card Number");
	WebElement expiryDate=driver.findElement(By.xpath(".//*[@id='card-expiry']"));
	enterText(expiryDate, "11/18", "Expiry Date");
	WebElement securityCode=driver.findElement(By.xpath(".//*[@id='card-cvc']"));
	enterText(securityCode, "159", "Security Code");
	WebElement name=driver.findElement(By.xpath(".//*[@id='cardholder-name']"));
	enterText(name, "ALEX HARDMAN", "Name ofn Card");
	WebElement authorize=driver.findElement(By.xpath(".//*[@id='authoriseStripeButton']"));
	click(authorize, "Authorize Payment is clicked");

}

public static void standardPlan() throws IOException, InterruptedException{
	paid_Version();
	//WebElement starter=driver.findElement(By.xpath(".//*[@id='PRODUCTOPTION/ORG/SOLO']/div[1]/label"));
	//click(starter, "Starter got clicked");
	WebElement continueToBill=driver.findElement(By.xpath(".//*[@id='frmMain']/div/div[2]/div/main/div[10]/button"));
	click(continueToBill, "Continue To Billing details got clicked");
	WebElement address=driver.findElement(By.xpath(".//*[@id='POAddress']"));
	enterText(address, "3450 granda ave", "Street Address");
	WebElement city=driver.findElement(By.xpath(".//*[@id='POCity']"));
	enterText(city, "SantaClara", "Town or City got");
	WebElement state=driver.findElement(By.xpath(".//*[@id='PORegionDropdown']"));
	enterText(state, "CA", "State");
	WebElement zipCode=driver.findElement(By.xpath(".//*[@id='POPostalCode']"));
	enterText(zipCode, "95051", "Zip Code");
	WebElement continueToreview=driver.findElement(By.xpath(".//*[@id='frmMain']/div/div/div/main/div[4]/div/div[2]/div/button"));
	click(continueToreview, "Continue to Review And Pay clicked");	
	WebElement cardNumber=driver.findElement(By.xpath(".//*[@id='card-element']"));
	enterText(cardNumber, "4539 2519 9996 7590","Card Number");
	WebElement expiryDate=driver.findElement(By.xpath(".//*[@id='card-expiry']"));
	enterText(expiryDate, "11/18", "Expiry Date");
	WebElement securityCode=driver.findElement(By.xpath(".//*[@id='card-cvc']"));
	enterText(securityCode, "159", "Security Code");
	WebElement name=driver.findElement(By.xpath(".//*[@id='cardholder-name']"));
	enterText(name, "ALEX HARDMAN", "Name ofn Card");
	WebElement authorize=driver.findElement(By.xpath(".//*[@id='authoriseStripeButton']"));
	click(authorize, "Authorize Payment is clicked");

}
public static void premiumPlan() throws IOException, InterruptedException{
	paid_Version();
	WebElement starter=driver.findElement(By.xpath(".//*[@id='PRODUCTOPTION/ORG/PRO']/div[1]/label/span"));
	click(starter, "Starter got clicked");
	WebElement continueToBill=driver.findElement(By.xpath(".//*[@id='frmMain']/div/div[2]/div/main/div[10]/button"));
	click(continueToBill, "Continue To Billing details got clicked");
	WebElement address=driver.findElement(By.xpath(".//*[@id='POAddress']"));
	enterText(address, "3450 granda ave", "Street Address");
	WebElement city=driver.findElement(By.xpath(".//*[@id='POCity']"));
	enterText(city, "SantaClara", "Town or City got");
	WebElement state=driver.findElement(By.xpath(".//*[@id='PORegionDropdown']"));
	enterText(state, "CA", "State");
	WebElement zipCode=driver.findElement(By.xpath(".//*[@id='POPostalCode']"));
	enterText(zipCode, "95051", "Zip Code");
	WebElement continueToreview=driver.findElement(By.xpath(".//*[@id='frmMain']/div/div/div/main/div[4]/div/div[2]/div/button"));
	click(continueToreview, "Continue to Review And Pay clicked");
	WebElement cardNumber=driver.findElement(By.xpath(".//*[@id='card-element']"));
	enterText(cardNumber, "4539 2519 9996 7590","Card Number");
	WebElement expiryDate=driver.findElement(By.xpath(".//*[@id='card-expiry']"));
	enterText(expiryDate, "11/18", "Expiry Date");
	WebElement securityCode=driver.findElement(By.xpath(".//*[@id='card-cvc']"));
	enterText(securityCode, "159", "Security Code");
	WebElement name=driver.findElement(By.xpath(".//*[@id='cardholder-name']"));
	enterText(name, "ALEX HARDMAN", "Name ofn Card");
	WebElement authorize=driver.findElement(By.xpath(".//*[@id='authoriseStripeButton']"));
	click(authorize, "Authorize Payment is clicked");

}
public static void purchase() throws IOException, InterruptedException{
	navigate_To_Xero_A();
	WebElement accounts=driver.findElement(By.xpath(".//*[@id='Accounts']"));
	click(accounts, "Accounts got clicked");
	Actions action = new Actions(driver);
	 
    action.moveToElement(accounts).build().perform();
	WebElement purchase=driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[1]/ul/li[2]/ul/li[3]/a"));
	click(purchase, "Purchase got clicked");
	
}

}
