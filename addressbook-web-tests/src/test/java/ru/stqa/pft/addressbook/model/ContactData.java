package ru.stqa.pft.addressbook.model;

/**
 * Created by Администратор on 28.01.2017.
 */
public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String address;
  private String homephone;
  private String mobilephone;
  private String group;


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }


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

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
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



/* //второй конструктор дез id   l4_m8
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

  }*/


