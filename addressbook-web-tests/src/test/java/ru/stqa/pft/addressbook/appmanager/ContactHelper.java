package ru.stqa.pft.addressbook.appmanager;

//import org.apache.bcel.generic.Select;
import org.openqa.selenium.*;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  //Возврат на стр контактов из модификации контакта
  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();//
  }

  //Возврат на стр контактов при удалении контакта. Через верхнее меню
  public void returnToHomePage2() {
    wd.findElement(By.linkText("home")).click();
  }

  //Заполнение формы контакта*******************************************************************
  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      //creation true для теста создания контакта. Тест увидит наличие нужного эл-та и выберет
      //из выпадающего списка группу по имени. Тест для модиф контакта creation false перех на else
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
      // Проверяем, что в форме нет элемента выбора группы. Контролируем отсутствие списка групп
    }
  }
  //************************************************************************************************
  public void submitContactCreation() {click(By.name("submit"));}

  // Методы выбора контакта bp для  МОДИФИКАЦИИ по id ДРУГИЕ СЕЛЕКТОРЫ l5_m9!!!!********************************************
  public void selectContactModificationById(int id) {
    //click(By.xpath("//input[@value='" + id +"']/../..//img[@alt='Edit']"));
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }
  //*************************************************************************************************
  public void submitContactModification()
  {
    click(By.name("update"));
  }
  //**************************************************************************************************

  // Методы ВЫБОРА контакта для УДАЛЕНИЯ. По ID*****************************************************
  public void selectContactDelationById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id +"']")).click(); // получаем id строки
    System.out.println(id);
  }
  //Удаление контакта**********************************************************************************
  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input")); //локатор кнопки Delete (Selenium Builder)
    wd.switchTo().alert().accept();
  }
   // Создание контакта***********************************************************************************
  public void create(ContactData contact, boolean creation ) {
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }
   // Модификация контакта по id****************************************************************************
  public void modify(ContactData contact) {
    selectContactModificationById(contact.getId());
    fillContactForm(contact,false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }
  // Удаление контакта по идентификатору id***************************************************************
  public void delete(ContactData сontact) {
    selectContactDelationById(сontact.getId());
    deleteContact();
    contactCache = null;
    returnToHomePage2();//Возврат на Home page
  }
  // Проверка наличия контакта****************************************************************************
  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }
   //Подсчет контактов  и разные локаторы*******************************************************************
  public int countContact() {
        return wd.findElements(By.xpath("//tr[@name='entry']")).size();
  }

  // return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).size();
  // return wd.findElements(By.name("selected[]")).size();
  //*******************************************************************************************************

  private Contacts contactCache = null; //Кеширование l5_m7

  //Метод создания МНОЖЕСТВА контактов************************************************************************
  public Contacts all() {
    if (contactCache != null){
        return new Contacts(contactCache);//проверка пустой ли cache?
      }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements){
      String firstname = element.findElement(By.xpath(".//td[3]")).getText(); //выбор имени 3- я колонка!!!
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      // полученин id

      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return new Contacts(contactCache);
  }
}




//Удаленные в l5_m5
/*// Модификация контакта*********************************************************************************
  public void modify(int index, ContactData contact) {
    selectContactModification(index);
    fillContactForm(contact,false);
    submitContactModification();
    returnToHomePage();
  }
//******************************************************************************************************
*/

/*// Методы выбора контакта для МОДИФИКАЦИИ по индексу***************************************************
// click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  public void selectContactModification(int index) {
    wd.findElements(By.xpath("//tr[@name='entry']")).get(index).  //получение всех сторок, выбор строк по индексу
    findElement(By.xpath(".//a[contains(@href,'edit')]")).click();  //выбор в строке элемента и click
  }
//*************************************************************************************************
*/

/*// Удаление контакта************************************************************************************
   public void delete(int index) {
     selectContactDelation(index);  // Выбор последнего эл-та (before -1)
     deleteContact();
     returnToHomePage2();//Возврат на Home page
}*/

/*// Методы ВЫБОРА контакта для УДАЛЕНИЯ. По index. Другие локаторы*****************************************************
  public void selectContactDelation(int index) {
    wd.findElements(By.name("selected[]")).get(index).click(); // Локатор name
    }
    //click(By.xpath("//input[@type='checkbox'][1]"));
    //click(By.cssSelector("td.center"));
    //click(By.name("selected[]"));
//***************************************************************************************************
*/

/*//Метод создания списка контактов************************************************************************
  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements){
      String firstname = element.findElement(By.xpath(".//td[3]")).getText(); //выбор имени 3- я колонка!!!
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      // полученин id

      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }*/