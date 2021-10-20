package com.pages;

import com.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleDetailsPage {

  private WebDriver driver;
  private By header = By.xpath("//h1");

  public VehicleDetailsPage(WebDriver driver) {
    this.driver = driver;
  }

  public void vehicleDetails() throws InterruptedException {
    Thread.sleep(500);
    String beforeXpathVehicleResults = "//*[@id='m']/div[2]/div[1]/div/div[2]/dl[";
    String afterXpathVehicleResults = "]/dd";

    for (int i = 1; i <= 4; i++) {
      String actualXpath = beforeXpathVehicleResults + i + afterXpathVehicleResults;
      WebElement element = driver.findElement(By.xpath(actualXpath));
      ElementUtil.waitForElementToBeVisible(driver, element, 10);
      System.out.println(element.getText() + ",");
    }
  }
}
