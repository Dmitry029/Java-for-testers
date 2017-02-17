package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
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
              .withGroup("test2"), true);
    }
    //*********************************************************************************************
  }
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
