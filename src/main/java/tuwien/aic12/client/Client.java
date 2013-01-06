package tuwien.aic12.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import tuwien.aic12.server.service.AnalyserService;
import tuwien.aic12.server.service.CustomerService;

public final class Client {

    private static final String SERVER_HOME = "http://service.server.aic12.tuwien/";
    private static final QName SERVICE_CUSTOMER = new QName(SERVER_HOME,
            "CustomerService");
    private static final QName SERVICE_CUSTOMER_PORT = new QName(SERVER_HOME,
            "CustomerServicePort");
    private static final QName SERVICE_ANALYSER = new QName(SERVER_HOME,
            "AnalyserService");
    private static final QName SERVICE_ANALYSER_PORT = new QName(SERVER_HOME,
            "AnalyserServicePort");
    
    private Client() {
    }

    public static void main(String args[]) throws Exception {
        performTest();

    }

    private static void performTest() {
        System.out.println("\n\n\n CustomerService Test\n\n\n");
        Service service = Service.create(SERVICE_CUSTOMER);
        // Endpoint Address
        String endpointAddress = "http://localhost:8084/AIC/CustomerService";
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        CustomerService customerService = service.getPort(CustomerService.class);
        
        String token;
        String info;
        
        info = customerService.registerCustomerInitial("vanja.bisanovic@gmail.com", "vanjalee", "qweasdzxc", "EBCONT  Enterprise Technologies", new Long(10000));
        token = customerService.login("vanjalee", "qweasdzxc");
        
        System.out.println("RECEIVED INFO : " + info);
        System.out.println("RECEIVED TOKEN : " + token);
        
        info = customerService.logout(token);
        
        System.out.println("\n\n\nCustomerService Test Finished.\n\n\n");
        
        token = customerService.login("vanjalee", "qweasdzxc");
        
        System.out.println("\n\n\nAnalyserService Test\n\n\n");
        service = Service.create(SERVICE_ANALYSER);
        // Endpoint Address
        endpointAddress = "http://localhost:8084/AIC/AnalyserService";
        // Add a port to the Service
        service.addPort(SERVICE_ANALYSER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        System.out.println("Subject of Twitter Sentiment Analysis : Hugh Hefner");
        AnalyserService analyserService = service.getPort(AnalyserService.class);
        System.out.println(analyserService.analyse("Hugh Hefner", token));
        System.out.println("\n\n\nAnalyserService Test Finished.\n\n\n");
    }
 
}
