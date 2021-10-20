package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadRegistrationNumber {
  private WebDriver driver;
  private VehicleCheckPage vehicleCheckPage;
  private VehicleDetailsPage vehicleDetailsPage;

  public ReadRegistrationNumber(WebDriver driver) {
    this.driver = driver;
    vehicleCheckPage = new VehicleCheckPage(driver);
    vehicleDetailsPage = new VehicleDetailsPage(driver);
  }

  private static Pattern p = Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]?$");
  private static Pattern p1 = Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]?[A-Z]{3}$");
  private static Pattern p2 = Pattern.compile("^[A-Z]{3}?$");

  public static boolean isAlphaNumericTest(String s) {
    return p.matcher(s).matches() || p1.matcher(s).matches() || p2.matcher(s).matches();
  }

  public List<String> inputRegNumberList() throws IOException {
    List<String> str = Files.readAllLines(new File("./Data/InputData/car_input.txt").toPath());

    List<String> list = new ArrayList<String>();
    for (String s1 : str) {
      s1 = s1.substring(0, s1.length() - 1);
      String arrayStr[] = s1.split(" ");
      String con = "";
      for (String charact : arrayStr) {
        if (isAlphaNumericTest(charact)) {
          if (!con.isEmpty()) {
            con = con + charact;
          }
          if (charact.length() == 4) {
            con = con + charact;
            continue;
          } else if (charact.length() == 7) list.add(charact);
        }
      }
      if (!con.isEmpty()) list.add(con);
    }
    System.out.println(list);
    return list;
  }

  public void extractInputRegistrationNumberAndVerifyRegistrationNumber() throws Exception {
    for (String inputRegNumber : inputRegNumberList()) {
      WebElement input = driver.findElement(By.id("vrm-input"));
      input.clear();
      input.sendKeys(inputRegNumber);
      if (isElementPresent(vehicleCheckPage.getFullCheckButton)) {
        vehicleCheckPage.clickOnGetAFullCheck();
      }
      vehicleCheckPage.clickOnVehicleCheck();
      vehicleDetailsPage.vehicleDetails();
      if (isElementPresent(vehicleCheckPage.tryAgainLink)) {
        vehicleCheckPage.clickOnTryAgainLink();
      } else {
        AlertIsPresentVehicleNotFound();
      }
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  public void AlertIsPresentVehicleNotFound() {
    try {
      WebDriverWait wait = new WebDriverWait(driver, 60);
      if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
        System.out.println("Alert not displayed");
      } else {

      }
    } catch (NoAlertPresentException ne) {
      System.out.println("Alert not displayed");
    }
  }
}
