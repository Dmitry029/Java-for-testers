package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/images.png");//относительный путь к файлу.
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
            .withEmail2("www3").withEmail3("eee3").withPhoto(photo)});
    return  list.iterator();
  }


  @Test(dataProvider = "validContacts")                           //    (enabled = false) //выкл теста
  public void testContactCreation(ContactData contact) {
    //Подсчет кол-ва контактов до создания нового**************************************************
    app.goTo().homePage(); //Переход на домашнюю страницу***********************
    Contacts before = (Contacts) app.contact().all(); //before - список объектов*
    app.goTo().addNew();// Переход на стр создания контакта
    app.contact().create(contact, true); //создаем контакт
    assertThat(app.contact().countContact(), equalTo(before.size() + 1));
    Contacts after = (Contacts) app.contact().all(); //after - множество объектов после добавления
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}

 /* public void testGroupCreationTests(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all(); //Подсчет групп до добавления
    app.group().create(group);//создаем группу
    assertThat(app.group().count(), equalTo(before.size() + 1));   // Проверка кол-ва групп до и после создания
    Groups after = app.group().all();  //Подсчет групп после добавления
    assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    //в сравнении учасствует копия объекта
  }*/


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


