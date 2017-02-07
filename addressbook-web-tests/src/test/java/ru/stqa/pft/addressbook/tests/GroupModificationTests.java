package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Администратор on 29.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){

    app.getNavigationHelper().gotoGtoupPage();
    int before = app.getGroupHelper().getGroupCount();    //Подсчет групп до модификации
    // Проверка существования группыю При отсутствии группы - создание.
    if (! app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("test1", "test11", null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test62", "12", "12"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();    //Подсчет групп после модификации
    Assert.assertEquals(after, before);                  // Проверка кол-ва групп до и после модификации

  }
}
