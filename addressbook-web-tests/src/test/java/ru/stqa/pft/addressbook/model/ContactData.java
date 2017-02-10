package ru.stqa.pft.addressbook.model;

/**
 * Created by Администратор on 28.01.2017.
 */
public class ContactData {

  private int id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homephone;
  private final String mobilephone;
  private String group;

  //второй конструктор дез id   l4_m8
  public ContactData(String firstname, String lastname, String address,
                     String homephone, String mobilephone, String group
  ) {
    this.id = Integer.MAX_VALUE; //присвоение параметра в атрибут l4_m8
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.group = group;    // Добавление группы в конструктор l3_m8. Убрал из скобок String group

  }

  public ContactData(int id,String firstname, String lastname, String address,
                     String homephone, String mobilephone, String group
  ) {
    this.id = id; //присвоение параметра в атрибут
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.group = group;    // Добавление группы в конструктор l3_m8. Убрал из скобок String group

  }

  public void setId(int id) {    this.id = id;  }
  public int getId() {return id;  }
  public String getFirstname() {
    return firstname;
  }
  public String getLastname() { return lastname;  }
  public String getAddress() {
    return address;
  }
  public String getHomephone() {
    return homephone;
  }
  public String getMobilephone() {
    return mobilephone;
  }
  public String getGroup() { return group;  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}






