package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreationTests() {
    app.getNavigationHelper().gotoGtoupPage();     //Переход на стр Groups
    List<GroupData> before = app. getGroupHelper(). getGroupList(); //Подсчет групп до добавления
    app.getGroupHelper().createGroup(new GroupData("test8", null, null));
    List<GroupData> after = app. getGroupHelper(). getGroupList();  //Подсчет групп после добавления
    Assert.assertEquals(after.size(), before.size() + 1);   // Проверка кол-ва групп до и после создания
  }

}



// Вместо них createGroup
// app.getGroupHelper().initGroupCreation();
// app.getGroupHelper().fillGroupForm(new GroupData("test9", null, null));
// app.getGroupHelper().submitGroupCreation();
// app.getGroupHelper().returnToGroupPage();