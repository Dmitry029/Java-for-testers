package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnToContactsPage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    }


  public void submitContactCreation() {click(By.name("submit"));}

  public void selectModifiedContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[14]/td[8]/a/img"));
  }

  public void submitContactModification()
  {
    click(By.name("update"));
  }


  public void selectContact() {
    click(By.id("25"));

  }

  public void deledeContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
}

