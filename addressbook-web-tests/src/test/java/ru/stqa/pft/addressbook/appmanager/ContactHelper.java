package ru.stqa.pft.addressbook.appmanager;

//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.*;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void returnToContactsPage() {
    wd.findElement(By.linkText("home page")).click();
  }



  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      //creation true для теста создания контакта. Тест увидит наличие нужного эл-та и выберет
      //из выпадающего списка группу по имени. Тест для модиф контакта creation false перех на else
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
      // Проверяем, что в форме нет элемента выбора группы. Контролируем отсутствие списка групп
    }
  }


  public void submitContactCreation() {click(By.name("submit"));}



// Методы Для модификации контакта
  public void initModificationContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification()
  {
    click(By.name("update"));
  }



// Методы Для удаления контакта
  public void selectContactDelation() {
      click(By.xpath("//input[@type='checkbox'][1]"));
  }

  public void deleteContact() {

    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

// Создание контакта
  public void createContact(ContactData contact, boolean creation ) {
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToContactsPage();
  }
// Проверка наличия контакта
  public boolean isThereAContact() {
  //  return isElementPresent(By.xpath("//input[@type='checkbox'][1]"));
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }
}

