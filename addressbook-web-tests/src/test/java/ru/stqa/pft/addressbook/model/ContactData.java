package ru.stqa.pft.addressbook.model;

/**
 * Created by Администратор on 28.01.2017.
 */
public class ContactData {

  private final String id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homephone;
  private final String mobilephone;
  private String group;

  //второй конструктор дез id
  public ContactData(String firstname, String lastname, String address,
                     String homephone, String mobilephone, String group
  ) {
    this.id = null; //присвоение параметра в атрибут
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.group = group;    // Добавление группы в конструктор l3_m8. Убрал из скобок String group

  }

  public ContactData(String id,String firstname, String lastname, String address,
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

  public String getId() {return id;  }
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
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}






