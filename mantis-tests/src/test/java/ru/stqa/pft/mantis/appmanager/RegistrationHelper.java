package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {

    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
      //Заполняем в форме регистрации поля Пользователь первый параметр - локатор,
      // второй - текст который надо ввести
    type(By.name("username"), username);
      //Заполняем поле Email
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Зарегистрироваться']"));
      //после этого на указанный адрес эл почты отправляется письмо
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
