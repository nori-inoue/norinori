package jp.gr.norinori.product.web;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 *  ログインテスト
 */
public class WebTest1IE11 extends WebTestBase {
	private StringBuffer verificationErrors = new StringBuffer();


	@Before
	public void setUp() throws Exception {
		File file = new File("./driver/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		this.driver = new InternetExplorerDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String testdir = this.getClass().getSimpleName();
		this.screenshotPath = this.screenshotPath + testdir;

		File path = new File(this.screenshotPath);
		if (!path.exists()) {
			path.mkdir();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	/**
	 * 正常にログインされること
	 * 
	 * @throws Exception
	 */
	public void login_success() throws Exception {
		WebElement uid = this.driver.findElement(By.id("uid"));
		uid.sendKeys("test");
		WebElement pswd = this.driver.findElement(By.id("pswd"));
		pswd.sendKeys("pass");
		WebElement login = this.driver.findElement(By.id("login"));
		login.submit();

		assertEquals("メニュー", this.driver.getTitle());
		capture("login_success", "capture");
	}

	/**
	 * 検索画面が表示されること
	 * 
	 * @throws Exception
	 */
	@Test
	public void display_menu() throws Exception {
		login_success();


		assertTrue(this.driver.findElement(By.xpath("//")).getText().indexOf("結果") >= 0);
		capture("display_menu", "capture");
	}

}