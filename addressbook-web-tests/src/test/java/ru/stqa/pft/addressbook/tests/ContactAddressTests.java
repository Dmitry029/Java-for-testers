package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Администратор on 17.02.2017.
 */
public class ContactAddressTests extends TestBase  {

  @BeforeMethod
  public void ensurePreconditions() {
    //Проверка существования контакта. Если контакта нет-создание*********************************
    app.goTo().homePage();
    if (app.contact().all().size() == 0){
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
  public void testContactEmail(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat (cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
  }

  //Чистка строки от пробелов
  public static String cleaned (String address){
    return address.replaceAll("\\s","");
  }
}
