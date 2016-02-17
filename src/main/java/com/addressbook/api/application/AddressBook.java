package com.addressbook.api.application;

import java.util.Collection;

import com.addressbook.api.application.model.Subscriber;
import com.addressbook.api.application.model.Message;
import com.addressbook.config.AddressBookConfiguration;
public interface AddressBook {
	public void loadConfiguration(AddressBookConfiguration configuration);
	
    public void lookupByLastName(String lastName);
}
