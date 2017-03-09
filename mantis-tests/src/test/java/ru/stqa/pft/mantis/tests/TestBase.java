package ru.stqa.pft.mantis.tests;


import org.openqa.selenium.remote.BrowserType;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


/**
 * Created by Администратор on 07.03.2017.
 */
public class TestBase {


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser"
          , BrowserType.FIREFOX)); //дефолтный браузер CHROME

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();//проинициализировали applicationManager
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php",
            "config_inc.php.back");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.back", "config_inc.php");//восстанавливаем файл конфигурации
    app.stop();
  }

}
