package com.addressbook.exceptions;

public abstract class AddressBookException extends RuntimeException {
    public AddressBookException(String name) {
        super(name);
    }
}
