package ru.stqa.pft.addressbook.model;

/**
 * Created by Администратор on 28.01.2017.
 */
public class ContactData {

  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homephone;
  private final String mobilephone;
  private String group;

  public ContactData(String firstname, String lastname, String address,
                     String homephone, String mobilephone, String group
  ) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getGroup() {
    return group;
  }
}






