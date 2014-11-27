import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.junit.*;
import junit.framework.TestCase;

public class test extends TestCase {

	public void testUserName() {
		// 1st usr test
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
		WebElement usrName = driver.findElement(By.name("userId"));
		WebElement pwd = driver.findElement(By.name("userPassword"));

		usrName.clear();
		usrName.sendKeys("andy");
		pwd.clear();
		pwd.sendKeys("apple");
		pwd.submit();

		assertTrue(driver.getTitle().equals("Online temperature conversion calculator"));

		// 2nd usr test
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
		usrName = driver.findElement(By.name("userId"));
		pwd = driver.findElement(By.name("userPassword"));

		usrName.clear();
		// Should be case insensitive
		usrName.sendKeys("Bob");
		pwd.clear();
		pwd.sendKeys("bathtub");
		pwd.submit();

		assertTrue(driver.getTitle().equals("Online temperature conversion calculator"));

		// 3rd usr test
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
		usrName = driver.findElement(By.name("userId"));
		pwd = driver.findElement(By.name("userPassword"));

		usrName.clear();
		// Insert space
		usrName.sendKeys(" charley");
		pwd.clear();
		pwd.sendKeys("china");
		pwd.submit();

		assertTrue(driver.getTitle().equals("Online temperature conversion calculator"));

		driver.quit();
	}

	public void testLockout() {

		WebDriver driver = new HtmlUnitDriver();

		// Lockout test
		// 3 wrong login results in 4th attempt failure
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
		WebElement usrName = driver.findElement(By.name("userId"));
		WebElement pwd = driver.findElement(By.name("userPassword"));

		usrName.clear();
		usrName.sendKeys("nullVal");
		pwd.clear();
		pwd.sendKeys("nullVal");
		pwd.submit();

		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
		usrName = driver.findElement(By.name("userId"));
		pwd = driver.findElement(By.name("userPassword"));

		usrName.clear();
		usrName.sendKeys("nullVal");
		pwd.clear();
		pwd.sendKeys("nullVal");
		pwd.submit();

		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
		usrName = driver.findElement(By.name("userId"));
		pwd = driver.findElement(By.name("userPassword"));

		usrName.clear();
		usrName.sendKeys("nullVal");
		pwd.clear();
		pwd.sendKeys("nullVal");
		pwd.submit();

		// 4th attempt should fail
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
		usrName = driver.findElement(By.name("userId"));
		pwd = driver.findElement(By.name("userPassword"));

		usrName.clear();
		usrName.sendKeys("andy");
		pwd.clear();
		pwd.sendKeys("apple");
		pwd.submit();

		assertFalse(driver.getTitle().equals("Online temperature conversion calculator"));

		driver.quit();
	}

	public void testInput() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://apt-public.appspot.com/testing-lab-conversion?farenheitTemperature=9.73E2");
		WebElement res = driver.findElement(By.tagName("h2")); 
		assertTrue(res.getText().substring(0, 34).equals("Need to enter a valid temperature!"));
		driver.quit();
	}

	public void testNumberFormat1() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://apt-public.appspot.com/testing-lab-conversion?farenheitTemperature=abc");
		WebElement res = driver.findElement(By.tagName("h2"));
		assertTrue(res.getText().substring(0, 34).equals("Need to enter a valid temperature!"));
		driver.quit();
	}

	public void testNumberFormat2() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://apt-public.appspot.com/testing-lab-conversion?farenheitTemperature=300");
		WebElement res = driver.findElement(By.tagName("h2"));
		assertTrue(res.getText().equals("300 Farenheit = 148.9 Celsius"));
		driver.quit();
	} 

	public void testURLParameter() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://apt-public.appspot.com/testing-lab-conversion?farenheitTEMperature=-20");
		WebElement res = driver.findElement(By.tagName("h2")); 
		assertTrue(res.getText().equals("-20 Farenheit = -28.89 Celsius"));
		driver.quit();
	}

	public static void main(String args[]) {
		String[] testCaseName = { test.class.getName() };
			junit.textui.TestRunner.main(testCaseName);
	}

}

