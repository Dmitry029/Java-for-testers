package ru.stqa.pft.mantis.appmanager;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.TestBase;

/**
 * Created by Администратор on 08.03.2017.
 */
public class RegistrationTests extends TestBase{

  @Test
  public void testRegictration(){
    app.registration().start("user1", "user1@kkk.lll");
  }
}
