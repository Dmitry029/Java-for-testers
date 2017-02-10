package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDelation(){

    app.getNavigationHelper().gotoHomePage();
    //Проверка существования контакта. Иначе - создание и удаление*********************************
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoAddNewContact();
      app. getContactHelper().createContact(new ContactData("Ivan11","Pomidorov",
              "Minsk, Gagarina 21/14","+375 17 5544120",
              "+375 29 6222552","test8"), true);
    }
    //*********************************************************************************************
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactDelation(before.size() -1);  // Выбор последнего эл-та (before -1)
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().gotoHomePage();//Возврат на Home page
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before,after); //Сравнение списков
    }
}


//int before = app.getContactHelper(). getContactCount();
//int after = app.getContactHelper(). getContactCount();