package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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

    before.add(contact); // та же локальн пер (чтобы не писать два раза)

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);//сравнение 2-х Спиcков упроядоченных по собственным правилам
  }
}

//ищем элемент с максимальный идентификатор. это - идентификатор новой группы l4_m9
// contact.setId(after.stream().max((o1,o2)-> Integer.compare(o1.getId(), o2.getId())).get().getId());
// before.add(contact); // та же локальн пер (чтобы не писать два раза)
// Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//сравнение 2-х МНОЖЕСТВ