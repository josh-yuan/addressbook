package com.addressbook.exceptions;

public class NotFoundException extends AddressBookException {
    //public MessageNotFoundException(String topicName) {
    //    super(topicName);
    //}
    public NotFoundException() {
        super("no available message");
    }
}
