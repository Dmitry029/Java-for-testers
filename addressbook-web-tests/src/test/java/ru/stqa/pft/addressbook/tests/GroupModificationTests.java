package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Администратор on 29.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod  //Перед каждым вызовом   - проверка предусловий i5_m2
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGtoupPage(); // Переход на нужную страницу
    // Проверка существования группыю При отсутствии группы - создание.************************
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test11", null));
    }
    //******************************************************************************************
  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.getGroupHelper().getGroupList(); //Подсчет КОЛ-ВА групп до модификации l4_m5
    int index = before.size() -1;//индекс группы, которую будем модифицировать
    GroupData group= new GroupData(before.get(index).getId(),"test62", "12", "12"); //Локальная переменная заполнения формы

    app.getGroupHelper().modifyGroup(index, group);

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());       // Проверка РАЗМЕРА групп до и после модификации

    before.remove(index);
    before.add(group); // та же локальн пер (чтобы не писать два раза)

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);//сравнение 2-х Списков упроядоченных по собственным правилам
  }


}
