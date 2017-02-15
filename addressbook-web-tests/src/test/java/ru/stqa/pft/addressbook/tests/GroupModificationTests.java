package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import sun.font.CoreMetrics;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

    Groups before = app.group().all(); //Подсчет КОЛ-ВА групп до модификации l4_m5
    GroupData modifiedGroup = before.iterator().next();
        GroupData group= new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size());       // Проверка РАЗМЕРА групп до и после модификации
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
