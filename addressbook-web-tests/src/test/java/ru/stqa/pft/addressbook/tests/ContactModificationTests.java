package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()){
      // Проверка того, что группа есть, а если нет - она создается **********
      app.getNavigationHelper().gotoGtoupPage();
      if (! app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("test8", null, null));
      }
      // блок проверки наличия группы окончен ********************************

      //В случае отсутствия контакта, он создается. ***************************
      app.getNavigationHelper().gotoAddNewContact();
      app.getContactHelper().createContact(new ContactData("Ivan100","Pomidorov",
              "Minsk, Gagarina 21/14","+375 17 5544120", "+375 29 6222552",
              "test8"), true);
    }
    //*************************************************************************
    int before = app.getContactHelper(). getContactCount();
    app.getContactHelper().initModificationContact(before - 1); //выбираем последний элемент
    app.getContactHelper().fillContactForm(new ContactData("Sasha21",
            "Pomidorov1", "Minsk, Gagarina 21/14","+375 17 5544120",
            "+375 29 6222552",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactsPage();
    int after = app.getContactHelper(). getContactCount();
    Assert.assertEquals(after, before);
  }

}


