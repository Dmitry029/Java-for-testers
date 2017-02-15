package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase{
    @BeforeMethod  //Перед каждым вызовом   - проверка предусловий i5_m2
    public void ensurePreconditions() {
        app.goTo().groupPage();

        // Проверка того, что группа есть, а если нет - создаем ее и удаляем.**********************
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        //******************************************************************************************
    }

    @Test
    public void testGroupDeletion() {

        Groups before = app.group().all(); //Подсчет групп  до удаления
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);// для удаления передаем группу целиком ВЫБРАНА СЛУЧАЙНЫМ ОБРАЗОМ
        Groups after = app.group().all();  //Подсчет групп после удаления
        assertEquals(after.size(), before.size() - 1);  // Проверка кол-ва групп до и после удаления    }
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
