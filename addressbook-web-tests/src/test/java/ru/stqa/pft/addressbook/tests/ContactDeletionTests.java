package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDelation(){

    app.getNavigationHelper().gotoHomePage();
    //Проверка существования контакта. Иначе - создание и удаление
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoAddNewContact();
      app. getContactHelper().createContact(new ContactData("Ivan11","Pomidorov",
              "Minsk, Gagarina 21/14","+375 17 5544120",
              "+375 29 6222552","test8"), true);

    }

    app.getContactHelper().selectContactDelation();
    app.getContactHelper().deleteContact();

  }
}



