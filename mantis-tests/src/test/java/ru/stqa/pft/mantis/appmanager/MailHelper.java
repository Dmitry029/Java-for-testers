
package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class MailHelper {
  private ApplicationManager app;
  private final Wiser wiser;

  //При инициализации создается объект типа Wiser() - это почтовый сервер
  //пока не запущен. Запускается методом start()
  public MailHelper(ApplicationManager app){
    this.app = app;
    wiser = new Wiser();
  }

  //Добавляем метод ожидания прихода почты - waitForMail(кол-во писем котор должны прийти, время ожидания прихода писем)
  public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException, IOException {

    //Запоминаем текщее время
    long start = System.currentTimeMillis();

    //Проверяем, что текущее новое текущее время не превышает момент старта + таймаут. т е продолжаем
    //ждать почту. Цекл продолжается пока не истечет время ожидания или пока не придет достаточно писем
    while (System.currentTimeMillis() < start + timeout) {

      //Если  почты пришло достаточно - то ВСЕ! -  ожидание можно прекращать
      if (wiser.getMessages().size() >= count){
        //Когда почты пришло достаточно много, проводим переобразованные реальные объекты в модельные
        //С ними удобно работать они не зависят от деталей реализации.
        //Почтовый список -> превращаем в поток -> ко всем эл-там потокаприменям функцию toModelMail(m)->
        // из получившиехся новых объектов формируем список
        return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
      }

      //Если почты пришло мало, то проскакиваем проверку и попадаем сюда
      try {
        Thread.sleep(1000); //подождать 1000 милесекунд
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
    //исключение - если почта не пришла (или пришло слишком мало (меньше чем параметр count)
    throw new Error("No mail :(");
  }

  //Метод переобразования реальных почтовых сообщений в модельные
  public static MailMessage toModelMail(WiserMessage m){
    try {
      MimeMessage mm = m.getMimeMessage();

      //Берем реальный объект, берем спосок получателей и оставляем только первого из них
      // мы точно знаем -  он единственный
      return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
      //это - mm.getAllRecipients()[0].toString() получатель письма. Содержание письма - mm.getContent()
      // в нашем случае - просто строка. Мы пеобр его в строку и передаем в модельный объект MailMessage

      // оставшаяся часть - перехват ошибок и возврат null
    }catch (MessagingException e){
      e.printStackTrace();
      return null;
    }catch (IOException e){
      e.printStackTrace();
      return null;
    }
  }

  public void start(){
    wiser.start();
  }

  public void stop(){
    wiser.stop();
  }
}