package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next(); //элемент для удаления выбирается случайным образом
    app.contact().delete(deletedContact);
    assertThat(app.contact().countContact(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
    }
}




//int before = app.getContactHelper(). getContactCount();
//int after = app.getContactHelper(). getContactCount();
//assertEquals(after.size(), before.size() - 1); проверка размера таким способом заменена в i5_m8
//assertThat(app.group().count(), equalTo(before.size())); l5_m8