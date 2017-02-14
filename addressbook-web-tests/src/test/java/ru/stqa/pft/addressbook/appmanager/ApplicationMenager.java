package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Администратор on 28.01.2017.
 */
public class ApplicationMenager {

  WebDriver wd;


  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;


  public ApplicationMenager(String browser) {
    this.browser = browser;
  }


  public void init() {

    if (Objects.equals(browser, BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if (Objects.equals(browser, BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.IE)){
      wd = new InternetExplorerDriver();
      wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }



    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //5-задержка для появления элемента
   // wd.get("http://localhost:8080/addressbook/group.php");    //Приложение открывается на стр Groups
    wd.get("http://localhost:8080/addressbook");                //Приложение открывается на главной
    contactHelper = new ContactHelper(wd);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() { wd.quit();  }

  public GroupHelper group() { return groupHelper;  }

  public ContactHelper contact() { return contactHelper;  }

  public NavigationHelper goTo() {return navigationHelper; }

}
