package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfferingsAdditionalDetailsPage { 
	
	final WebDriver driver;
	
	@FindBy(xpath = "//div[@class='float-right']//a")
	WebElement additionalDetails;
	
	@FindBy(xpath = "//div[@class='address-container']//h1")
	WebElement propNameHeader;
	
	/*
	 * Location
	 */
	@FindBy(xpath = "//div[@id='details.Location.Address.0']")
	WebElement address;
	
	@FindBy(xpath = "//div[@id='details.Location.City.1']")
	WebElement city;
	
	@FindBy(xpath = "//div[@id='details.Location.State.2']")
	WebElement state;
	
	@FindBy(xpath = "//div[@id='details.Location.Postal_Code.3']")
	WebElement postalCode;
	
	@FindBy(xpath = "//div[@id='details.Location.Country.4']")
	WebElement country;
	
	@FindBy(xpath = "//div[@id='details.Location.Lat_/_Long.5']")
	WebElement latLong;
	
	/*
	 * Property Details
	 */
	@FindBy(xpath = "//div[@id='details.Property_Details.Type.0']")
	WebElement propType;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Prop_Subtype.1']")
	WebElement propSubtype;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Area_(SqFt).2']")
	WebElement area;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Units.3']")
	WebElement units;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Land_Area.4']")
	WebElement landArea;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Status.5']")
	WebElement status;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Occupancy.6']")
	WebElement occupancy;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Year_Built.7']")
	WebElement yearBuilt;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Loan_Type.8']")
	WebElement loanType;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Interest_Type.9']")
	WebElement interestType;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Loan_Class.10']")
	WebElement loanClass;
	
	@FindBy(xpath = "//div[@id='details.Property_Details.Website.11']/a")
	WebElement website;

	@FindBy(xpath = "//div[@id='details.Property_Details.Website.11']/a")
	List<WebElement> websiteExist;
	
	
	/*
	 * Transaction Details
	 */
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Project_ID.0']")
	WebElement projectID;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Project_Type.1']")
	WebElement projectType;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Offered_Date.2']")
	WebElement offeredDate;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Value.3']")
	WebElement value;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Value_Type.4']")
	WebElement valueType;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Cap_Rate.5']")
	WebElement capRate;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Company.6']")
	WebElement company;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Name.7']")
	WebElement name;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Phone_Number.8']")
	WebElement phoneNumber;
	
	@FindBy(xpath = "//div[@id='details.Transaction_Details.Summary.9']")
	WebElement summary;
	
	public OfferingsAdditionalDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Property Name Header
	public String returnPropNameHeader()	{ return propNameHeader.getText(); }
	
	//Location	
	public String returnAdd() 				{ return address.getText(); }  
	public String returnCity() 				{ return city.getText(); }
	public String returnState() 			{ return state.getText(); }
	public String returnPostalCode() 		{ return postalCode.getText(); }
	public String returnCountry() { 
		//WebDriver wait = (WebDriver) new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(country));	
		FluentWait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(country));
		return country.getText(); 
		}
	public String returnLatLong() 			{ return latLong.getText(); }
	
	//Property Details
	public String returnPropType()			{ return propType.getText(); }
	public String returnPropSubtype()		{ return propSubtype.getText(); }
	public String returnArea()				{ return area.getText(); }
	public String returnUnits()				{ return units.getText(); }
	public String returnLandArea()			{ return landArea.getText(); }
	public String returnStatus()			{ return status.getText(); }
	public String returnOccupancy()			{ return occupancy.getText(); }
	public String returnYearBuilt()			{ return yearBuilt.getText(); }
	public String returnLoanType()			{ return loanType.getText(); }
	public String returnInterestType()		{ return interestType.getText(); }
	public String returnLoanClass()			{ return loanClass.getText(); }
	public String returnWebsite()			{ return website.getText(); }
	public boolean isWebsiteExist()			{ return websiteExist.size() != 0; }
	
	//Transaction Details
	public String returnProjectID() 		{ return projectID.getText(); }
	public String returnProjectType() 		{ return projectType.getText(); }
	public String returnOfferedDate() 		{ return offeredDate.getText(); }
	public String returnValue() 			{ return value.getText(); }
	public String returnValueType()			{ return valueType.getText(); }
	public String returnCapRate() 			{ return capRate.getText(); }
	public String returnCompany() 			{ return company.getText(); }
	public String returnName()				{ return name.getText(); }
	public String returnPhoneNumber()		{ return phoneNumber.getText(); }
	public String returnSummary() 			{ return summary.getText(); }
}
