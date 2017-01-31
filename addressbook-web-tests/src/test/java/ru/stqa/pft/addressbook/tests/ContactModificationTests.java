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

    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectModifiedContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivan13", "Pomidorov", "Minsk, Gagarina 21/14", "+375 17 5544120", "+375 29 6222552"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactsPage();
  }



  }


