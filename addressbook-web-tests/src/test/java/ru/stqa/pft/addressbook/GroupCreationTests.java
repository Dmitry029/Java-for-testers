package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreationTests() {

    gotoGtoupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test2", "test3", "test4"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
