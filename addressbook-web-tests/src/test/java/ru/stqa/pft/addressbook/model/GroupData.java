package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.testng.annotations.Test;

@XStreamAlias("group")
public class GroupData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String name;
  private String header;
  private String footer;

  public int getId() { return id;  }
  public String getName() {
    return name;
  }
  public String getHeader() {
    return header;
  }
  public String getFooter() { return footer; }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;

  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }


  @Override //строковое представление отчета. Как создадим - так и увидим
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

}


//Конструктор без id Группа с неизвестным идентификатором
 /* public GroupData( String name, String header, String footer) {
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
  }*/