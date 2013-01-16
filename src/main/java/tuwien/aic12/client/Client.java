package tuwien.aic12.client;

import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import tuwien.aic12.dto.JobDTO;
import tuwien.aic12.server.service.AnalyserService;
import tuwien.aic12.server.service.BillingService;
import tuwien.aic12.server.service.CustomerService;

public final class Client {

    private static final String SERVER_HOME = "http://service.server.aic12.tuwien/";
    private static final String ENDPOINT_ADDRESS = "http://localhost:8084/AIC/";
    private static final String SCUSTOMER = "CustomerService";
    private static final String SANALYSER = "AnalyserService";
    private static final String SBILLING = "BillingService";
    private static final QName SERVICE_CUSTOMER = new QName(SERVER_HOME, SCUSTOMER);
    private static final QName SERVICE_CUSTOMER_PORT = new QName(SERVER_HOME, "CustomerServicePort");
    private static final QName SERVICE_ANALYSER = new QName(SERVER_HOME, SANALYSER);
    private static final QName SERVICE_ANALYSER_PORT = new QName(SERVER_HOME, "AnalyserServicePort");
    private static final QName SERVICE_BILLING = new QName(SERVER_HOME, SBILLING);
    private static final QName SERVICE_BILLING_PORT = new QName(SERVER_HOME, "BillingServicePort");
    private static String token;

    private Client() {
    }

    public static void main(String args[]) throws Exception {
        String email = "vanja.bisanovic@gmail.com";
        String username = "vanjalee";
        String password = "qweasdzxc";
        String company = "EBCONT  Enterprise Technologies";
        Long duration = new Long(999999999);
        
        // Read from db and enter for testing purposes
        // String token = "51ldfakfgcufatkugf6rfm9flb";
        // Long jobId = new Long(3);
        // Double amount = 2.75;
        // Double billAmount = 5.75;

        testCustomerRegisterInitial(email, username, password, company, duration);
        testCustomerLogin(username, password);

        testSeach("Dana White", token);
        // testSeachFromTo("Dana White", "2010-12-30", "2010-12-31", token);
        testSeach("Jon Jones", token);
        // testSeachFromTo("Jon Jones", "2012-12-30", "2012-12-31", token);

        //testBillingGetBill(token);
        // testBillingGetSingleJobBill(jobId, token);

        // testBillingPayRating(jobId, amount - 1);
        // testBillingPayRating(jobId, amount);
        
        // testBillingPayBill(token, billAmount - 1);
        // testBillingPayBill(token, billAmount);
        
        testCustomerGetJobs(token);
    }

    private static void testCustomerLogin(String username, String password) {
        System.out.println("\n\n\n CustomerService Test\n\n\n");
        Service service = Service.create(SERVICE_CUSTOMER);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SCUSTOMER;
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        CustomerService customerService = service.getPort(CustomerService.class);
        token = customerService.login(username, password);
        System.out.println("Server : " + token);
        System.out.println("\n\n\nCustomerService Test Finished.\n\n\n");
    }

    private static void testCustomerLogout(String token) {
        System.out.println("\n\n\n CustomerService Test\n\n\n");
        Service service = Service.create(SERVICE_CUSTOMER);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SCUSTOMER;
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        CustomerService customerService = service.getPort(CustomerService.class);
        String feedback = customerService.logout(token);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nCustomerService Test Finished.\n\n\n");
    }

    private static void testCustomerGetJobs(String token) {
        System.out.println("\n\n\n CustomerService getJobs\n\n\n");
        Service service = Service.create(SERVICE_CUSTOMER);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SCUSTOMER;
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        CustomerService customerService = service.getPort(CustomerService.class);
        List<JobDTO> results = customerService.getJobs(token);
        System.out.println("Server : Size of the result list : " + results.size());
        for(JobDTO jobDto : results) {
            System.out.println(jobDto.toString());
        }
        System.out.println("\n\n\nCustomerService getJobs Test Finished.\n\n\n");
    }
    
    private static void testCustomerRegisterInitial(String email, String username, String password, String company, Long duration) {
        System.out.println("\n\n\n CustomerService Test\n\n\n");
        Service service = Service.create(SERVICE_CUSTOMER);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SCUSTOMER;
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        CustomerService customerService = service.getPort(CustomerService.class);
        String feedback = customerService.registerCustomerInitial(email, username, password, company, duration);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nCustomerService Test Finished.\n\n\n");
    }

    private static void testCustomerRegister(String company, Long duration) {
        System.out.println("\n\n\n CustomerService Test\n\n\n");
        Service service = Service.create(SERVICE_CUSTOMER);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SCUSTOMER;
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        CustomerService customerService = service.getPort(CustomerService.class);
        String feedback = customerService.registerCustomer(company, duration);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nCustomerService Test Finished.\n\n\n");
    }

    private static void testSeach(String subject, String token) {
        System.out.println("\n\n\nAnalyserService Test\n\n\n");
        Service service = Service.create(SERVICE_ANALYSER);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SANALYSER;
        // Add a port to the Service
        service.addPort(SERVICE_ANALYSER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        System.out.println("Subject of Twitter Sentiment Analysis : " + subject);
        AnalyserService analyserService = service.getPort(AnalyserService.class);
        String feedback = analyserService.analyse(subject, token);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nAnalyserService Test Finished.\n\n\n");
    }

    private static void testSeachFromTo(String subject, String from, String to, String token) {
        System.out.println("\n\n\nAnalyserService Test\n\n\n");
        Service service = Service.create(SERVICE_ANALYSER);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SANALYSER;
        // Add a port to the Service
        service.addPort(SERVICE_ANALYSER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        System.out.println("Subject of Twitter Sentiment Analysis : " + subject);
        AnalyserService analyserService = service.getPort(AnalyserService.class);
        String feedback = analyserService.analyseFromTo(subject, from, to, token);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nAnalyserService Test Finished.\n\n\n");
    }

    private static void testBillingGetBill(String token) {
        System.out.println("\n\n\nBilling Test getBill\n\n\n");
        Service service = Service.create(SERVICE_BILLING);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SBILLING;
        // Add a port to the Service
        service.addPort(SERVICE_BILLING_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        System.out.println("Getting bill for token : " + token);
        BillingService billingService = service.getPort(BillingService.class);
        String feedback = billingService.getBill(token);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nBilling Test Finished.\n\n\n");
    }

    private static void testBillingPayRating(Long ratingId, Double amount) {
        System.out.println("\n\n\n Test getBill Finished.\n\n\n");
        Service service = Service.create(SERVICE_BILLING);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SBILLING;
        // Add a port to the Service
        service.addPort(SERVICE_BILLING_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        System.out.println("Pay rating : " + ratingId + " - " + amount);
        BillingService billingService = service.getPort(BillingService.class);
        String feedback = billingService.payRating(ratingId, amount);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nBilling Test getBill Finished.\n\n\n");
    }

    private static void testBillingGetSingleJobBill(long jobId, String token) {
        System.out.println("\n\n\nTest getSingleJobBill\n\n\n");
        Service service = Service.create(SERVICE_BILLING);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SBILLING;
        // Add a port to the Service
        service.addPort(SERVICE_BILLING_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        System.out.println("Getting bill for token : " + token + ", and jobID : " + jobId);
        BillingService billingService = service.getPort(BillingService.class);
        String feedback = billingService.getSingleJobBill(jobId, token);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nBilling Test getSingleJobBill Finished.\n\n\n");
    }

    private static void testBillingPayBill(String token, Double amount) {
        System.out.println("\n\n\nTest payBill\n\n\n");
        Service service = Service.create(SERVICE_BILLING);
        // Endpoint Address
        String endpointAddress = ENDPOINT_ADDRESS + SBILLING;
        // Add a port to the Service
        service.addPort(SERVICE_BILLING_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        System.out.println("Paying bill for token : " + token + " - " + amount);
        BillingService billingService = service.getPort(BillingService.class);
        String feedback = billingService.payBill(amount, token);
        System.out.println("Server : " + feedback);
        System.out.println("\n\n\nBilling Test payBill Finished.\n\n\n");
    }
    
}
