package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Администратор on 09.03.2017.
 */
public class FtpHelper {

  private final ApplicationManager app;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager app){
    this.app = app;
    ftp = new FTPClient();
    //при вызове конструктора происходит инициализация - создается FTPClien
    //ftp клиент устанавливает соединение, передает файлы и т д
  }


  // метод загружает новый конфигурационный файл, старый временно переименовывает.
  //Вызывается в самом начале
  public void upload(File file, String target, String backup) throws IOException{
     //для загрузки файла устанавливается соединениее с какким-то сервером см ниже
    ftp.connect(app.getProperty("ftp.host"));
      //выполняется логин
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
      //удаляем предыдущую резервную копию (на всякий случай)
    ftp.deleteFile(backup);
      //переименовываем удаленный файл, делаем резервную копию
    ftp.rename(target, backup);
      //включается пассивный режит передачи данных
    ftp.enterLocalPassiveMode();
      //!!! самая Главная строка!!! Здесь ПЕРЕДАЕТСЯ ФАЙЛ
    ftp.storeFile(target, new FileInputStream(file));
      //разрываем соединение
    ftp.disconnect();
  }

  //восстанавливает старый конфигурвционный файл
  //Вызывается в конце и восстанавливает исходную конфигурацию
  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);//восстановление оригинального файла из резервной копии
    ftp.disconnect();
  }
}
