package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

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
    List<ContactData> before = app.getContactHelper().getContactList(); // before - список контактов
    app.getContactHelper().initModificationContact(before.size() - 1); //выбираем последний элемент
    app.getContactHelper().fillContactForm(new ContactData("Sasha21",
            "Pomidorov1", "Minsk, Gagarina 21/14","+375 17 5544120",
            "+375 29 6222552",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactsPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}


//int before = app.getContactHelper(). getContactCount();
//int after = app.getContactHelper(). getContactCount();