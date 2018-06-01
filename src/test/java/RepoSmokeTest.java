package test.java;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.*;

import Apps.*;
import PageObjects.*;
import Utility.*;
import Validation.CoreLogicValidation;
import Validation.DodgeValidation;
import Validation.OfferingsValidation;



public class RepoSmokeTest { 

	//public static String env = "-test"; //-uat or -test. "" for prod
	public static String env = Constant.ESenv; //-uat or -test. "" for prod
	public static String uiEnvironment = Constant.Repo_Search_URL; //_UAT / _test / prod
	public static RemoteWebDriver driver;
	public static String nodeUrl = "http://192.168.133.84:4444/wd/hub";
	public static String host = Constant.host; //"local" or "VM" 
	//public WebDriver driver;
	public static String token = "";
	//public static File screenshotFolder = new File("C:\\Users\\BChen\\workspace\\dodge-search\\Screenshots");
	
	public static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-YY");
	public static final Date date = new Date();
	public static final String currentDateEnv = "-" + dateFormat.format(date) + env;  
	public static final boolean runAll = true;
	
	public static int testCase = 0;
	public static int dodgeSearchCaseID = 0;
	public static int RCMSearchCaseID = 0;
	public static int titleSearchCaseID = 0;
	
	@BeforeClass
	public void setUp() throws MalformedURLException, InterruptedException {
		
		if (host == "VM") {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WIN10);
			driver = new RemoteWebDriver(new URL(nodeUrl), cap);
		}  
		if (host == "local") { 
			System.setProperty("webdriver.chrome.driver", Constant.chromeDriver_Path);
			driver = new ChromeDriver();
		}
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1920,1080));
		driver.get(uiEnvironment); 
		Login_Action.execute(driver);
		token = Elastic_Action.getToken(env);
		HomePage_Action.goToRepoSearch(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		//executor.executeScript("document.body.style.zoom = '.9'");
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	}
	
	@Test (enabled = runAll, dataProvider = "DodgeInput")
	public void validateDodgeResults(String repnum, String propertyName, String address, String city, String state, String postalCode) throws Exception{
		//Instantiate Dodge Search page
		DodgeSearchPage dodgeSearchPage = new DodgeSearchPage(driver);
		dodgeSearchPage.selectConstructionDropdown();
		System.out.println("*******************************************************");
		System.out.println("Construction selected - validating results");
		System.out.println("*******************************************************");
		//Input and verify address
		dodgeSearchPage.inputTitle(propertyName);
		dodgeSearchPage.inputAddress1(address);
		dodgeSearchPage.selectState(state);
		dodgeSearchPage.inputCity(city);
		dodgeSearchPage.inputPostalCode(postalCode);
		dodgeSearchPage.inputRepnum(repnum);
		dodgeSearchPage.clickSearchBtn();
		try {
			Assert.assertTrue(dodgeSearchPage.verifyTooltip(propertyName));
			Assert.assertTrue(dodgeSearchPage.verifyTooltip(address));
			Assert.assertTrue(dodgeSearchPage.verifyTooltip(state));
			Assert.assertTrue(dodgeSearchPage.verifyTooltip(city));
			Assert.assertTrue(dodgeSearchPage.verifyTooltip(postalCode));
			System.out.println("Tooltips passed");
			Assert.assertTrue(dodgeSearchPage.verifyResultText(propertyName));
			Assert.assertTrue(dodgeSearchPage.verifyResultText(address));
			Assert.assertTrue(dodgeSearchPage.verifyResultText(city));
			Assert.assertTrue(dodgeSearchPage.verifyResultText(state));
			Assert.assertTrue(dodgeSearchPage.verifyResultText(postalCode));
			System.out.println("Results verified");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		dodgeSearchCaseID++;
		testCase++;
		if (dodgeSearchCaseID == 1){
			CSV.createFileExcel("dodgeSearch" + currentDateEnv);
		}
		System.out.println("*******************************************************");
		System.out.println("Started Dodge Search Test Case: " + dodgeSearchCaseID);
		System.out.println("*******************************************************");

		//Pass repnum into search and get ES results
		JSONObject searchResultsData = Elastic_Action.getSearchResultsByRepnum(repnum, env, token);

		//Instantiate additional details page object
		DodgeAdditionalDetailsPage additionalDetails = new DodgeAdditionalDetailsPage(driver);

		dodgeSearchPage.getFirstRow().click();
		
		//Function that grabs the property details from ES and UI, outputs and compares them in CSV  
		DodgeValidation.getDodgePropDetailResults(searchResultsData, additionalDetails, dodgeSearchCaseID);
		dodgeSearchPage.clearAll();
		System.out.println("*******************************************************");
		System.out.println("Test Case Complete");
		System.out.println("*******************************************************");
	}
	
	@Test (enabled = runAll, dataProvider = "RCMInput")
	public void validateRCMResults(String projectID, String propertyName, String address, String city, String state, String postalCode) throws Exception{
		//Instantiate Offerings Search page
		OfferingsSearchPage offeringsSearchPage = new OfferingsSearchPage(driver);
		offeringsSearchPage.selectOfferingsDropdown();
		System.out.println("*******************************************************");
		System.out.println("Offerings selected - validating results");
		System.out.println("*******************************************************");
		//Input and verify address
		offeringsSearchPage.inputAddress(address);
		offeringsSearchPage.selectState(state);
		offeringsSearchPage.inputCity(city);
		offeringsSearchPage.inputPostalCode(postalCode);
		offeringsSearchPage.inputProjectId(projectID);
		offeringsSearchPage.clickSearchBtn();
		try {
			Assert.assertTrue(offeringsSearchPage.verifyTooltip(address));
			Assert.assertTrue(offeringsSearchPage.verifyTooltip(state));
			Assert.assertTrue(offeringsSearchPage.verifyTooltip(city));
			Assert.assertTrue(offeringsSearchPage.verifyTooltip(postalCode));
			System.out.println("Tooltips passed");
			Assert.assertTrue(offeringsSearchPage.verifyResultText(propertyName));
			Assert.assertTrue(offeringsSearchPage.verifyResultText(address));
			Assert.assertTrue(offeringsSearchPage.verifyResultText(city));
			Assert.assertTrue(offeringsSearchPage.verifyResultText(state));
			Assert.assertTrue(offeringsSearchPage.verifyResultText(postalCode));
			System.out.println("Results verified");
		} catch (Exception e){
			e.printStackTrace();
		}
			
		RCMSearchCaseID++;
		testCase++;
		if (RCMSearchCaseID == 1) {
			CSV.createFileExcel("offeringsSearch" + currentDateEnv);
		}
		System.out.println("*******************************************************");
		System.out.println("Started Offerings Search Test Case");
		System.out.println("*******************************************************");
		//Pass project ID into search and get ES results
		JSONObject searchResultsData = Elastic_Action.getSearchResultsByProjID(projectID, env, token);
		
		offeringsSearchPage.getFirstRow().click();
		
		//Instantiate additional details page object
		OfferingsAdditionalDetailsPage additionalDetails = new OfferingsAdditionalDetailsPage(driver);
		
		//Function that grabs the property details from ES and UI, outputs and compares them in CSV  
		OfferingsValidation.getOfferingsPropDetailResults(searchResultsData, additionalDetails, RCMSearchCaseID);
		
		offeringsSearchPage.clearAll();
		System.out.println("*******************************************************");
		System.out.println("Test Case Complete");
		System.out.println("*******************************************************");
	}
	
	@Test (enabled = runAll, dataProvider = "TitleInput")
	public void validateTitleResults(String masterTaxRollID, String address, String city, String state, String postalCode) throws Exception{
		CoreLogicSearchPage coreLogicSearchPage = new CoreLogicSearchPage(driver);
		coreLogicSearchPage.selectCoreLogicDropdown();
		System.out.println("*******************************************************");
		System.out.println("CoreLogic selected - validating results");
		System.out.println("*******************************************************");
		//Input and verify address
		coreLogicSearchPage.inputAddress(address);
		coreLogicSearchPage.selectState(state);
		coreLogicSearchPage.inputCity(city);
		Thread.sleep(4000);
		coreLogicSearchPage.inputPostalCode(postalCode);
		coreLogicSearchPage.inputMasterTaxId(masterTaxRollID);
		coreLogicSearchPage.clickSearchBtn();
		try {
			Assert.assertTrue(coreLogicSearchPage.verifyTooltip(address));
			Assert.assertTrue(coreLogicSearchPage.verifyTooltip(state));
			Assert.assertTrue(coreLogicSearchPage.verifyTooltip(city));
			Assert.assertTrue(coreLogicSearchPage.verifyTooltip(postalCode));
			System.out.println("Tooltips passed");
			Assert.assertTrue(coreLogicSearchPage.verifyResultText(address));
			Assert.assertTrue(coreLogicSearchPage.verifyResultText(city));
			Assert.assertTrue(coreLogicSearchPage.verifyResultText(state));
			Assert.assertTrue(coreLogicSearchPage.verifyResultText(postalCode));
			System.out.println("Results verified");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		titleSearchCaseID++;
		testCase++;
		if (titleSearchCaseID == 1) {
			CSV.createFileExcel("titleSearch" + currentDateEnv);
		}
		System.out.println("*******************************************************");
		System.out.println("Started Title Search Test Case");
		
		//Pass project ID into search and get ES results
		JSONObject searchResultsData = Elastic_Action.getSearchResultsByMasterTaxID(masterTaxRollID, env, token);
		coreLogicSearchPage.getFirstRow().click();
		
		//Instantiate additional details page object
		CoreLogicAdditionalDetailsPage additionalDetails = new CoreLogicAdditionalDetailsPage(driver);
		
		//Function that grabs the property details from ES and UI, outputs and compares them in CSV  
		CoreLogicValidation.getCoreLogicPropDetailResults(searchResultsData, additionalDetails, titleSearchCaseID);
		
		coreLogicSearchPage.clearAll();
		System.out.println("*******************************************************");
		System.out.println("Test Case Complete");
		System.out.println("*******************************************************");
	}
	
	@DataProvider(name = "DodgeInput")
	public Object[][] getDodgeData() {
		ExcelDataConfig config = new ExcelDataConfig(Constant.DL_DataInput_Path);
		int rows = config.getRowCount(0);
		System.out.println("num rows: " + rows);
		Object[][] data = new Object[rows][6]; // rows is number of rows, 1 is number of arguments
		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData2(0, i, 0);
			data[i][1] = config.getData2(0, i, 1);
			data[i][2] = config.getData2(0, i, 2);
			data[i][3] = config.getData2(0, i, 3);
			data[i][4] = config.getData2(0, i, 4);
			data[i][5] = config.getData2(0, i, 5);
		}
		return data;
	}
	
	@DataProvider(name = "RCMInput")
	public Object[][] getRCMData() {
		ExcelDataConfig config = new ExcelDataConfig(Constant.DL_DataInput_Path);
		int rows = config.getRowCount(0);
		System.out.println("num rows: " + rows);
		Object[][] data = new Object[rows][6]; // rows is number of rows, 1 is number of arguments
		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData2(1, i, 0);
			data[i][1] = config.getData2(1, i, 1);
			data[i][2] = config.getData2(1, i, 2);
			data[i][3] = config.getData2(1, i, 3);
			data[i][4] = config.getData2(1, i, 4);
			data[i][5] = config.getData2(1, i, 5);
		}
		return data;
	}
	
	@DataProvider(name = "TitleInput")
	public Object[][] getTitleData() {
		ExcelDataConfig config = new ExcelDataConfig(Constant.DL_DataInput_Path);
		int rows = config.getRowCount(0);
		System.out.println("num rows: " + rows);
		Object[][] data = new Object[rows][5]; // rows is number of rows, 1 is number of arguments
		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData2(2, i, 0);
			data[i][1] = config.getData2(2, i, 1);
			data[i][2] = config.getData2(2, i, 2);
			data[i][3] = config.getData2(2, i, 3);
			data[i][4] = config.getData2(2, i, 4);
		}
		return data;
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		if (driver!=null){
			System.out.println("Closing browser");
			driver.quit();
		}
		System.out.println("Test Complete!");	
	}
}
