package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by Администратор on 28.01.2017.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGtoupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElements(By.tagName("h1")).getText().eguals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
        click(By.linkText("groups"));
  }


  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }



  public void gotoAddNewContact() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElements(By.tagName("h1")).getText().eguals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {
      return;
    }

    click(By.linkText("add new"));
  }


}
