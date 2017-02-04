package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Администратор on 28.01.2017.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGtoupPage() {
    click(By.linkText("groups"));
  }

  public void gotoAddNewContact() {
    click(By.linkText("add new"));
  }

  public void gotoHomePage() {click(By.linkText("home"));
  }
}
