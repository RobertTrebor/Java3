package de.cimdata.weatherservice.xmlservice;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WeatherServiceWebTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testWeatherServiceWeb() throws Exception {
    driver.get(baseUrl + "/Ue_GlobalweatherService0.1/faces/weather.xhtml");
    driver.findElement(By.id("j_idt8:autoComplete_input")).clear();
    driver.findElement(By.id("j_idt8:autoComplete_input")).sendKeys("gedfsf");
    driver.findElement(By.id("j_idt8:j_idt11")).click();
    driver.findElement(By.id("j_idt8:autoComplete_input")).clear();
    driver.findElement(By.id("j_idt8:autoComplete_input")).sendKeys("Goo#");
    driver.findElement(By.id("j_idt8:j_idt11")).click();
    driver.findElement(By.id("j_idt8:autoComplete_input")).click();
    driver.findElement(By.id("j_idt8:autoComplete_input")).clear();
    driver.findElement(By.id("j_idt8:autoComplete_input")).sendKeys("Bermuda");
    driver.findElement(By.id("j_idt8:j_idt11")).click();
    driver.findElement(By.id("j_idt8:autoComplete_input")).clear();
    driver.findElement(By.id("j_idt8:autoComplete_input")).sendKeys("Germany");
   
    new Select(driver.findElement(By.id("j_idt12:j_idt13"))).selectByVisibleText("Duesseldorf");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
