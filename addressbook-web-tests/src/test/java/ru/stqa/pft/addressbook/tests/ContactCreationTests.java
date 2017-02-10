package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    //Подсчет кол-ва контактов до создания нового**************************************************
    app.getNavigationHelper().gotoHomePage(); //Переход на домашнюю страницу***********************
    List<ContactData> before = app.getContactHelper().getContactList(); //before - список объектов*
    //блок подсчета окончен************************************************************************
    app.getNavigationHelper().gotoAddNewContact();       // Переход на стр создания контакта
    app.getContactHelper().createContact(new ContactData("Ivan15", "Pomidorov",
            "Minsk, Gagarina 21/14", "+375 17 5544120",
            "+375 29 6222552", "test8"), true);
    List<ContactData> after = app.getContactHelper().getContactList(); //after - список объектов после добавления
    Assert.assertEquals(after.size(), before.size() + 1);      // Сравнение результатов до и после создания
  }
}

//int after = app.getContactHelper().getContactCount(); //Подсчет кол-ва контактов после создания
//int before = app.getContactHelper().getContactCount(); // старая реализация