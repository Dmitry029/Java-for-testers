package ru.stqa.pft.addressbook.model;

import org.testng.annotations.Test;

public class GroupData {
  private int  id; // Модификатор final означает что значение присваевоемое конструктором неизменно
  private final String name;
  private final String header;
  private final String footer;

  //Конструктор без id Группа с неизвестным идентификатором
  public GroupData( String name, String header, String footer) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  //Конструктор
  public GroupData(int id, String name, String header, String footer) {
    this.id = id;   //присвоение значения параметра в атрибут
    this.name = name;
    this.header = header;
    this.footer = footer;
  }
  public int getId() { return id;  }
  public String getName() {
    return name;
  }
  public String getHeader() {
    return header;
  }
  public String getFooter() {
    return footer;
  }

  public void setId(int id) { this.id = id;  }


  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

}
