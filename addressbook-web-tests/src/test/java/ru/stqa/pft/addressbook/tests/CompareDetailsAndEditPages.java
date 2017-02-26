package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static com.sun.xml.internal.bind.WhiteSpaceProcessor.replace;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    ContactData contactInforFromDetailesForm = app.contact().infoFromDetailesForm(contact);

    System.out.println("Ниже - почищенная инфа со страницы с подробной инф о контакте");
    System.out.println(cleaned1(contactInforFromDetailesForm.getAllInformation()));
    System.out.println(mergeDadaFromEditPage(contactInforFromEditForm));
    System.out.println("Выше - почищенная инфа со страницы формы редактирования");

    assertThat(cleaned1(contactInforFromDetailesForm.getAllInformation()),equalTo
            (mergeDadaFromEditPage(contactInforFromEditForm)));
  }

  private String mergeDadaFromEditPage(ContactData contact) {

    String H = "H:";
    String M = "M:";
    String W = "W:";

    if(contact.getHomePhone().equals(""))  H = "";
    if(contact.getMobilePhone().equals(""))  M = "";
    if(contact.getWorkPhone().equals(""))  W = "";

    return Arrays.asList(contact.getFirstname(),contact.getLastname(),contact.getAddress(),
            (H + contact.getHomePhone()), (M + contact.getMobilePhone()),(W +  contact.getWorkPhone()),
            contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)-> ! s.equals("")) //выбрасываем пустые элементы из строки
            .map(CompareDetailsAndEditPages::cleaned2)
            .collect(Collectors.joining(""));
  }

  private  static String cleaned1 (String information){
    return information.replaceAll("\\s","");
  }

  private  static String cleaned2 (String phone){
    return phone.replaceAll("\\s","").replaceAll("[()]","");     //***\\s это пробел и др
  }
}





