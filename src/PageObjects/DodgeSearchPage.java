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

public class DodgeSearchPage {
	
	final WebDriver driver;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button")
	WebElement dataSourceDropdown;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button/following-sibling::ul/li[contains(.,'Construction')]")
	WebElement constructionSelection;
	
	@FindBy(xpath = ".//*[@class='index-names-div']//button/following-sibling::ul/li[contains(.,'Construction')]")
	List<WebElement> constructionSelectionExist;
	
	@FindBy(xpath = "//button[contains(.,'More Filters')]")
	WebElement moreFilters;
	
	@FindBy(xpath = ".//*[@id='Construction.title_tx']")
	WebElement titleInput;
	
	@FindBy(xpath = ".//*[@id='left-table']//label[contains(.,'Title')]")
	List<WebElement> titleLabelExist;
	
	@FindBy(xpath = ".//*[@id='Construction.addr1_tx']")
	WebElement address1Input;
	
	@FindBy(xpath = ".//*[@id='Construction.addr2_tx']")
	WebElement address2Input;
	
	@FindBy(xpath = ".//*[@id='itemMenu.Construction.state_tx']")
	WebElement stateDropdown;
	
	@FindBy(xpath = ".//*[@id='Construction.city_tx']")
	WebElement cityInput;
	
	@FindBy(xpath = ".//*[@id='itemMenu.Construction.primaryProperty_tx']")
	WebElement propTypeDropdown;
	
	@FindBy(xpath = ".//*[@id='Construction.owner_json.name_tx']")
	WebElement ownerInput;
	
	@FindBy(xpath = "//button[@id='clear-button']")
	WebElement clearBtn;
	
	@FindBy(xpath = "//button[contains(@class,'button orange')]")
	WebElement searchBtn;
	
	@FindBy(xpath = ".//*[@id='Construction.zipCode_tx']")
	WebElement postalCodeInput;
	
	@FindBy(xpath = ".//*[@id='Construction.sourceKey_tx.textfield']")
	WebElement repnumInput;
	
	/*
	 * todo - add remaining input fields
	 */
	
	@FindBy(xpath = "//div[@id='body']//datatable-body-cell[1]/div[1]/span[1]/span[1]")
	WebElement firstRow;
	
	public boolean isConstructionExist()	{ return constructionSelectionExist.size() != 0; }
	
	public DodgeSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectConstructionDropdown() throws NoSuchElementException {
		dataSourceDropdown.click();
		if (isConstructionExist()) {
			System.out.println("Construction exist in dropdown");
		}
		Assert.assertTrue(isConstructionExist());
		constructionSelection.click();
		Boolean elementNotPresent = ExpectedConditions.not(ExpectedConditions.visibilityOf(titleInput)).apply(driver);
		Assert.assertFalse(elementNotPresent);
	}
	
	public void inputTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(titleInput));
		titleInput.clear();
		titleInput.sendKeys(title);
		titleInput.sendKeys(Keys.TAB);
		titleInput.sendKeys(Keys.ENTER);
		//System.out.println("entered title: " + title);
	}
	
	public void inputAddress1(String address) throws Exception {
		address1Input.clear();
		address1Input.sendKeys(address);
		Thread.sleep(2000);
		address1Input.sendKeys(Keys.TAB);
		address1Input.sendKeys(Keys.ENTER);
		//System.out.println("entered address: " + address);
	}
	
	public void selectState(String state) throws NoSuchElementException, InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].focus()", stateDropdown);
		Thread.sleep(2000);
		address1Input.sendKeys(Keys.TAB);
		address1Input.sendKeys(Keys.ENTER);
		stateDropdown.click();
		WebElement stateSelection = driver.findElement(By.xpath("//li[@class='dropdown-item'][contains(text(),'"+state+"')]"));
		stateSelection.click();
		//System.out.println("state selected: " + state);
	} 
	
	public void inputCity(String city) throws Exception {
		cityInput.clear();
		cityInput.sendKeys(city);
		cityInput.sendKeys(Keys.TAB);
		cityInput.sendKeys(Keys.ENTER);
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
	
	public void inputRepnum(String repnum) throws NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		repnumInput.clear();
		repnumInput.sendKeys(repnum);
		//System.out.println("entered postal code: " + postalCode);
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
	
	public void searchByRepnum(String repnum) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		moreFilters.click();
		wait.until(ExpectedConditions.visibilityOf(repnumInput));
		Thread.sleep(3000);
		repnumInput.click();
		repnumInput.clear();
		repnumInput.sendKeys(repnum);
		Thread.sleep(3500);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", searchBtn);
		
		Thread.sleep(3500);
		getFirstRow().click();
		
		driver.findElement(By.xpath("//div[@class='float-right']//a")).click();
	}

	public WebElement getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(WebElement firstRow) {
		this.firstRow = firstRow;
	}
}
