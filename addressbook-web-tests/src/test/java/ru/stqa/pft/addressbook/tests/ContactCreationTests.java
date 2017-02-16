package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test                                           //(enabled = false) выкл теста
  public void testContactCreation() {
    //Подсчет кол-ва контактов до создания нового**************************************************
    app.goTo().homePage(); //Переход на домашнюю страницу***********************
    Contacts before = (Contacts) app.contact().all(); //before - список объектов*
    app.goTo().addNew();// Переход на стр создания контакта

    ContactData contact = new ContactData().withFirstname("Sasha1").withLastname("Pomidorov1")
            .withAddress("Minsk, Gagarina 21/14").withHomephone("+375 17 5544120")
            .withMobilephone("+375 29 6222552").withGroup("test2");

    app.contact().create(contact, true); //создаем контакт
    assertThat(app.contact().countContact(), equalTo(before.size() + 1));
    Contacts after = (Contacts) app.contact().all(); //after - множество объектов после добавления
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}




/*ищем элемент с максимальный идентификатор. это - идентификатор новой группы l4_m9
* contact.setId(after.stream().max((o1,o2)-> Integer.compare(o1.getId(), o2.getId())).get().getId());
* before.add(contact); // та же локальн пер (чтобы не писать два раза)
* Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//сравнение 2-х МНОЖЕСТВ */

// Удалена сортировка списков l5_m5
 /* Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
            after.sort(byId);*/
//assertThat(after.size(), equalTo(before.size() + 1));     // Сравнение результатов
