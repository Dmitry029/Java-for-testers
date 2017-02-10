package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    //Подсчет кол-ва контактов до создания нового**************************************************
    app.getNavigationHelper().gotoHomePage(); //Переход на домашнюю страницу***********************
    List<ContactData> before = app.getContactHelper().getContactList(); //before - список объектов*
    //блок подсчета окончен************************************************************************
    app.getNavigationHelper().gotoAddNewContact();// Переход на стр создания контакта

    ContactData contact = new ContactData("Sasha1",
            "Pomidorov1", "Minsk, Gagarina 21/14","+375 17 5544120",
            "+375 29 6222552","test2");

    app.getContactHelper().createContact(contact, true); //создаем контакт



    List<ContactData> after = app.getContactHelper().getContactList(); //after - список объектов после добавления
    Assert.assertEquals(after.size(), before.size() + 1);     // Сравнение результатов

    int max =0;
    for (ContactData g : after) {
      if (g.getId() > max){
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact); // та же локальн пер (чтобы не писать два раза)
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//сравнение 2-х МНОЖЕСТВ
  }
}

