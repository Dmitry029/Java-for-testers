package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreationTests() {
    app.getNavigationHelper().gotoGtoupPage();
    int before = app.getGroupHelper().getGroupCount();   //Подсчет групп до добавления
    app.getGroupHelper().createGroup(new GroupData("test8", null, null));
    int after = app.getGroupHelper().getGroupCount();    //Подсчет групп после добавления
    Assert.assertEquals(after, before + 1);     // Проверка кол-ва групп до и после создания
  }

}



// Вместо них createGroup
// app.getGroupHelper().initGroupCreation();
// app.getGroupHelper().fillGroupForm(new GroupData("test9", null, null));
// app.getGroupHelper().submitGroupCreation();
// app.getGroupHelper().returnToGroupPage();