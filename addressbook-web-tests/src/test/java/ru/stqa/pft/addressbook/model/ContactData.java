package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")
public class ContactData {
@XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String address;
  private String email;
  private String email2;
  private String email3;
  private String group;
  private String homephone;
  private String mobilephone;
  private String workphone;
  private String allPhones;
  private String allEmails;
  private String allInformation;
  private File photo;

  public int getId() {return id;  }
  public String getFirstname() {
    return firstname;
  }
  public String getLastname() { return lastname;  }
  public String getAddress() {
    return address;
  }
  public String getHomePhone() {
    return homephone;
  }
  public String getMobilePhone() {
    return mobilephone;
  }
  public String getWorkPhone() { return workphone;  }
  public String getEmail() {
    return email;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getGroup() { return group;  }
  public String getAllPhones() { return allPhones;  }
  public String getAllEmails() { return allEmails;  }
  public String getAllInformation() { return allInformation;  }
  public File getPhoto() {    return photo;  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withAllInformation(String allInformation) {
    this.allInformation = allInformation;
    return this;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

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

  public ContactData withHomePhone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilePhone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }
  public ContactData withWorkPhone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

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


