package ru.stqa.pft.addressbook.tests;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {

    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }

      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {

    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod  //Перед каждым вызовом   - проверка предусловий i5_m2
  public void ensurePreconditions() {
    app.goTo().groupPage();

    // Проверка того, что группа есть, а если нет - создаем ее и удаляем.**********************
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    //******************************************************************************************
  }

  @Test(dataProvider = "validContactsFromJson")                           //    (enabled = false) //выкл теста
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/images.png");//относительный путь к файлу.
    app.goTo().homePage();
    Contacts before = app.db().contacts(); // before - множество контактов взято из базы l7_m4
    app.goTo().addNew();
    app.contact().create(contact.inGroup(groups.iterator().next()), true);      //создаем контакт
    assertThat(app.contact().countContact(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();   //after - множество объектов после добавления взято из базы l7_m4
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();//проверка данных из БД и UI
  }
}




   /*@Test  //Вспомогательный тест проверка нахождения рисунка l6_m1
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/images.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }*/


//File photo = new File("src/test/resources/images.png");//относительный путь к файлу.
    // Относительный по отношению к рабочей директории!! Т к программ на разных комп нах в разных
    // рабочих директориях  -АБСОЛЮТНЫЙ путь указывать нельзя!!!
