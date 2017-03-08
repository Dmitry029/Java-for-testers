package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by Администратор on 08.03.2017.
 */
public class RegistrationHelper {
  private final  ApplicationMenager app;
  private WebDriver wd; //запуск браузера

  public RegistrationHelper(ApplicationMenager app) {
    this.app = app;
    wd = app.getDriver(); //ленивая инициализация/ Метод getDriver
    //будет инициализировать браузер
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
