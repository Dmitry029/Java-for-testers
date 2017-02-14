package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

/**
 * Created by Администратор on 29.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod  //Перед каждым вызовом   - проверка предусловий i5_m2
  public void ensurePreconditions(){
    app.goTo().groupPage(); // Переход на нужную страницу
    // Проверка существования группыю При отсутствии группы - создание.************************
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    //******************************************************************************************
  }

  @Test
  public void testGroupModification() {

    Set<GroupData> before = app.group().all(); //Подсчет КОЛ-ВА групп до модификации l4_m5
    GroupData modifiedGroup = before.iterator().next();
        GroupData group= new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(group);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());       // Проверка РАЗМЕРА групп до и после модификации

    before.remove(modifiedGroup);
    before.add(group); // та же локальн пер (чтобы не писать два раза)
    Assert.assertEquals(before,after);//сравнение 2-х Списков упроядоченных по собственным правилам
  }


}
