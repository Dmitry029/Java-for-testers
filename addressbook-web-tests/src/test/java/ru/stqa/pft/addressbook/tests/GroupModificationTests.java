package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Администратор on 29.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){

    app.getNavigationHelper().gotoGtoupPage();

    // Проверка существования группыю При отсутствии группы - создание.
    if (! app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("test1", "test11", null));
    }

    List<GroupData> before = app. getGroupHelper(). getGroupList(); //Подсчет групп до модификации l4_m5
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test62", "12", "12"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app. getGroupHelper(). getGroupList();  //Подсчет групп после модификации
    Assert.assertEquals(after.size(), before.size());                  // Проверка кол-ва групп до и после модификации

  }
}
