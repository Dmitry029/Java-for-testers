package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{
    @BeforeMethod  //Перед каждым вызовом   - проверка предусловий i5_m2
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGtoupPage();

        // Проверка того, что группа есть, а если нет - создаем ее и удаляем.**********************
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test8", null, null));
        }
        //******************************************************************************************
    }

    @Test
    public void testGroupDeletion() {

        List<GroupData> before = app. getGroupHelper(). getGroupList(); //Подсчет групп  до удаления
        int index = before.size() -1;//индекс группы, которую будем удалять
        app.getGroupHelper().selectGroup(index);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app. getGroupHelper(). getGroupList();  //Подсчет групп после удаления
        Assert.assertEquals(after.size(), before.size() - 1);  // Проверка кол-ва групп до и после удаления    }

        before.remove(index);
        Assert.assertEquals(before, after);  //сравниваем по элементно списки
     }
}
