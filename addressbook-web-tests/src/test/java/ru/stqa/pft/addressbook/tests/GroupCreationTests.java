package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreationTests() {

    app.getNavigationHelper().gotoGtoupPage();
    app.getGroupHelper().createGroup(new GroupData("test9", null, null));

   // Вместо них createGroup
   // app.getGroupHelper().initGroupCreation();
   // app.getGroupHelper().fillGroupForm(new GroupData("test9", null, null));
   // app.getGroupHelper().submitGroupCreation();
   // app.getGroupHelper().returnToGroupPage();
  }

}
