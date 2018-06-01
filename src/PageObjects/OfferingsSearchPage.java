package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OfferingsSearchPage {
	
	WebDriver driver;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button")
	WebElement dataSourceDropdown;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button/following-sibling::ul/li[contains(.,'Offerings')]")
	WebElement offeringsSelection;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button/following-sibling::ul/li[contains(.,'Offerings')]")
	List<WebElement> offeringsSelectionExist;
	
	@FindBy(xpath = "//button[contains(.,'More Filters')]")
	WebElement moreFilters;
	
	@FindBy(xpath = "//input[@id='Offerings.addr1_tx']")
	WebElement addressInput;
	
	@FindBy(xpath = "//button[@id='itemMenu.Offerings.state_tx']")
	WebElement stateDropdown;
	
	@FindBy(xpath = "//input[@id='Offerings.city_tx']")
	WebElement cityInput;
	
	@FindBy(xpath = "//button[@id='itemMenu.Offerings.propertyType_tx']")
	WebElement typeDropdown;
	
	@FindBy(xpath = "//button[@id='itemMenu.Offerings.status_tx']")
	WebElement statusDropdown;

	@FindBy(xpath = "//input[@id='Offerings.company_tx']")
	WebElement sellerBrokerInput;
	
	@FindBy(xpath = "//input[@id='Offerings.company_tx']")
	List<WebElement> sellerBrokerLabelExist;
	
	@FindBy(xpath = "//button[@id='clear-button']")
	WebElement clearBtn;
	
	@FindBy(xpath = "//button[contains(@class,'button orange')]")
	WebElement searchBtn;
	
	@FindBy(xpath = "//input[@id='Offerings.propertyName_tx']")
	WebElement propNameInput;
	
	@FindBy(xpath = "//input[@id='Offerings.zipCode_tx']")
	WebElement postalCodeInput;
	
	@FindBy(xpath = "//input[@id='Offerings.country_tx']")
	WebElement countryInput;
	
	@FindBy(xpath = "//input[@id='radius.Offerings.location_GeoPoint']")
	WebElement radiusInput;
	
	@FindBy(xpath = "//button[@id='itemMenu.Offerings.location_GeoPoint']")
	WebElement radiusDropdown;
	
	@FindBy(xpath = "//input[@id='latitude.Offerings.location_GeoPoint']")
	WebElement latitudeInput;
	
	@FindBy(xpath = "//input[@id='longitude.Offerings.location_GeoPoint']")
	WebElement longitudeInput;
	
	@FindBy(xpath = "//input[@id='Offerings.project_id.numberfield']")
	WebElement projectIdInput;
	
	/*
	 * todo - add remaining search filters 
	 */
	
	@FindBy(xpath = "//div[@id='body']//datatable-body-cell[1]/div[1]/span[1]/span[1]")
	WebElement firstRow;
	
	

	public boolean isOfferingsExist()	{ return offeringsSelectionExist.size() != 0; }
	
	public OfferingsSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void selectOfferingsDropdown() throws NoSuchElementException {
		dataSourceDropdown.click();
		if (isOfferingsExist()) {
			System.out.println("Offerings exist in dropdown");
		}
		Assert.assertTrue(isOfferingsExist());
		offeringsSelection.click();
		Boolean elementNotPresent = ExpectedConditions.not(ExpectedConditions.visibilityOf(sellerBrokerInput)).apply(driver);
		Assert.assertFalse(elementNotPresent);
		//Assert.assertEquals(sellerBrokerInputExist.size(), 1);
	}
	
	public void inputAddress(String address) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(addressInput));
		addressInput.clear();
		addressInput.sendKeys(address);
		//System.out.println("entered address: " + address);
	}
	
	public void selectState(String state) throws NoSuchElementException {
		stateDropdown.click();
		WebElement stateSelection = driver.findElement(By.xpath("//li[@class='dropdown-item'][contains(text(),'"+state+"')]"));
		stateSelection.click();
		//System.out.println("state selected: " + state);
	}
	
	public void inputCity(String city) throws NoSuchElementException {
		cityInput.clear();
		cityInput.sendKeys(city);
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
	
	public void inputProjectId(String projectId) throws NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.visibilityOf(projectIdInput));
		projectIdInput.clear();
		projectIdInput.sendKeys(projectId);
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
		List<WebElement> tooltip = driver.findElements(By.xpath("//li[@class='nav-item' and @title='" + value + "']"));
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

	public void setFirstRow(WebElement firstRow) {
		this.firstRow = firstRow;
	}
	
	/*
	public void searchByRepnum(String repnum) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		moreFilters.click();
		wait.until(ExpectedConditions.visibilityOf(repnumInput));
		Thread.sleep(3000);
		repnumInput.click();
		repnumInput.clear();
		repnumInput.sendKeys(repnum);
		Thread.sleep(3500);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", submitBtn);
		
		Thread.sleep(3500);
		firstRow.click();
		//wait.until(ExpectedConditions.visibilityOf(address1));
		driver.findElement(By.xpath("//*[@id='body']/ng-sidebar-container/div/div/dp-search-additional-details/div/div[1]")).click();
	} */
}
