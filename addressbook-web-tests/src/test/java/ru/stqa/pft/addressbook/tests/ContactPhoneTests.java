package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Администратор on 17.02.2017.
 */
public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next(); //загрузка инф с Home page
    ContactData contactInforFromEditForm = app.contact().infoFromEditForm(contact);//загрузка инф с формы редактирования

    assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInforFromEditForm)));
  }

  // Метод отбрасывает ненужные элементы - пустые строки и склеивает результат l5_m11******************************
  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s)-> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  //Чистка строки от пробелов, минусов - и скобок()
  public static String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");//***\\s это пробел
  }
}
