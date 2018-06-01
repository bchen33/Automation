package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private static WebElement elmt = null;

	//find username input elememnt
	public static WebElement RepoSearchBtn(WebDriver driver){
		elmt = driver.findElement(By.xpath("//div[@class='module-circle repo-search']"));
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(elmt));
		
		//elmt = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'repo-search')]")));
//				driver.findElement(By.xpath("//div[contains(@class,'repo-search')]"));
		return elmt;
	}
	
}
