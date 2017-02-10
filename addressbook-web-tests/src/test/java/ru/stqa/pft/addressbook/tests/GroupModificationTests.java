package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Администратор on 29.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGtoupPage();

    // Проверка существования группыю При отсутствии группы - создание.************************
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test11", null));
    }
    //******************************************************************************************
    List<GroupData> before = app.getGroupHelper().getGroupList(); //Подсчет до модификации l4_m5
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group= new GroupData(before.get(before.size() -1).getId(),
            "test62", "12", "12"); //Локальная переменная заполнения формы
    app.getGroupHelper().fillGroupForm(group);// локальная пер заполняет форму
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());       // Проверка размера групп до и после модификации

    before.remove(before.size() - 1);
    before.add(group); // та же локальн пер (чтобы не писать два раза)
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//сравнение 2-х МНОЖЕСТВ построенных из списков
  }
}
