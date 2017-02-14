package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();

    if (app.contact().list().size() == 0){
      // Проверка того, что группа есть, а если нет - она создается **********
      app.goTo().groupPage();
      if (app.group().list().size() == 0){
        app.group().create(new GroupData().withName("test8"));
      }
      // блок проверки наличия группы окончен ********************************

      //В случае отсутствия контакта, он создается. ***************************
      app.goTo().addNew();
      app.contact().create(new ContactData().withFirstname("Ivan100").withLastname("Pomidorov")
                .withAddress("Minsk, Gagarina 21/14").withHomephone("+375 17 5544120")
                .withMobilephone("+375 29 6222552").withGroup("test8"), true);
    }
    //*************************************************************************
  }


  @Test                             //(enabled = false) выкл теста

  public void testContactModification(){

    List<ContactData> before = app.contact().list(); // before - список контактов
    int index = before.size() -1; //индекс контакта, который будем модифицировать
    app.contact().initModificationContact(index); //выбираем последний элемент

    // новая локальная переменная contact. заполняет контакт. l4_m7
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Sasha2")
            .withLastname("Pomidorov1").withAddress("Minsk, Gagarina 21/14").withHomephone("+375 17 5544120")
            .withMobilephone("+375 29 6222552");//group null

    app.contact().modify(contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()); //проверка размера контакта до и после модиф

    before.remove(index);
    before.add(contact); // та же локальн пер (чтобы не писать два раза)

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);//сравнение 2-х Спиcков упроядоченных по собственным правилам

    }


}


