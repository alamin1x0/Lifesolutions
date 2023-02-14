package com.lifesolutions.bd.Models;

import com.github.tamir7.contacts.Contact;

public class SyncPeople {
    Contact contact;
    String id;

    public SyncPeople(Contact contact, String id) {
        this.contact = contact;
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
