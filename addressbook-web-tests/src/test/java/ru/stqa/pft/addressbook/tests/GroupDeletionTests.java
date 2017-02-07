package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{


    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGtoupPage();
        int before = app.getGroupHelper().getGroupCount();   //Подсчет групп до удаления
        // Проверка того, что группа есть, а если нет - создаем ее и удаляем.
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test8", null, null));
        }

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();    //Подсчет групп после удаления
        Assert.assertEquals(after, before - 1);     // Проверка кол-ва групп до и после удаления    }
    }
}
