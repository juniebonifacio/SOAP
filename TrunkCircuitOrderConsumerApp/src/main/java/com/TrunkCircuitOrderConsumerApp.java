package com;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.tempuri.ITrunkCircuitOrderService;
import org.tempuri.TrunkCircuitOrderService;

public class TrunkCircuitOrderConsumerApp {

	public static void main(String[] args) throws MalformedURLException, Exception {
		TrunkCircuitOrderConsumerApp consumerApp = new TrunkCircuitOrderConsumerApp();
		
		consumerApp.approach1();
		
		consumerApp.approach2();
		
		consumerApp.approach3();
		
		consumerApp.approach4();
		
	}
	
	/**
	 * WSDL: Call Web Service by loading the WSDL on the JAR, not the WSDL from the URL endpoint.
	 * Endpoint: Set the endpoint in the code.
	 * Call: Use the port not the service to make the W/S call. This is needed to be able to pass the username and password
	 * 
	 */
	public void approach1() throws MalformedURLException, Exception {
		System.out.println("approach1()");
		String wsUrl = "http://localhost:8080/TrunkCircuitOrderService/TrunkCircuitOrderService";

		//For some reason, getResource(".") does not work it does not see the wsdl file from jar, probably because it is in the root dir, not in a folder named wsdl.
        //URL baseUrl = org.tempuri.TrunkCircuitOrderService.class.getResource(".");
        //URL inSwitchURL = new URL(baseUrl, "TrunkCircuitOrderService.wsdl");
		
		URL inSwitchURL = org.tempuri.TrunkCircuitOrderService.class.getResource("/TrunkCircuitOrderService.wsdl");
		System.out.println("inSwitchURL: " + inSwitchURL);
		if (inSwitchURL == null) {
			throw new Exception("No value");
		}
		// Updated the code again, it was using the endpoint in the WSDL not the one set in the code.
		// This is what it needs (use the port not the service to make the W/S call) ...
		TrunkCircuitOrderService service = new TrunkCircuitOrderService(inSwitchURL,
				new QName("http://tempuri.org/", "TrunkCircuitOrderService"));
		ITrunkCircuitOrderService port = service.getPort(ITrunkCircuitOrderService.class);
		BindingProvider prov = (BindingProvider) port;
		prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "WL0001/tvriuser");
		prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "F0rtun3!");
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsUrl);
		String result = port.cancelTrunkCircuitOrderProjectGroup("Test");
		System.out.println("approach1. result: " + result + "\n");
	}
	
	/**
	 * WSDL: Call Web Service by loading the WSDL on the JAR, not the WSDL from the URL endpoint.
	 * Endpoint: Read the endpoint in the WSDL too.
	 * Call: Use the port not the service to make the W/S call. This is needed to be able to pass the username and password
	 * 
	 */
	public void approach2() throws MalformedURLException, Exception {
		System.out.println("approach2()");
		System.out.println("To test this out, tried to change endpoint inside WSDL in the jar to a value expected to return an error: \n" + 
				"location=\"http://localhost:XXXX/TrunkCircuitOrderService/TrunkCircuitOrderService?wsdl");
		
		//String wsUrl = "http://localhost:8080/TrunkCircuitOrderService/TrunkCircuitOrderService";

		URL inSwitchURL = TrunkCircuitOrderService.class.getResource("/TrunkCircuitOrderService.wsdl");
		System.out.println("inSwitchURL: " + inSwitchURL);
		if (inSwitchURL == null) {
			throw new Exception("No value");
		}
		// Updated the code again, it was using the endpoint in the WSDL not the
		// one set in the code.
		// This is what it needs (use the port not the service to make the W/S
		// call) ...
		TrunkCircuitOrderService service = new TrunkCircuitOrderService(inSwitchURL,
				new QName("http://tempuri.org/", "TrunkCircuitOrderService"));
		ITrunkCircuitOrderService port = service.getPort(ITrunkCircuitOrderService.class);
		BindingProvider prov = (BindingProvider) port;
		prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "WL0001/tvriuser");
		prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "F0rtun3!");
		//prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsUrl);
		String result = port.cancelTrunkCircuitOrderProjectGroup("Test");
		System.out.println("approach2. result: " + result + "\n");
	}
	
	/**
	 * WSDL: Call Web Service by loading the WSDL on the JAR, not the WSDL from the URL endpoint.
	 * Endpoint: Read the endpoint in the WSDL too.
	 * Call: Use the service to make the W/S call. This approach does not allow you to pass username and password. Use port if you'll pass a username/password.
	 * 
	 */
	public void approach3() throws MalformedURLException, Exception {
		System.out.println("approach3()");
		String wsUrl = "http://localhost:8080/TrunkCircuitOrderService/TrunkCircuitOrderService";

		URL inSwitchURL = TrunkCircuitOrderService.class.getResource("/TrunkCircuitOrderService.wsdl");
		System.out.println("inSwitchURL: " + inSwitchURL);
		if (inSwitchURL == null) {
			throw new Exception("No value");
		}
		// Updated the code again, it was using the endpoint in the WSDL not the one set in the code.
		// This is what it needs (use the port not the service to make the W/S call) ...
		TrunkCircuitOrderService service = new TrunkCircuitOrderService(inSwitchURL,
				new QName("http://tempuri.org/", "TrunkCircuitOrderService"));
		
		String result = service.getBasicHttpBindingITrunkCircuitOrderService().cancelTrunkCircuitOrderProjectGroup("Test");
		System.out.println("approach3. result: " + result + "\n");
/*		ITrunkCircuitOrderService port = service.getPort(ITrunkCircuitOrderService.class);
		BindingProvider prov = (BindingProvider) port;
		prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "WL0001/tvriuser");
		prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "F0rtun3!");
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsUrl);
		String result = port.cancelTrunkCircuitOrderProjectGroup("Test");
		System.out.println("approach1. result: " + result + "\n");*/
	}
	
	/**
	 * WSDL: Call Web Service by loading the WSDL from the URL endpoint, not from the WSDL on the JAR.
	 * Endpoint: Read the endpoint in the Endpoint too.
	 * Call: Use the service to make the W/S call. This approach does not allow you to pass username and password. Use port if you'll pass a username/password.
	 * You need to make sure that you have the ?wsdl at the end of the endpoint.
	 */
	public void approach4() throws MalformedURLException, Exception {
		System.out.println("approach4()");
		String wsUrl = "http://localhost:8080/TrunkCircuitOrderService/TrunkCircuitOrderService?wsdl";

		URL inSwitchURL = new URL(wsUrl);
		System.out.println("inSwitchURL: " + inSwitchURL);
		if (inSwitchURL == null) {
			throw new Exception("No value");
		}
		// Updated the code again, it was using the endpoint in the WSDL not the one set in the code.
		// This is what it needs (use the port not the service to make the W/S call) ...
		TrunkCircuitOrderService service = new TrunkCircuitOrderService(inSwitchURL,
				new QName("http://tempuri.org/", "TrunkCircuitOrderService"));
		
		String result = service.getBasicHttpBindingITrunkCircuitOrderService().cancelTrunkCircuitOrderProjectGroup("Test");
		System.out.println("approach3. result: " + result);
/*		ITrunkCircuitOrderService port = service.getPort(ITrunkCircuitOrderService.class);
		BindingProvider prov = (BindingProvider) port;
		prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "WL0001/tvriuser");
		prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "F0rtun3!");
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsUrl);
		String result = port.cancelTrunkCircuitOrderProjectGroup("Test");
		System.out.println("approach1. result: " + result + "\n");*/
	}


}
