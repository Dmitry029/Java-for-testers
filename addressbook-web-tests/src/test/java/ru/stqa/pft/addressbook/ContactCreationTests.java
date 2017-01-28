package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        app.gotoAddNew();
        app.enterFirstName();
        app.enterLastName();
        app.enterAddress();
        app.enterPhoneNumber();
        app.enterMobileNumber();
        app.submitContact();
        app.returnToContacts();
    }
}
