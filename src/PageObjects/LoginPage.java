package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private static WebElement elmt = null;

	//find username input elememnt
	public static WebElement UserNameElmt(WebDriver driver){
		elmt = driver.findElement(By.id("usernameInput"));
		return elmt;
	}
	
	//find password input element
	public static WebElement PasswordElmt(WebDriver driver){
		elmt = driver.findElement(By.id("passwordInput"));
		return elmt;
	}
	
	//find Login button element
	public static WebElement LoginBtnElmt(WebDriver driver){
		elmt = driver.findElement(By.xpath(".//button[@type='submit']"));
		return elmt;
	}
}
