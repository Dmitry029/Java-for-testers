package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public  static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jComander = new JCommander(generator);
    try {
      jComander.parse(args);
    } catch (ParameterException ex){
      jComander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    //Генерация данных
    List<ContactData> contacts = generateContacts(count);

    //Сохранение данных в файл
    save(contacts, new File(file));
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


