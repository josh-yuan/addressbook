package com.addressbook.api.exceptions;

public class NotFoundException extends MsgasException {
    //public MessageNotFoundException(String topicName) {
    //    super(topicName);
    //}
    public NotFoundException() {
        super("no available message");
    }
}
