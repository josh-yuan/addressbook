package com.addressbook.exceptions;

public class InternalErrorException extends AddressBookException {
    public InternalErrorException(String why) {
        super(why);
    }
}
