package ru.stqa.pft.mantis.appmanager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.tests.TestBase;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Администратор on 08.03.2017.
 */
public class RegistrationTests extends TestBase {

    //для каждого тестового метода запускаем заново почтовый сервер
    // т о старая почта гарантированно пропадет нет влияния тестов друг на друга
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testRegictration() throws IOException, MessagingException {
    String email = "user1@locahost.localdom";
    String user = "user1";
    String password = "password";

    app.registration().start(user, email);

      //ожидание почты. 2 письма 10 сек
    List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);

      //метод извлекает ссылку. Передаем - список писем и адрес. Присваеваем полученную ссылку
      // в локальную переменную
    String confirmationLink = findConfirmationLink(mailMessages, email);

    app.registration().finish(confirmationLink, password);
      //проверка
    assertTrue(app.newSession().login(user, password));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      //ищем письмо пришедшее по нужному адресу, выбираем первое findFirst()
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      //извлекаем из текста сообщения ссылку с пом ощью регулярных выражений. вначале ищем http://
      //затем идут непробельные символы nonSpace() один или более. Результат - объект VerbalExpression
      //внутри него находится построенное сложное регулярное выражение
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      //применяем регулярное выражение к тексту нашего письма и возврашаем тот кусок текста,
      // который соотверствует построенному регулярному выражению
    return regex.getText(mailMessage.text);


  }



  @AfterMethod (alwaysRun = true)
  public void StopMailServer() {
    app.mail().stop();
  }
}