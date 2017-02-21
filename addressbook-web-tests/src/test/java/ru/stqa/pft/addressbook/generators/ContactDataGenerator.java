package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 21.02.2017.
 */
public class ContactDataGenerator {
  public  static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]); //передаем кол-во контактов
    File file = new File(args[1]); // адрес файла с данными

    //Генерация данных
    List<ContactData> contacts = generateContacts(count);

    //Сохранение данных в файл
    save(contacts, file);


  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Вова %s", i))
        .withLastname(String.format("Петров %s", i)).withAddress(String.format("Солнечная %s", i))
        .withHomePhone(String.format("+375 17 152%s",i)).withMobilePhone(String.format("+375 29 161%s",i))
        .withWorkPhone(String.format("+375 17 451%s", i)).withEmail(String.format("kkk@jj.%s", i))
        .withEmail2(String.format("lll@jj.%s", i)).withEmail3(String.format("hhh@jj.%s", i)));
    }
    return contacts;
  }
  private static void save (List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact: contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",contact.getFirstname()
              , contact.getLastname(), contact.getAddress(), contact.getHomePhone()
              , contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail()
              , contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();
  }



}


/**
 * Created by Администратор on 21.02.2017.
 */
/*public class GroupDataGenerator {
  public  static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]); //передаем кол-во групп
    File file = new File(args[1]); // адрес файла с данными

    //Генерация данных
    List<GroupData> groups = generateGroups(count);

    //Сохранение данных в файл
    save(groups, file);
  }

  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
    }
    return groups;
  }

  private static void save (List<GroupData> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupData group: groups){
      writer.write(String.format("%s;%s;%s\n",group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }
}*/
