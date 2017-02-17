package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Администратор on 17.02.2017.
 */
public class ContactEmailTests extends TestBase{

  @Test
  public void testContactEmail(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getEmail(), equalTo(cleaned(contactInfoFromEditForm.getEmail())));
  }

  //Чистка строки от пробелов
  public static String cleaned (String email){
    return email.replaceAll("\\s","");
  }
}


//assertThat(contact.getEmail(), CoreMatchers.equalTo(cleaned2(contactInforFromEditForm.getEmail())));
//assertThat(contact.getAddress(), CoreMatchers.equalTo(cleaned2(contactInforFromEditForm.getAddress())));