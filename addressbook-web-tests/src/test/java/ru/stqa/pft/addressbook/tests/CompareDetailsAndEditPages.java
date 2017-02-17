package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Администратор on 17.02.2017.
 */
public class CompareDetailsAndEditPages extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    //Проверка существования контакта. Если контакта нет-создание*********************************
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Ivan11").withLastname("Pomidorov")
              .withAddress("Minsk, Gagarina 21/14").withHomePhone("+375 17 5544120")
              .withMobilePhone("+375 29 6222552").withWorkPhone("4576895")
              .withEmail("lkj@yui.io").withEmail2("ooo@iii.uu").withEmail3("yyy@uuu.gg")
              .withGroup("test2"), true);
    }
    //*********************************************************************************************
  }
  @Test
  public void testCompareDetailsAndEditPages() {
     app.goTo().homePage();
     ContactData contact = app.contact().all().iterator().next(); //загрузка множества контактов и выбор одного из них случайным образом
     ContactData contactInforFromEditForm = app.contact().infoFromEditForm(contact);


  }
}

//assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInforFromEditForm)));