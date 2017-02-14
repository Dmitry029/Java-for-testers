package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{
    @BeforeMethod  //Перед каждым вызовом   - проверка предусловий i5_m2
    public void ensurePreconditions() {
        app.goTo().groupPage();

        // Проверка того, что группа есть, а если нет - создаем ее и удаляем.**********************
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        //******************************************************************************************
    }

    @Test
    public void testGroupDeletion() {

        List<GroupData> before = app.group().list(); //Подсчет групп  до удаления
        int index = before.size() -1;//индекс группы, которую будем удалять
        app.group().delete(index);
        List<GroupData> after = app.group().list();  //Подсчет групп после удаления
        Assert.assertEquals(after.size(), before.size() - 1);  // Проверка кол-ва групп до и после удаления    }

        before.remove(index);
        Assert.assertEquals(before, after);  //сравниваем по элементно списки
     }
}
