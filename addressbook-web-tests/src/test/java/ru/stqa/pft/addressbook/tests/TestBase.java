package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.appmanager.ApplicationMenager;

/**
 * Created by Администратор on 28.01.2017.
 */
public class TestBase {

  protected final ApplicationMenager app = new ApplicationMenager(BrowserType.IE);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }




}
