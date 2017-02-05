package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoAddNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan12","Pomidorov",
                "Minsk, Gagarina 21/14","+375 17 5544120",
                "+375 29 6222552","test9"), true);

        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactsPage();
    }
}


