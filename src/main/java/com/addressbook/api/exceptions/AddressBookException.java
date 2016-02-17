package com.addressbook.api.exceptions;

public abstract class AddressBookException extends RuntimeException {
    public AddressBookException(String name) {
        super(name);
    }
}
