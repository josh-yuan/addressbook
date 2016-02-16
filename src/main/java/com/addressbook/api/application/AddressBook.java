package com.addressbook.api.application;

import java.util.Collection;

import com.addressbook.api.application.model.Subscriber;
import com.addressbook.api.application.model.Message;
import com.addressbook.config.AddressBookConfiguration;
public interface AddressBook {
	public void loadConfiguration(AddressBookConfiguration configuration);
	
    public Collection<String> getTopics(String tenantId);
    public Collection<String> describeTopic(String tenantId, String topicName);
    public void createTopic(String tenantId, String name);
    public void deleteTopic(String tenantId, String name);
    
    public String sendMessage(String tenantId, String topicName, String message);
    public Message pullMessage(String tenantId, String topicName);     // only for P2P message polling
    public boolean deleteMessage(String tenantId, String topicName, String messageId);
    
    public Subscriber createSubscriber(String subscriberName, String endpoint, Collection<String> topics);
    public Subscriber getSubscriberInfo(String subscriberId);
    public boolean deleteSubscriber(String subscriberId);
    public Collection<String> subscribeTopic(String subscriberId, Collection<String> topics);
    public Collection<String> unsubscribeTopic(String subscriberId, Collection<String> topics);
}
