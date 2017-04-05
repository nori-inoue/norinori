package jp.gr.norinori.product.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *  LoginPage
 */
public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	protected String getUrl() {
		return "http://testsrver/inoue/login.php";
	}

	public void setUid(String value) {
		WebElement element = this.driver.findElement(By.id("uid"));
		element.sendKeys(value);
	}

	public void setPswd(String value) {
		WebElement element = this.driver.findElement(By.id("pswd"));
		element.sendKeys(value);
	}


	public WebElement loginForm() {
		WebElement form = this.driver.findElement(By.id("login"));
		return form;
	}

}