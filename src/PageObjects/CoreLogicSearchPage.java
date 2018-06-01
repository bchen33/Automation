package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CoreLogicSearchPage {
	
	final WebDriver driver;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button")
	WebElement dataSourceDropdown;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button/following-sibling::ul/li[contains(.,'CoreLogic')]")
	WebElement coreLogicSelection;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button/following-sibling::ul/li[contains(.,'CoreLogic')]")
	List<WebElement> coreLogicSelectionExist;
	
	@FindBy(xpath = "//button[contains(.,'More Filters')]")
	WebElement moreFilters;
	
	@FindBy(xpath = "//input[@id='CoreLogic.propertyAddressStd_tx']")
	WebElement addressInput;
	
	@FindBy(xpath = "//button[@id='itemMenu.CoreLogic.propertyState_cd']")
	WebElement stateDropdown;
	
	@FindBy(xpath = "//input[@id='CoreLogic.propertyCity_tx']")
	WebElement cityInput;
	
	@FindBy(xpath = "//button[@id='itemMenu.CoreLogic.propertyType_tx']")
	WebElement propTypeDropdown;
	
	@FindBy(xpath = "//input[@id='CoreLogic.transactions_json.buyerName1_tx,transactions_json.buyerName2_tx']")
	WebElement buyerNameInput;
	
	@FindBy(xpath = "//input[@id='CoreLogic.transactions_json.sellerName_tx']")
	WebElement sellerNameInput;
	
	@FindBy(xpath = "//input[@id='CoreLogic.apn_tx,apnPrevious_tx.sidebar']")
	WebElement APNInput;
	
	@FindBy(xpath = "//input[@id='CoreLogic.apn_tx,apnPrevious_tx.sidebar']")
	List<WebElement> APNLabelExist;
	
	@FindBy(xpath = "//button[@id='clear-button']")
	WebElement clearBtn;
	
	@FindBy(xpath = "//button[contains(@class,'button orange')]")
	WebElement searchBtn;
	
	@FindBy(xpath = "//input[@id='CoreLogic.propertyZipCode_tx']")
	WebElement postalCodeInput;
	
	@FindBy(xpath = "//input[@id='CoreLogic.municipalityName_tx']")
	WebElement countyInput;
	
	@FindBy(xpath = "//input[@id='CoreLogic.fips_tx']")
	WebElement fipsCodeInput;
	
	@FindBy(xpath = "//input[@id='radius.CoreLogic.location_GeoPoint']")
	WebElement radiusInput;
	
	@FindBy(xpath = "//button[@id='itemMenu.CoreLogic.location_GeoPoint']")
	WebElement radiusDropdown;
	
	@FindBy(xpath = "//input[@id='latitude.CoreLogic.location_GeoPoint']")
	WebElement latitudeInput;
	
	@FindBy(xpath = "//input[@id='longitude.CoreLogic.location_GeoPoint']")
	WebElement longitudeInput;
	
	@FindBy(xpath = "//input[@id='CoreLogic.vendorRecord_id.textfield']")
	WebElement masterTaxIdInput;
	
	@FindBy(xpath = "//div[@id='body']//datatable-body-cell[1]/div[1]/span[1]/span[1]")
	WebElement firstRow;
	
	public boolean isCoreLogicExist()	{ return coreLogicSelectionExist.size() != 0; }
	
	public CoreLogicSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCoreLogicDropdown() throws NoSuchElementException {
		dataSourceDropdown.click();
		if (isCoreLogicExist()) {
			System.out.println("CoreLogic exist in dropdown");
		}
		Assert.assertTrue(isCoreLogicExist());
		coreLogicSelection.click();
		Boolean elementNotPresent = ExpectedConditions.not(ExpectedConditions.visibilityOf(APNInput)).apply(driver);
		Assert.assertFalse(elementNotPresent);
	}
	
	public void inputAddress(String address) throws Exception {
		addressInput.clear();
		addressInput.sendKeys(address);
		addressInput.sendKeys(Keys.TAB);
		//addressInput.sendKeys(Keys.ENTER);
		//Thread.sleep(3000);
		System.out.println("entered address: " + address);
	}
	
	public void selectState(String state) throws NoSuchElementException {
		((JavascriptExecutor) driver).executeScript("arguments[0].focus()", stateDropdown);
		stateDropdown.click();
		WebElement stateSelection = driver.findElement(By.xpath(".//*[@id='left-table']//label[contains(.,'State')]/parent::node()/following-sibling::span//li[@class='dropdown-item' and contains(text(),'"+state+"')]"));
		stateSelection.click();
		//System.out.println("state selected: " + state);
	}
	
	public void inputCity(String city) throws Exception {
		cityInput.clear();
		cityInput.sendKeys(city);
		cityInput.sendKeys(Keys.TAB);
		//cityInput.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//System.out.println("entered city: " + city);
	}
	
	public void inputPostalCode(String postalCode) throws NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		moreFilters.click();
		wait.until(ExpectedConditions.visibilityOf(postalCodeInput));
		postalCodeInput.clear();
		postalCodeInput.sendKeys(postalCode);
		//System.out.println("entered postal code: " + postalCode);
	}
	
	public void inputMasterTaxId(String masterTaxId) throws NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.visibilityOf(masterTaxIdInput));
		masterTaxIdInput.clear();
		masterTaxIdInput.sendKeys(masterTaxId);
	}
	
	public void clickSearchBtn() throws NoSuchElementException, InterruptedException {
		searchBtn.click();
		System.out.println("clicked Search");
	}
	
	public void clearAll() throws NoSuchElementException, InterruptedException {
		clearBtn.click();
		System.out.println("clicked Clear");
		Thread.sleep(5000);
	}
	
	public boolean verifyTooltip(String value) throws NoSuchElementException {
		List<WebElement> tooltip = driver.findElements(By.xpath("//li[@class='nav-item' and contains(@title,'" + value + "')]"));
		boolean isExist = false; 
		if (tooltip.size() > 0) {
			isExist = true;
			System.out.println(value + " tooltip present");
		}
		return isExist;
	}
	
	public boolean verifyResultText(String value) throws NoSuchElementException {
		List<WebElement> resultText = driver.findElements(By.xpath(".//*[@class='datatable-body-cell-label']/span/span[contains(.,'"+ value +"')]"));
		boolean isExist = false;
		if (resultText.size() > 0) {
			isExist = true;
			System.out.println(value + " result present");
		}
		return isExist;
	}
	
	public WebElement getFirstRow() {
		return firstRow;
	}
	
}