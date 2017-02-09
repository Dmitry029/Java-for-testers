package ru.stqa.pft.addressbook.appmanager;

import com.sun.javafx.binding.ExpressionHelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 28.01.2017.
 */
public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();  //сделать click по элементу index
    //click(By.name("selected[]"));      //до l4_m4
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }


  //Создаеие группы
  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  //Проверка наличия группы. Ищет чекбокс
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }


  // Подсчет колличества групп на странице Groups
  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


   // Метод заполнения списка данными
  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<GroupData>();       //Создание списка который будем заполнять
    // Извлечение данных для заполнения списка со страницы web приложения
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //найти все элементы с тегом span и класс groupИзвле
    for (WebElement element : elements){        //element пробегает по списку elements
      String name = element.getText();          // из каждого элемента получаем текст - имя группы
      GroupData group =  new GroupData(name, null, null); //создаем объект типа GroupData. null - т к значения не известны
      groups.add(group);                        // Добавляем созданный объект в список
    }
    return groups;
  }
}




