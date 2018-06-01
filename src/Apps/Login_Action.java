package Apps;

import org.openqa.selenium.WebDriver;

import Utility.Constant;
import PageObjects.LoginPage;

public class Login_Action {
	
	public static void execute(WebDriver driver){
		LoginPage.UserNameElmt(driver).sendKeys(Constant.username);
		LoginPage.PasswordElmt(driver).sendKeys(Constant.password);
		LoginPage.LoginBtnElmt(driver).click();
	}
}