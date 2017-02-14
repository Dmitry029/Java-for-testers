package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()){
      // Проверка того, что группа есть, а если нет - она создается **********
      app.getNavigationHelper().gotoGtoupPage();
      if (! app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("test8", null, null));
      }
      // блок проверки наличия группы окончен ********************************

      //В случае отсутствия контакта, он создается. ***************************
      app.getNavigationHelper().gotoAddNewContact();
      app.getContactHelper().createContact(new ContactData("Ivan100","Pomidorov",
              "Minsk, Gagarina 21/14","+375 17 5544120", "+375 29 6222552",
              "test8"), true);
    }
    //*************************************************************************
  }


  @Test                             //(enabled = false) выкл теста

  public void testContactModification(){

    List<ContactData> before = app.getContactHelper().getContactList(); // before - список контактов
    int index = before.size() -1; //индекс контакта, который будем модифицировать
    app.getContactHelper().initModificationContact(index); //выбираем последний элемент

    // новая локальная переменная contact. заполняет контакт. l4_m7
    ContactData contact = new ContactData(before.get(index).getId(),"Sasha2",
            "Pomidorov1", "Minsk, Gagarina 21/14","+375 17 5544120",
            "+375 29 6222552",null);

    app.getContactHelper().modifyContact(contact);

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()); //проверка размера контакта до и после модиф

    before.remove(index);
    before.add(contact); // та же локальн пер (чтобы не писать два раза)

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);//сравнение 2-х Спиcков упроядоченных по собственным правилам

    }


}


