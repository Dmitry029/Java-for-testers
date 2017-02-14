package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreationTests() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all(); //Подсчет групп до добавления
    GroupData group = new GroupData().withName("test2");//данные для создания группы
    app.group().create(group);//создаем группу
    Set<GroupData> after = app.group().all();  //Подсчет групп после добавления
    assertThat(after.size(), equalTo(before.size() + 1));   // Проверка кол-ва групп до и после создания

    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());//находим макс идентификатор среди групп
    before.add(group);
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