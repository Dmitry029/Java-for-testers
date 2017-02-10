package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreationTests() {
    app.getNavigationHelper().gotoGtoupPage();     //Переход на стр Groups
    List<GroupData> before = app. getGroupHelper(). getGroupList(); //Подсчет групп до добавления

    GroupData group = new GroupData("test8", null, null);

    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app. getGroupHelper(). getGroupList();  //Подсчет групп после добавления
    Assert.assertEquals(after.size(), before.size() + 1);   // Проверка кол-ва групп до и после создания

    //ищем максимальный идентификатор. Он - идентификатор новой группы
    int max =0;
    for (GroupData g : after) {
      if (g.getId() > max){
      max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//сравнение 2-х МНОЖЕСТВ
  }

}



