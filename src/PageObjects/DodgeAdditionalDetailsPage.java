package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DodgeAdditionalDetailsPage { 
	
	final WebDriver driver;
	
	@FindBy(xpath = "//div[@class='float-right']//a")
	WebElement additionalDetails;
	
	@FindBy(xpath = "//div[@class='address-container']//h1")
	WebElement propNameHeader;
	
	/*
	 * Location
	 */
	@FindBy(xpath = "//div[@id='details.Location.Address_1.0']")
	WebElement address1;
	
	@FindBy(xpath = "//div[@id='details.Location.Address_2.1']")
	List<WebElement> add2Exist;
	
	@FindBy(xpath = "//div[@id='details.Location.Address_2.1']")
	WebElement address2;
	
	@FindBy(xpath = "//div[@id='details.Location.City.2']")
	WebElement city;
	
	@FindBy(xpath = "//div[@id='details.Location.State.3']")
	WebElement state;
	
	@FindBy(xpath = "//div[@id='details.Location.Postal_Code.4']")
	WebElement postalCode;
	
	@FindBy(xpath = "//div[@id='details.Location.County.5']")
	WebElement county;
	
	@FindBy(xpath = "//div[@id='details.Location.Lat_/_Long.6']")
	WebElement latLong;
	
	@FindBy(xpath = "//div[@id='details.Location.FIPS_Name.7']")
	WebElement FIPSName;
	
	@FindBy(xpath = "//div[@id='details.Location.FIPS_Code.8']")
	WebElement FIPSCode;
	
	@FindBy(xpath = "//div[@id='details.Location.MSA_Name.9']")
	WebElement MSAName;
	
	@FindBy(xpath = "//div[@id='details.Location.MSA_Code.10']")
	WebElement MSACode;
	
	@FindBy(xpath = "//div[@id='details.Location.NCREIF.11']")
	WebElement NCREIF;
	
	/*
	 * Property Characteristics
	 */
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Prop_Type.0']")
	WebElement propType;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Prop_Subtype.1']")
	WebElement propSubtype;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Hard_Cost.2']")
	WebElement hardCost;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Area_(SqFt).3']")
	WebElement area;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Units.4']")
	WebElement units;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Buildings.5']")
	WebElement buildings;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Floors.6']")
	WebElement floors;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Parking/Measure.7']")
	WebElement parkingMeasure;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Hotel_Brand(s).8']")
	WebElement hotelBrands;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Tenant(s).9']")
	WebElement tenants;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Leed_Registered.10-boolean']/img")
	List<WebElement> leedRegistered;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Rating.11']")
	WebElement leedRegRating;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Leed_Intended.12-boolean']/img")
	List<WebElement> leedIntended;
	
	@FindBy(xpath = "//div[@id='details.Property_Characteristics.Rating.13']")
	WebElement leedIntendedRating;
	
	/*
	 * Company
	 */
	@FindBy(xpath = "//div[@id='details.Company.Name.0']")
	WebElement owner1Name;
	
	@FindBy(xpath = "//div[@id='details.Company.Address.1']")
	WebElement owner1Add;
	
	@FindBy(xpath = "//div[@id='details.Company.City.2']")
	WebElement owner1City;
	
	@FindBy(xpath = "//div[@id='details.Company.State.3']")
	WebElement owner1State;
	
	@FindBy(xpath = "//div[@id='details.Company.Postal_Code.4']")
	WebElement owner1PostalCode;
	
	@FindBy(xpath = "//div[@id='details.Company.Website.5']/a")
	WebElement owner1Website;
	
	@FindBy(xpath = "//div[@id='details.Company.Website.5']/a")
	List<WebElement> owner1WebsiteExist;
	
	@FindBy(xpath = "//span[contains(.,'Owner 2')]")
	List<WebElement> owner2Label;
	
	@FindBy(xpath = "//div[@id='details.Company.Name.7']")
	WebElement owner2Name;
	
	@FindBy(xpath = "//div[@id='details.Company.Address.8']")
	WebElement owner2Add;
	
	@FindBy(xpath = "//div[@id='details.Company.City.9']")
	WebElement owner2City;
	
	@FindBy(xpath = "//div[@id='details.Company.State.10']")
	WebElement owner2State;
	
	@FindBy(xpath = "//div[@id='details.Company.Postal_Code.11']")
	WebElement owner2PostalCode;
	
	@FindBy(xpath = "//div[@id='details.Company.Website.12']/a")
	WebElement owner2Website;
	
	@FindBy(xpath = "//div[@id='details.Company.Website.12']/a")
	List<WebElement> owner2WebsiteExist;
	
	
	/*
	 * Construction Details
	 */
	@FindBy(xpath = "//div[@id='details.Construction_Details.Repnum.0']")
	WebElement repnum;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Version.1']")
	WebElement version;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Source_Extract_ID.2']")
	WebElement sourceExtractId;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Report_Date.3']")
	WebElement reportDate;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Stage*.4']")
	WebElement stage;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Est._Start_Date.5']")
	WebElement estStartDate;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Est._Comp_Date.6']")
	WebElement estCompDate;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Status.7']")
	WebElement status;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Construction_Details.0']")
	WebElement constructionDetails;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Final_Date.1']")
	WebElement finalDate;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Final_Status.2']")
	WebElement finalStatus;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Start_Date.3']")
	WebElement startDate;
	
	@FindBy(xpath = "//div[@id='details.Construction_Details.Start_Status.4']")
	WebElement startStatus;
	
	public DodgeAdditionalDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Property Name Header
	public String returnPropNameHeader()	{ return propNameHeader.getText(); }
	
	//Location
	public String returnRepnum() 			{ return repnum.getText(); }	
	public String returnAdd1() 				{ return address1.getText(); }  
	public boolean isAdd2Exist()			{ return add2Exist.size() != 0; }
	public String returnAdd2() 				{ return address2.getText(); }
	public String returnCity() 				{ return city.getText(); }
	public String returnState() 			{ return state.getText(); }
	public String returnPostalCode() 		{ return postalCode.getText(); }
	public String returnCounty() 			{ return county.getText(); }
	public String returnLatLong() 			{ return latLong.getText(); }
	public String returnFIPSName() 			{ return FIPSName.getText(); }
	public String returnFIPSCode() 			{ return FIPSCode.getText(); }
	public String returnMSAName() 			{ return MSAName.getText(); }
	public String returnMSACode() 			{ return MSACode.getText(); }
	public String returnNCREIF() 			{ return NCREIF.getText(); }
	
	//Property Characteristics
	public String returnPropType()			{ return propType.getText(); }
	public String returnPropSubtype()		{ return propSubtype.getText(); }
	public String returnHardCost()			{ return hardCost.getText(); }
	public String returnArea()				{ return area.getText(); }
	public String returnUnits()				{ return units.getText(); }
	public String returnBuildings()			{ return buildings.getText(); }
	public String returnFloors()			{ return floors.getText(); }
	public String returnParkingMeasure()	{ return parkingMeasure.getText(); }
	public String returnHotelBrands()		{ return hotelBrands.getText(); }
	public String returnTenants()			{ return tenants.getText(); }
	public boolean returnLeedRegistered()	{ return leedRegistered.size() != 0; }
	public String returnLeedRegRating() 	{ return leedRegRating.getText(); }
	public boolean returnLeedIntended()		{ return leedIntended.size() != 0; }
	public String returnLeedIntRating()		{ return leedIntendedRating.getText(); }
	
	//Company
	public String returnOwner1Name() 		{ return owner1Name.getText(); }
	public String returnOwner1Add() 		{ return owner1Add.getText(); }
	public String returnOwner1City() 		{ return owner1City.getText(); }
	public String returnOwner1State() 		{ return owner1State.getText(); }
	public String returnOwner1PostalCode()	{ return owner1PostalCode.getText(); }
	public String returnOwner1Website() 	{ return owner1Website.getText(); }
	public boolean isOwner1WebsiteExist()	{ return owner1WebsiteExist.size() != 0; }
	public boolean isOwner2Displayed()		{ return owner2Label.size() != 0; }
	public String returnOwner2Name() 		{ return owner2Name.getText(); }
	public String returnOwner2Add() 		{ return owner2Add.getText(); }
	public String returnOwner2City() 		{ return owner2City.getText(); }
	public String returnOwner2State() 		{ return owner2State.getText(); }
	public String returnOwner2PostalCode()	{ return owner2PostalCode.getText(); }
	public String returnOwner2Website() 	{ return owner2Website.getText(); }
	public boolean isOwner2WebsiteExist()	{ return owner2WebsiteExist.size() != 0; }

	//Construction Details
	public String returnVersion()			{ return version.getText(); }
	public String returnSourceExtractId()	{ return sourceExtractId.getText(); }
	public String returnReportDate()		{ return reportDate.getText(); }
	public String returnStage()				{ return stage.getText(); }
	public String returnEstStartDate()		{ return estStartDate.getText(); }
	public String returnEstCompDate()		{ return estCompDate.getText(); }
	public String returnStatus()			{ return status.getText(); }
	public String returnConstDetails()		{ return constructionDetails.getText(); }
	public String returnFinalDate()			{ return finalDate.getText(); }
	public String returnFinalStatus()		{ return finalStatus.getText(); }
	public String returnStartDate()			{ return startDate.getText(); }
	public String returnStartStatus()		{ return startStatus.getText(); }
	
}
