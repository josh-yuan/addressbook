package com.addressbook.api.exceptions;

public class NotFoundException extends AddressBookException {
    //public MessageNotFoundException(String topicName) {
    //    super(topicName);
    //}
    public NotFoundException() {
        super("no available message");
    }
}
