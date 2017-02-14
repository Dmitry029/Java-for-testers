package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;


import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationMenager;

/**
 * Created by Администратор on 28.01.2017.
 */
public class TestBase {

  protected static final ApplicationMenager app = new ApplicationMenager(BrowserType.FIREFOX);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }
}
