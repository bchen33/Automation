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

public class CoreLogicAdditionalDetailsPage { 
	
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
	
	@FindBy(xpath = "//div[@id='details.Location.City.1']")
	WebElement city;
	
	@FindBy(xpath = "//div[@id='details.Location.State.2']")
	WebElement state;
	
	@FindBy(xpath = "//div[@id='details.Location.Postal_Code.3']")
	WebElement postalCode;
	
	@FindBy(xpath = "//div[@id='details.Location.County.4']")
	WebElement county;
	
	@FindBy(xpath = "//div[@id='details.Location.FIPS_Code.5']")
	WebElement FIPSCode;
	
	@FindBy(xpath = "//div[@id='details.Location.APN.6']")
	WebElement APN;
	
	@FindBy(xpath = "//div[@id='details.Location.Original_APN.7']")
	WebElement originalAPN;
	
	@FindBy(xpath = "//div[@id='details.Location.Lat_/_Long.8']")
	WebElement latLong;
	
	@FindBy(xpath = "//div[@id='details.Location.Zoning.9']")
	WebElement zoning;
	
	@FindBy(xpath = "//div[@id='details.Location.Subdivision_Name.10']")
	WebElement subdivisionName;
	
	@FindBy(xpath = "//div[@id='details.Location.Township.11']")
	WebElement township;
	
	/*
	 * Property
	 */
	@FindBy(xpath = "//div[@id='details.Property.Prop_Type.0']")
	WebElement propType;
	
	@FindBy(xpath = "//div[@id='details.Property.Value.1']")
	WebElement value;
	
	@FindBy(xpath = "//div[@id='details.Property.Units.2']")
	WebElement units;
	
	@FindBy(xpath = "//div[@id='details.Property.Floors.3']")
	WebElement floors;
	
	@FindBy(xpath = "//div[@id='details.Property.Buildings.4']")
	WebElement buildings;

	@FindBy(xpath = "//div[@id='details.Property.SqFt.5']")
	WebElement sqFt;
	
	@FindBy(xpath = "//div[@id='details.Property.Total_SqFt.6']")
	WebElement totalSqFt;
	
	@FindBy(xpath = "//div[@id='details.Property.Land_SqFt.7']")
	WebElement landSqFt;
	
	@FindBy(xpath = "//div[@id='details.Property.Total_Rooms.8']")
	WebElement totalRooms;
	
	@FindBy(xpath = "//div[@id='details.Property.Year_Built.9']")
	WebElement yearBuilt;
	
	@FindBy(xpath = "//div[@id='details.Property.Year_Renov.10']")
	WebElement yearRenov;
	
	@FindBy(xpath = "//div[@id='details.Property.Master_Tax_ID.11']")
	WebElement masterTaxID;
	
	/*
	 * Tax
	 */
	@FindBy(xpath = "//div[@id='details.Tax.Tax_Amount.0']")
	WebElement taxAmount;
	
	@FindBy(xpath = "//div[@id='details.Tax.Tax_Year.1']")
	WebElement taxYear;
	
	@FindBy(xpath = "//div[@id='details.Tax.Assd._Total_Value.2']")
	WebElement assdTotalValue;
	
	@FindBy(xpath = "//div[@id='details.Tax.Assd._Land_Value.3']")
	WebElement assdLandValue;
	
	@FindBy(xpath = "//div[@id='details.Tax.Assd._Improv._Value.4']")
	WebElement assdImprovValue;
	
	@FindBy(xpath = "//div[@id='details.Tax.Assd._Year.5']")
	WebElement assdYear;
	
	@FindBy(xpath = "//div[@id='details.Tax.Census_Track.6']")
	WebElement censusTrack;
	
	@FindBy(xpath = "//div[@id='details.Tax.Block_Number.7']")
	WebElement blockNumber;
	
	@FindBy(xpath = "//div[@id='details.Tax.Lot_Number.8']")
	WebElement lotNumber;
	
	@FindBy(xpath = "//div[@id='details.Tax.Lot_Area.9']")
	WebElement lotArea;
	
	@FindBy(xpath = "//div[@id='details.Tax.Legal_Description.10']")
	WebElement legalDescription;
	
	/*
	 * Owner/Buyer
	 */
	@FindBy(xpath = "//div[@id='details.Owner/Buyer.Name.0']")
	WebElement owner1Name;
	
	@FindBy(xpath = "//div[@id='details.Owner/Buyer.Address.1']")
	WebElement owner1Address;
	
	@FindBy(xpath = "//div[@id='details.Owner/Buyer.City.2']")
	WebElement owner1City;
	
	@FindBy(xpath = "//div[@id='details.Owner/Buyer.State.3']")
	WebElement owner1State;
	
	@FindBy(xpath = "//div[@id='details.Owner/Buyer.Postal_Code.4']")
	WebElement owner1PostalCode;
	
	@FindBy(xpath = "//div[@id='details.Owner/Buyer.Name.6']")
	WebElement owner2Name;
	
	@FindBy(xpath = "//div[@id='details.Owner/Buyer.Name.6']")
	List<WebElement> owner2Exist;
	
	public CoreLogicAdditionalDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Property Name Header
	public String returnPropNameHeader()	{ return propNameHeader.getText(); }
	
	//Location
	public String returnAdd1() 				{ return address1.getText(); }  
	public String returnCity() 				{ 
		FluentWait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(city));
		return city.getText(); }
	public String returnState() 			{ 
		FluentWait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(state));
		return state.getText(); }
	public String returnPostalCode() 		{ return postalCode.getText(); }
	public String returnCounty() 			{ return county.getText(); }
	public String returnFIPSCode() 			{ return FIPSCode.getText(); }
	public String returnAPN() 				{ return APN.getText(); }
	public String returnOriginalAPN()		{ return originalAPN.getText(); }
	public String returnLatLong() 			{ return latLong.getText(); }
	public String returnZoning() 			{ return zoning.getText(); }
	public String returnSubdivisionName()	{ return subdivisionName.getText(); }
	public String returnTownship() 			{ return township.getText(); }
	
	//Property
	public String returnPropType()			{ return propType.getText(); }
	public String returnValue()				{ return value.getText(); }
	public String returnUnits()				{ return units.getText(); }
	public String returnFloors()			{ return floors.getText(); }
	public String returnBuildings()			{ return buildings.getText(); }
	public String returnSqFt()				{ return sqFt.getText(); }
	public String returnTotalSqFt()			{ return totalSqFt.getText(); }
	public String returnLandSqFt()			{ return landSqFt.getText(); }
	public String returnTotalRooms()		{ return totalRooms.getText(); }
	public String returnYearBuilt()			{ return yearBuilt.getText(); }
	public String returnYearRenov()			{ return yearRenov.getText(); }
	public String returnMasterTaxID()		{ return masterTaxID.getText(); }
	
	//Tax
	public String returnTaxAmount() 		{ return taxAmount.getText(); }
	public String returnTaxYear() 			{ return taxYear.getText(); }
	public String returnAssdTotalValue()	{ return assdTotalValue.getText(); }
	public String returnAssdLandValue()		{ return assdLandValue.getText(); }
	public String returnAssdImprovValue()	{ return assdImprovValue.getText(); }
	public String returnAssdYear()			{ return assdYear.getText(); }
	public String returnCensusTrack()		{ return censusTrack.getText(); }
	public String returnBlockNumber()		{ return blockNumber.getText(); }
	public String returnLotNumber()			{ return lotNumber.getText(); }
	public String returnLotArea()			{ return lotArea.getText(); }
	public String returnLegalDescription()	{ return legalDescription.getText(); }
	
	//Owner/Buyer
	public String returnOwner1Name()		{ return owner1Name.getText(); }
	public String returnOwner1Address()		{ return owner1Address.getText(); }
	public String returnOwner1City()		{ return owner1City.getText(); }
	public String returnOwner1State()		{ return owner1State.getText(); }
	public String returnOwner1PostalCode()	{ return owner1PostalCode.getText(); }
	public boolean isOwner2Exist()			{ return owner2Exist.size() != 0; }
	public String returnOwner2Name()		{ return owner2Name.getText(); }
}
