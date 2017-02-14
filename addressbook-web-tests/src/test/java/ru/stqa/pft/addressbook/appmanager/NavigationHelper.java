package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



/**
 * Created by Администратор on 28.01.2017.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }


  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))  // l3_ m9 Проверка - находимся ли мы на странице Groups
            // элемент(заголовок) h1 присутствует и его текст совпадает с заданным
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")  //по заголовку  Groups
            && isElementPresent(By.name("new"))) {                          // и эл-ту New group
      return;
    }
        click(By.linkText("groups")); // click выполняется если мы еще не находитмя на странице
  }



  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))){   // Проверка - находимся ли мы на странице Home
      return;                                    // по элементу Таблица
    }
    click(By.linkText("home"));   // click выполняется если мы еще не находитмя на странице
  }



  public void gotoAddNewContact() {
    if (isElementPresent(By.tagName("h1"))   // Проверка - находимся ли мы на странице Add new
            && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {
      return;
    }

    click(By.linkText("add new"));      // click выполняется если мы еще не находитмя на странице
  }

}
