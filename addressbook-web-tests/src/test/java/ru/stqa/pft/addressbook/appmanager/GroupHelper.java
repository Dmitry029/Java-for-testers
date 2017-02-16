package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
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


  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();  //сделать click по id
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  //Создаеие группы
  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }
  //Модификация группы
  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);// заполняет форму
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  // Новый метод удаления l5_m5
  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }


  //Проверка наличия группы. Ищет чекбокс
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }


  // Подсчет колличества групп на странице Groups
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupCache = null;

  // Метод заполнения списка данными
  public Groups all() {
    if (groupCache != null){
    return new Groups(groupCache);//проверка пустой ли cache?
    }
    groupCache = new Groups();
    // Извлечение данных для заполнения списка со страницы web приложения
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //найти все элементы с тегом span и класс groupИзвле
    for (WebElement element : elements){        //element пробегает по списку elements
      String name = element.getText();          // из каждого элемента получаем текст - имя группы
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));//ПОЛУЧЕНИЕ ИДЕНТИФИКАТОРА ID затем он передается в конструктор
      groupCache.add(new GroupData().withId(id).withName(name));                        // Добавляем созданный объект в МНОЖЕСТВО
    }
    return new Groups(groupCache);
  }

}



//    Методы удаленные в l5_m5
/*Удаление группы
  public void delete(int index) {
    selectGroup(index);
    deleteSelectedGroups();
    returnToGroupPage();
  }*/
/*public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();  //сделать click по элементу index
  }*/

// Метод заполнения списка данными
 /* public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();       //Создание списка который будем заполнять
    // Извлечение данных для заполнения списка со страницы web приложения
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //найти все элементы с тегом span и класс groupИзвле
    for (WebElement element : elements){        //element пробегает по списку elements
      String name = element.getText();          // из каждого элемента получаем текст - имя группы
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));//ПОЛУЧЕНИЕ ИДЕНТИФИКАТОРА ID затем он передается в конструктор
      groups.add(new GroupData().withId(id).withName(name));                        // Добавляем созданный объект в список
    }
    return groups;
  }*/