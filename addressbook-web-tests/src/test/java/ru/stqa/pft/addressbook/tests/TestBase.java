package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationMenager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Администратор on 28.01.2017.
 */
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationMenager app
          = new ApplicationMenager(System.getProperty("browser"
          , BrowserType.CHROME)); //дефолтный браузер CHROME

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  //Сравнение данных из базы и UI У данных из базы выбираются только Id и name
  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();

      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }
   public void verifyContactListInUI() {

       if(Boolean.getBoolean("verifyUI")){
         Contacts dbContacts = app.db().contacts();
         System.out.println("***bd****" + dbContacts);
         Contacts uiContacts = app.contact().all();
         System.out.println("***ui****" + uiContacts);
         assertThat(uiContacts, equalTo(dbContacts.stream()
                 .map((g) -> new ContactData().withId(g.getId()).withLastname(g.getLastname())
                         .withFirstname(g.getFirstname()).withAddress(g.getAddress()))
                 .collect(Collectors.toSet())));
       }
     }
}
    /*if(Boolean.getBoolean("verifyUI")){
      Contacts dbContacts = app.db().con
      tacts();
      System.out.println("***bd****" + dbContacts);
      Contacts uiContacts = app.contact().all();
      System.out.println("***ui****" + uiContacts);
      assertThat(cleaned1(uiContacts), equalTo(mergeDBContacts(dbContacts));
      .stream()
             .map((g) -> new ContactData().withId(g.getId()).withLastname(g.getLastname())
             .withFirstname(g.getFirstname()).withAddress(g.getAddress()))
             .collect(Collectors.toSet())));
      }
    }*/

  /*private <Con> mergeDBContacts(Contacts dbContacts) {

  }*/



//assertThat(cleaned1(contactInforFromDetailesForm.getAllInformation()),equalTo
//            (mergeDadaFromEditPage(contactInforFromEditForm)));

/* public void verifyContactListInUI() {
    if(Boolean.getBoolean("verifyUI")){
      Contacts dbContacts = app.db().contacts();
      System.out.println("***bd****" + dbContacts);
      Contacts uiContacts = app.contact().all();
      System.out.println("***ui****" + uiContacts);
      assertThat(uiContacts, equalTo(dbContacts.stream()
             .map((g) -> new ContactData().withId(g.getId()).withLastname(g.getLastname())
             .withFirstname(g.getFirstname()).withAddress(g.getAddress()))
             .collect(Collectors.toSet())));
      }
    }*/