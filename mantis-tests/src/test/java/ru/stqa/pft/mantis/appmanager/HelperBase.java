package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by Администратор on 09.03.2017.
 */
public class HelperBase {
    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app)
    {
      this.app = app;
      this.wd = app.getDriver();//сработает ленивая инициализация
    }

    public void click(By locator) {
      wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
      click(locator);
      if (text != null){
        String existingText = wd.findElement(locator).getAttribute("value");
        if ( ! text.equals(existingText)){
          wd.findElement(locator).clear();
          wd.findElement(locator).sendKeys(text);
        }
      }
    }

    protected void attach(By locator, File file) {
      if (file != null){
        wd.findElement(locator).sendKeys(file.getAbsolutePath());//определяем абсолютный путь к файлу l6_m1
        // метод getaasolutePath преобразовывает относительный путь в Абсолютный
      }
    }

    // Пока не используемый метод 3-е занятиек
    public boolean isAlertPresent() {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }
    // Метод проверки наличия элемента на странице (согдасно локатора) l3_m8
    protected boolean isElementPresent(By locator) {
      try{
        wd.findElement(locator);
        return true;
      } catch (NoSuchElementException ex){
        return false;
      }
    }
  }





