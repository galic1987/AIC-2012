package tuwien.aic12.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import tuwien.aic12.server.service.UserService;

public final class Client {

	private static final String SERVER_HOME = "http://service.server.aic12.tuwien/";

	private static final QName SERVICE_USER = new QName(SERVER_HOME,
			"userService");
	private static final QName SERVICE_USER_PORT = new QName(SERVER_HOME,
			"userServicePort");

	private static final QName SERVICE_SEARCH = new QName(SERVER_HOME,
			"searchService");
	private static final QName SERVICE_SEARCH_PORT = new QName(SERVER_HOME,
			"searchServicePort");

	private Client() {
	}

	public static void main(String args[]) throws Exception {
		
		Service service = Service.create(SERVICE_USER);
		// Endpoint Address
		String endpointAddress = "http://localhost:8084/aic12/userService";
		// Add a port to the Service
		service.addPort(SERVICE_USER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

		UserService userService = service.getPort(UserService.class);
		System.out.println(userService.registerUser("test1", "test1"));
                String token = userService.login("test1", "test1");
                System.out.println(token);
                System.out.println(userService.logout(token));
	}	

}
