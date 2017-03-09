package ru.stqa.pft.mantis.appmanager;

        import org.apache.http.NameValuePair;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.impl.client.LaxRedirectStrategy;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.util.EntityUtils;
        import ru.stqa.pft.mantis.appmanager.ApplicationManager;
        import org.apache.http.impl.client.CloseableHttpClient;
        import sun.net.www.http.HttpClient;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;


/**
 * Created by Администратор on 08.03.2017.
 */
public class HttpSession{
  private CloseableHttpClient httpclient;
  private ApplicationManager app;//Запоминается ссылка на ApplicationManager

  //Передается ссылка на ApplicationManager
  public HttpSession(ApplicationManager app) {
    this.app = app;
      //**создание нового клиента, новая сессия для работы по протоколу http. Объект который будет
      // отправлять запросы на сервер
    httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }


      //метод определяющий логин l8_m3
  public boolean login(String username, String password) throws IOException {
      //**в строке ниже создается запрорс(на адрес login) типа post для того, чтоб залогинится
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php"); //адрес куда отправл логин
      //**ниже - несколько строк создают параметры запроса
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));

      //**все упаковывается и помещается в запрос post setEntity
    post.setEntity(new UrlEncodedFormEntity(params));//запрос сформирован и готов к отправке
    CloseableHttpResponse response = httpclient.execute(post);//происходит отправка (после знака =)
      //**response - строка выше - это ответ сервера. ниже он анализируется. Получаем его текст
    String body = geTextFrom(response);
      //**проверка - действительно ли пользователь успешно вошел. Есть ли в коде страници строка в " "
      // где находится имя пользователя, который вошел в с-му
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }

  private String geTextFrom(CloseableHttpResponse response) throws  IOException{
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }

  }
  //**метод определяющий какой пользователь сейчас вошел в систему
  public boolean isLoggedInAs(String username) throws IOException {
      //выполняется запрос на адрес index  т е просто зайти на главную страницу. Запрос типа get
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");//формируется запрос
    CloseableHttpResponse response = httpclient.execute(get); //отправляется запрос (после =)
      //**response строка выше - это ответ сервера. ниже он анализируется. Получаем его текст
    String body = geTextFrom(response);
      //проверяем действительно ли в тексте стр содержится нужный фрагмент т е залогинен именно тот пользователь
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }
}
