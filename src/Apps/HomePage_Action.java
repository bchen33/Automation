package Apps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.css.ElementCSSInlineStyle;

import Utility.Constant;
import PageObjects.HomePage;

public class HomePage_Action {
	
	public static void goToRepoSearch(WebDriver driver){
		HomePage.RepoSearchBtn(driver).click();
	}
}