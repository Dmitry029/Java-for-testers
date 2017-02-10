package ru.stqa.pft.addressbook.appmanager;

//import org.apache.bcel.generic.Select;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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


//Заполнение формы контакта*******************************************************************
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
//***********************************************************************************************

//************************************************************************************************
  public void submitContactCreation() {click(By.name("submit"));}
//************************************************************************************************

// Методы Для МОДИФИКАЦИИ контакта****************************************************************
// click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  public void initModificationContact(int index) {
    wd.findElements(By.xpath("//tr[@name='entry']")).get(index).  //получение всех сторок, выбор строк по индексу
    findElement(By.xpath(".//a[contains(@href,'edit')]")).click();  //выбор в строке элемента и click
  }
//*************************************************************************************************

  public void submitContactModification()
  {
    click(By.name("update"));
  }

//**************************************************************************************************

// Методы ВЫБОРА контакта для УДАЛЕНИЯ. Другие локаторы*****************************************************
  public void selectContactDelation(int index) {
    wd.findElements(By.name("selected[]")).get(index).click(); // Локатор name
    }
    //click(By.xpath("//input[@type='checkbox'][1]"));
    //click(By.cssSelector("td.center"));
    //click(By.name("selected[]"));
//***************************************************************************************************

//Удаление контакта**********************************************************************************
    public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input")); //локатор кнопки Delete (Selenium Builder)
    wd.switchTo().alert().accept();
  }
//*****************************************************************************************************

// Создание контакта***********************************************************************************
  public void createContact(ContactData contact, boolean creation ) {
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToContactsPage();
  }
//******************************************************************************************************

// Проверка наличия контакта****************************************************************************
  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }
//*******************************************************************************************************
//Подсчет контактов  и разные локаторы*******************************************************************
// return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).size();
// return wd.findElements(By.name("selected[]")).size();

  public int getContactCount() {
        return wd.findElements(By.xpath("//tr[@name='entry']")).size();
  }
//*******************************************************************************************************
//Метод создания списка контактов************************************************************************
  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements){
      String firstname = element.findElement(By.xpath(".//td[3]")).getText(); //выбор имени 3- я колонка!!!
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();

      String id = element.findElement(By.tagName("input")).getAttribute("value");// полученин id

      ContactData contact = new ContactData(id,firstname, lastname, null,
                             null, null, null );
      contacts.add(contact);
    }
    return contacts;
  }
}
//**********************************************************************************************************
