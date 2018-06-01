package test.java;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.sql.*;

import Apps.*;
import PageObjects.*;
import Utility.*;
import Validation.DodgeValidation;




public class RepoSearchTest { 

	public static String env = ""; //-uat or -test. "" for prod
	public static String uiEnvironment = Constant.Repo_Search_URL_prod; //_UAT / _test / prod
	public WebDriver driver;
	public static String token = "";
	
	public static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-YY");
	public static final Date date = new Date();
	public static final String currentDateEnv = "-" + dateFormat.format(date) + env;  
	public static final boolean runAll = true;
	
	public static int testCase = 0;
	public static int dodgeSearchCaseID = 0;
	public static int offeringsSearchCaseID = 0;
	public static int titleSearchCaseID = 0;
	public static int FIPSCountCaseID = 0;
	public static int TransCountCaseID = 0;
	
	@BeforeClass
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", Constant.chromeDriver_Path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(uiEnvironment); 
		Login_Action.execute(driver);
		token = Elastic_Action.getToken(env);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//*[@id='body']/ng-sidebar-container/div/div/dp-search-additional-details/div/div[1]")).click();

		Thread.sleep(5000);
	}
	
	//still used
	@Test (enabled = false, dataProvider = "MasterTaxRollTransactions")
	public void CLTransactionsCountTest(String MasterTaxRollId, String TransCount) throws Exception {
		TransCountCaseID++;
		if (TransCountCaseID == 1) {
			CSV.createFileExcelTransCounts("CLTransCount" + currentDateEnv);
			System.out.println("Started CL Transactions Count Test");
		}
		List<TransCountResults> results = new ArrayList<TransCountResults>(); 
		String esTransCount = String.valueOf(Elastic_Action.getTransSCountByMasterTaxrollID(MasterTaxRollId, env, token));
		results.add(new TransCountResults(MasterTaxRollId, TransCount, esTransCount));
		CSV.appendToFileExcelTransCounts("CLTransCount" + currentDateEnv, results);
	}
	
	//deprecated
	@Test (enabled = false, dataProvider = "FIPScounts")
	public void FIPSCountTest(String FIPScode, String exFIPSCount) throws Exception {
		FIPSCountCaseID++;
		if (FIPSCountCaseID == 1) {
			CSV.createFileExcelFIPSCounts("FIPScount" + currentDateEnv);
			System.out.println("Started FIPS Count Test");
		}
		List<FIPSCountResults> results = new ArrayList<FIPSCountResults>(); 
		String esFIPSCount = String.valueOf(Elastic_Action.getFIPSCount(FIPScode));
		results.add(new FIPSCountResults(FIPScode, exFIPSCount, esFIPSCount));
		CSV.appendToFileExcelFIPSCounts("FIPSCount" + currentDateEnv, results);
	}
	
	
	
		/*
		 * TODO - add sql validation
		 */
		/*
		String connectionUrl = "jdbc:sqlserver://DM-SQL-Test;" +  
		         "databaseName=dbConstruction;user=webadmin;password=rc@1105$";
		Connection con = null;  
	    Statement stmt = null;  
	    ResultSet rs = null;  

		try {  
	         // Establish the connection.  
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	         con = DriverManager.getConnection(connectionUrl);
	         String SQL = "SELECT TOP 10 * FROM Dodge.MAIN";  
	         stmt = con.createStatement();  
	         rs = stmt.executeQuery(SQL);  
	         System.out.println(rs.toString());
	         // Iterate through the data in the result set and display it.  
	         while (rs.next()) {  
	        	 System.out.println(rs.getString(4) + " " + rs.getString(6));  
	         }
		}
		catch (Exception e) {  
	         e.printStackTrace();  
	    }
		finally {  
	         if (rs != null) try { rs.close(); } catch(Exception e) {}  
	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	         if (con != null) try { con.close(); } catch(Exception e) {}  
	    } */
	
	
	@DataProvider(name = "FIPScounts")
	public Object[][] getFIPSCounts() {
		ExcelDataConfig config = new ExcelDataConfig(Constant.Title_FIPSCounts_Path);
		int rows = config.getRowCount(0);
		System.out.println("num rows: " + rows);
		Object[][] data = new Object[rows][2]; // rows is number of rows, 1 is number of arguments
		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData(0, i, 0);
			data[i][1] = config.getData2(0, i, 1);
		}
		return data;
	}
	
	@DataProvider(name = "MasterTaxRollTransactions")
	public Object[][] getTransCounts() {
		ExcelDataConfig config = new ExcelDataConfig(Constant.Title_CLTransCounts_Path);
		int rows = config.getRowCount(0);
		System.out.println("num rows: " + rows);
		Object[][] data = new Object[rows][2]; // rows is number of rows, 1 is number of arguments
		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData2(0, i, 0);
			data[i][1] = config.getData2(0, i, 1);
		}
		return data;
	}
	
	@DataProvider(name = "RCMProjectID")
	public Object[][] getProjIdData() {
		ExcelDataConfig config = new ExcelDataConfig(Constant.Offerings_ProjectID_Path);
		int rows = config.getRowCount(0);
		Object[][] data = new Object[rows][1]; // rows is number of rows, 1 is number of arguments
		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData(0, i, 0);
		}
		return data;
	}
	
	
	@DataProvider(name = "DodgeRepnum")
	public Object[][] getRepNumData() {
		ExcelDataConfig config = new ExcelDataConfig(Constant.DodgeSearch_RepNum_Path);
		int rows = config.getRowCount(0);
		Object[][] data = new Object[rows][1]; // rows is number of rows, 1 is number of arguments
		for (int i = 0; i < rows; i++) {
			data[i][0] = config.getData(0, i, 0);
		}
		return data;
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
		System.out.println("Test Complete!");	
	}
}
