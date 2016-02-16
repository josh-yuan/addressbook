package com.addressbook.client;

public interface AddrBookClient {
	public void getTopics();

	public void createTopic(String topicName);
	
	public void deleteTopic(String topicName);
	
	public void describeTopic(String topicName);
	
	public void sendMessage(String topic, String message);
	
	public void pullMessage(String topic);
	
	public void deleteMessage(String topic, String messageId);
}
