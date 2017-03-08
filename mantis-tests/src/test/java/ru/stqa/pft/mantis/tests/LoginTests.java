package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Администратор on 08.03.2017.
 */
public class LoginTests extends TestBase{

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
      //проверка того что пользователь залогинился - на странице почвился
      //нужный текст
    assertTrue(session.login("administrator", "root"));
      //вторая проверка -
    assertTrue(session.isLoggedInAs("administrator"));
  }

}
