package com.addressbook.client;

public class AddrBookClientFactory {
	public static AddrBookClient getClient(String clientType, String serviceEndpoint) throws Exception {
		if (clientType.equals("JerseyClient")) {
			return new AddrBookJerseyClient(serviceEndpoint);
		}
		else {
			throw new Exception("Invalid Msgas Client Type: " + clientType);
		}
	}
}
