package tuwien.aic12.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import tuwien.aic12.server.service.CustomerService;

public final class Client {

    private static final String SERVER_HOME = "http://service.server.aic12.tuwien/";
    private static final QName SERVICE_CUSTOMER = new QName(SERVER_HOME,
            "customerService");
    private static final QName SERVICE_CUSTOMER_PORT = new QName(SERVER_HOME,
            "customerServicePort");
    private static final QName SERVICE_SEARCH = new QName(SERVER_HOME,
            "searchService");
    private static final QName SERVICE_SEARCH_PORT = new QName(SERVER_HOME,
            "searchServicePort");

    private Client() {
    }

    public static void main(String args[]) throws Exception {

        Service service = Service.create(SERVICE_CUSTOMER);
        // Endpoint Address
        String endpointAddress = "http://localhost:8084/aic12/customerService";
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        CustomerService customerService = service.getPort(CustomerService.class);
        System.out.println(customerService.registerCustomer("test1", "test1"));
        String token = customerService.login("test1", "test1");
        System.out.println(token);
        System.out.println(customerService.logout(token));
    }
}
