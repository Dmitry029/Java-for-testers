package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Администратор on 09.03.2017.
 */
public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd; //т о переменная wd доступна только черз getDriver()


    private String browser;
    private RegistrationHelper registrationHelper;
  private FtpHelper ftp;

  public ApplicationManager(String browser)  {
      this.browser = browser;
      properties = new Properties();
    }

    //При вызовк init() загружается только конфигурационный файл. Драйвер инициализир только после
    // обращения к нему - вызов getDriver()
    public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }


    // если браузер (wd) был инициализирован - его остановить. Иначе - ничего не делать.
    public void stop() {
      if (wd != null){
        wd.quit();}
    }

    //инициализация помощника работы с сетьевым протоколом (в обход браузера)
    public HttpSession newSession(){
      return new HttpSession(this);
    }

    public String getProperty(String key) {
      return properties.getProperty(key);
    }


    //реализована ленивая инициализация
    public RegistrationHelper registration() {
      if(registrationHelper == null){
        return new RegistrationHelper(this);// выполняется инициализация если еще не инициализирован
      }
      return registrationHelper;
    }

    public FtpHelper ftp(){
      if(ftp == null){
        ftp = new FtpHelper(this);
      }
      return ftp;
    }



    //лениваяы инициализация драйвера l8_m4. Браузер будет инициализироваться только тогда когда
    //будет вызван метод getDriver(). Если драйвер проинициализирован - ничего делать не надо
    public WebDriver getDriver() {
      if(wd == null) {
        if (Objects.equals(browser, BrowserType.FIREFOX)) {
          wd = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
          wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.IE)) {
          wd = new InternetExplorerDriver();
          wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //5-задержка для появления элемента
        wd.get(properties.getProperty("web.baseUrl"));
      }
      return wd;
    }
}


