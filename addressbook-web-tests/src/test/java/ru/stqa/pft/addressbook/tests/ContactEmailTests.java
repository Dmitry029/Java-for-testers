package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
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

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s)-> !s.equals(""))
              .map(ContactEmailTests::cleaned)
              .collect(Collectors.joining("\n"));
  }

  //Чистка строки от пробелов
  public static String cleaned (String email){
    return email.replaceAll("\\s","");
  }

  
}



//assertThat(contact.getAddress(), CoreMatchers.equalTo(cleaned2(contactInforFromEditForm.getAddress())));

/*  assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInforFromEditForm)));
  }

  // Метод отбрасывает ненужные элементы - пустые строки и склеивает результат l5_m11******************************
  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));*/