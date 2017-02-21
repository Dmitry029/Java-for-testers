package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validGroups() {
  List<Object[]> list = new ArrayList<Object[]>();
  list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
  list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
  list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
  return  list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreationTests(GroupData group) {
   // GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);//данные для создания группы
    app.goTo().groupPage();
    Groups before = app.group().all(); //Подсчет групп до добавления
    app.group().create(group);//создаем группу
    assertThat(app.group().count(), equalTo(before.size() + 1));   // Проверка кол-ва групп до и после создания
    Groups after = app.group().all();  //Подсчет групп после добавления
    assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    //в сравнении учасствует копия объекта
    }
  @Test
  public void testBadGroupCreationTests() {
    app.goTo().groupPage();
    Groups before = app.group().all(); //Подсчет групп до добавления
    GroupData group = new GroupData().withName("test2'");//данные для создания группы !!!! некорректные данные
    app.group().create(group);//создаем группу
    assertThat(app.group().count(), equalTo(before.size()));   // Проверка кол-ва групп до и после создания
    Groups after = app.group().all();  //Подсчет групп после добавления
    assertThat(after, equalTo(before));

  }
}




//ищем элемент с максимальный идентификатор. это - идентификатор новой группы l4_m9
//    group.setId(after.stream().max((o1,o2)-> Integer.compare(o1.getId(), o2.getId())).get().getId());
//    before.add(group);
//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));//сравнение 2-х МНОЖЕСТВ

// закоментирован l5_m5
/*//упорядочиваем списки (мах идентификатор искать не будем  l4_m10
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);*/