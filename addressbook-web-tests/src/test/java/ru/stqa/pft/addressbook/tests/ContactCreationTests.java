package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {

      List<Object[]> list = new ArrayList<Object[]>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
      String line = reader.readLine();
      while(line != null){
        String[] split = line.split(";");
        list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                  .withHomePhone(split[3]).withMobilePhone(split[4]).withWorkPhone(split[5]).withEmail(split[6])
                  .withEmail2(split[7]).withEmail3(split[8])});
        line = reader.readLine();
      }
      return  list.iterator();
    }

//  list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});

  @Test(dataProvider = "validContacts")                           //    (enabled = false) //выкл теста
  public void testContactCreation(ContactData contact) {
    //Подсчет кол-ва контактов до создания нового**************************************************
    app.goTo().homePage(); //Переход на домашнюю страницу***********************
    Contacts before = (Contacts) app.contact().all(); //before - список объектов*
    app.goTo().addNew();                              // Переход на стр создания контакта
    app.contact().create(contact, true);      //создаем контакт
    assertThat(app.contact().countContact(), equalTo(before.size() + 1));
    Contacts after = (Contacts) app.contact().all();   //after - множество объектов после добавления
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}




 /*ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Pomidorov")
            .withAddress("Minsk, Gagarina 21/14").withHomePhone("+375 17 5544120")
            .withMobilePhone("+375 29 6222552").withWorkPhone("2741523")
            .withEmail("lll@kkk.yy").withEmail2("ooo@kkk.yy").withEmail3("ppp@kkk.yy").withPhoto(photo);*/


  /*@Test  //Вспомогательный тест проверка нахождения рисунка l6_m1
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/images.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }*/


/*File photo = new File("src/test/resources/images.png");//относительный путь к файлу.
    // Относительный по отношению к рабочей директории!! Т к программ на разных комп нах в разных
    // рабочих директориях  -АБСОЛЮТНЫЙ путь указывать нельзя!!!
    list.add(new Object[] {new ContactData().withFirstname("Sasha1").withLastname("Rovny1").withAddress("Gogola 1")
            .withHomePhone("+375 671").withMobilePhone("+375 781").withWorkPhone("+375 001").withEmail("qqq1")
            .withEmail2("www1").withEmail3("eee1").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("Sasha2").withLastname("Rovny2").withAddress("Gogola 2")
            .withHomePhone("+375 672").withMobilePhone("+375 782").withWorkPhone("+375 002").withEmail("qqq2")
            .withEmail2("www2").withEmail3("eee2").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("Sasha3").withLastname("Rovny3").withAddress("Gogola 3")
            .withHomePhone("+375 673").withMobilePhone("+375 783").withWorkPhone("+375 003").withEmail("qqq3")
            .withEmail2("www3").withEmail3("eee3").withPhoto(photo)});*/