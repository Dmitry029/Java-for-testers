package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Администратор on 29.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //Проверка существования контакта. Иначе - создание и удаление*********************************
    if (app.contact().list().size() == 0){
      app.goTo().addNew();
      app. contact().create(new ContactData("Ivan11","Pomidorov",
              "Minsk, Gagarina 21/14","+375 17 5544120",
              "+375 29 6222552","test8"), true);
    }
    //*********************************************************************************************
  }

  @Test                                     //(enabled = false) выключение теста
  public void testContactDelation(){

    List<ContactData> before = app.contact().list();
    int index = before.size() -1;//индекс контакта, который будем удалять
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1); // Сравнение размеров списка

    before.remove(index);
    Assert.assertEquals(before,after); //Сравнение списков целиком поэлементно
  }


}


//int before = app.getContactHelper(). getContactCount();
//int after = app.getContactHelper(). getContactCount();