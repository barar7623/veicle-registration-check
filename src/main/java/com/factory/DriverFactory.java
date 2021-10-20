package com.factory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    /**
     * This method is used to initialize the threadLocal driver on the basis of given broswer
     * @param browser
     * @return
     */
  public WebDriver init_driver(String browser) {
    System.out.println("Browser value is: " + browser);
    if (browser.equals("chrome")) {
      WebDriverManager.chromedriver().setup();
      threadLocal.set(new ChromeDriver());
    } else if (browser.equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      threadLocal.set(new FirefoxDriver());
    } else if (browser.equals("safari")) {
      threadLocal.set(new SafariDriver());
    } else {
      System.out.println("Please enter valid browser value:" + browser);
    }
    getDriver().manage().deleteAllCookies();
    getDriver().manage().window().maximize();
    return getDriver();
  }

    /**
     * This is used to get driver with ThreadLocal
     * @return
     */
  public static synchronized WebDriver getDriver(){
     return threadLocal.get();
  }

}
