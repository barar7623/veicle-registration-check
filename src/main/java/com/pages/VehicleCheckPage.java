package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Pattern;

public class VehicleCheckPage {

  private WebDriver driver;
  private static Pattern p = Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]?$");
  private static Pattern p1 = Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]?[A-Z]{3}$");
  private static Pattern p2 = Pattern.compile("^[A-Z]{3}?$");

  private By inputRegistrationField = By.id("vrm-input");
  public By checkVehicle = By.xpath("//*[text() = 'Check Vehicle']");
  public By getFullCheckButton = By.xpath("//*[text() = 'Get a Full Check']");
  private By freeCarCheckButton = By.xpath("//*[starts-with(@class,'jsx-11')]");
  public By tryAgainLink = By.xpath("//*[text() = 'Try Again']");
  public By vehicleNotFound = By.xpath("//*[text() = 'Vehicle Not Found']");

  public VehicleCheckPage(WebDriver driver) {
    this.driver = driver;
  }

  public String getVehiclePageTitle() {
    return driver.getTitle();
  }

  public void clickOnGetAFullCheck() {
    driver.findElement(getFullCheckButton).click();
  }

  public void clickOnVehicleCheck() {
    driver.findElement(checkVehicle).click();
  }

  public void clickOnTryAgainLink() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement element =
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'Try Again']")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }
}
