package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();

    if (app.contact().all().size() == 0){
      // Проверка того, что группа есть, а если нет - она создается **********
      app.goTo().groupPage();
      if (app.group().all().size() == 0){
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

    Set<ContactData> before = app.contact().all(); // before - множество контактов
    ContactData modifiedContact = before.iterator().next(); //элемент для удаления выбирается случайным образом
    // новая локальная переменная contact. заполняет контакт. l4_m7
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Sasha2")
            .withLastname("Pomidorov1").withAddress("Minsk, Gagarina 21/14").withHomephone("+375 17 5544120")
            .withMobilephone("+375 29 6222552").withGroup(null);

    app.contact().modify(contact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()); //проверка размера множества контакта до и после модиф

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before,after);//сравнение 2-х множеств
    }
}


