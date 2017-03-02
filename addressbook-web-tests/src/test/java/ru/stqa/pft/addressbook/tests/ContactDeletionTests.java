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
    //Проверка существования контакта. Иначе - создание и далее удаление***************************
    app.goTo().homePage();
      if (app.db().contacts().size() == 0){
      app.goTo().addNew();
      app. contact().create(new ContactData().withFirstname("Ivan11").withLastname("Pomidorov")
              .withAddress("Minsk, Gagarina 21/14").withHomePhone("+375 17 5544120")
              .withMobilePhone("+375 29 6222552").withWorkPhone("4576895")
              .withEmail("lkj@yui.io").withEmail2("ooo@iii.uu").withEmail3("yyy@uuu.gg")
              , true);
    }
    //*********************************************************************************************
  }

  @Test
  public void testContactDelation(){

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next(); //элемент для удаления выбирается случайным образом
    app.contact().delete(deletedContact);
    assertThat(app.contact().countContact(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));

    verifyContactListInUI();//проверка данных из БД и UI
    }
}




//int before = app.getContactHelper(). getContactCount();
//int after = app.getContactHelper(). getContactCount();
//assertEquals(after.size(), before.size() - 1); проверка размера таким способом заменена в i5_m8
//assertThat(app.group().count(), equalTo(before.size())); l5_m8