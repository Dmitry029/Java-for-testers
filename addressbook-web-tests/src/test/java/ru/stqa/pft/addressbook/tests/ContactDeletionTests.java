package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //Проверка существования контакта. Иначе - создание и удаление*********************************
    if (app.contact().all().size() == 0){
      app.goTo().addNew();
      app. contact().create(new ContactData().withFirstname("Ivan11").withLastname("Pomidorov")
              .withAddress("Minsk, Gagarina 21/14").withHomephone("+375 17 5544120")
              .withMobilephone("+375 29 6222552").withGroup("test8"), true);
    }
    //*********************************************************************************************
  }

  @Test
  public void testContactDelation(){

    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next(); //элемент для удаления выбирается случайным образом
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1); // Сравнение размеров списка

    before.remove(deletedContact);
    Assert.assertEquals(before,after); //Сравнение массивов целиком поэлементно
  }


}


//int before = app.getContactHelper(). getContactCount();
//int after = app.getContactHelper(). getContactCount();