package ru.stqa.pft.addressbook.tests;

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
//В случае отсутствия контакта, он создается.
      app.getNavigationHelper().gotoAddNewContact();
      app.getContactHelper().createContact(new ContactData("Ivan100","Pomidorov",
              "Minsk, Gagarina 21/14","+375 17 5544120", "+375 29 6222552",
              "test9"), true);
    }


    app.getContactHelper().initModificationContact();
    app.getContactHelper().fillContactForm(new ContactData("Sasha1",
            "Pomidorov1", "Minsk, Gagarina 21/14","+375 17 5544120",
            "+375 29 6222552",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactsPage();
  }

}


