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

    GroupData group = new GroupData("test2", null, null);

    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app. getGroupHelper(). getGroupList();  //Подсчет групп после добавления
    Assert.assertEquals(after.size(), before.size() + 1);   // Проверка кол-ва групп до и после создания

    //ищем элемент с максимальный идентификатор. это - идентификатор новой группы
    group.setId(after.stream().max((o1,o2)-> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//сравнение 2-х МНОЖЕСТВ
  }

}



