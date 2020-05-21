package com.perscholas.jenkins_pipeline.web_automation;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IndexPageTest {
	private static WebDriver driver = null;
	private String actual = null;
	private String expected = null;
	
	@BeforeClass
	public static void setUp() {
		String driverName = null;
		if (System.getProperty("os.name").contains("Windows")) {
			driverName = "\\chromedriver.exe";
		} else {
			driverName = "/chromedriver";
		}
		System.setProperty("webdriver.chrome.driver", System.getenv("SELENIUM_DRIVERS_HOME") + driverName);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/jenkins_pipeline_servlet_selenium/HomeServlet");
	}
	
	@AfterClass
	public static void shutDown() {
		driver.quit();
	}
	
	@Test
	public void testIndexPageTitle() {
		actual = driver.getTitle();
		expected = "Index";
		assertEquals(actual, expected);
	}
	
	@Test
	public void testH2Element() {
		actual = driver.findElement(By.cssSelector("h2")).getText();
		expected = "Servlet Application with Selenium Tests";
		assertEquals(actual, expected);
	}
}