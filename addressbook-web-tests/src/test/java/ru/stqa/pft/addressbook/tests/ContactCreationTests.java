package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    //Подсчет кол-ва контактов до создания нового
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    //блок подсчета окончен
    app.getNavigationHelper().gotoAddNewContact();       // Переход на стр создания контакта
    app.getContactHelper().createContact(new ContactData("Ivan14", "Pomidorov",
            "Minsk, Gagarina 21/14", "+375 17 5544120",
            "+375 29 6222552", "test8"), true);
    int after = app.getContactHelper().getContactCount(); //Подсчет кол-ва контактов после создания
    Assert.assertEquals(after, before + 1);      // Сравнение результатов до и после создания
  }
}


