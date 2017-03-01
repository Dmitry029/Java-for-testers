package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;


@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

  @XStreamOmitField //это поле пропускается для xml
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  @Expose //поля для создания jaso файла
  private String firstname;

  @Column(name = "lastname")
  @Expose
  private String lastname;

  @Column (name = "address")
  @Type(type = "text")
  @Expose
  private String address;

  @Column (name = "email")
  @Type(type = "text")
  @Expose
  private String email;

  @Column(name = "email2")
  @Type(type = "text")
  @Expose
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  @Expose
  private String email3;

  @Column(name = "home")
  @Type(type = "text")
  @Expose
  private String homephone;

  @Column(name = "mobile")
  @Type(type = "text")
  @Expose
  private String mobilephone;

  @Column(name = "work")
  @Type(type = "text")
  @Expose
  private String workphone;

  @Column (name = "photo")
  @Type(type = "text")
  private String photo;


  @Transient //при выборке из бвзы эти атрибуты будут пропускаться
  private String group;
  @Transient
  private String allPhones;
  @Transient
  private String allEmails;
  @Transient
  private String allInformation;


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
 // public File getPhoto() {return new File(photo);  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
    if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
    return workphone != null ? workphone.equals(that.workphone) : that.workphone == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
    result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
    result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
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


