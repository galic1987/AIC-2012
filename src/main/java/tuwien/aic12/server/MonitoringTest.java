/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server;

import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import tuwien.aic12.model.Customer;
import tuwien.aic12.model.Job;
import tuwien.aic12.server.dao.CustomerDao;
import tuwien.aic12.server.dao.JobDao;
import tuwien.aic12.server.service.CustomerService;
import tuwien.aic12.server.service.QueryService;

/**
 *
 * @author amin
 */
public class MonitoringTest {

    private static final String SERVER_HOME = "http://service.server.aic12.tuwien/";
    private static final QName SERVICE_CUSTOMER = new QName(SERVER_HOME,
            "CustomerService");
    private static final QName SERVICE_CUSTOMER_PORT = new QName(SERVER_HOME,
            "CustomerServicePort");
    private static final QName SERVICE_SEARCH = new QName(SERVER_HOME,
            "QueryService");
    private static final QName SERVICE_SEARCH_PORT = new QName(SERVER_HOME,
            "QueryServicePort");

    public static void main(String[] args) throws InterruptedException {
        JobDao jobDao = new JobDao();
        insertUser();
        while (1 == 1) {
            //Reading registered jobs and creating new thread

            List<Job> jobs = jobDao.readRegisteredJobs();

            for (Job job : jobs) {
                MonitorJob m = new MonitorJob();
                m.setJob(job);
                m.run();
            }
            Thread.sleep(10000);
        }
    }

    private static void insertUser() {
        Service service = Service.create(SERVICE_CUSTOMER);
        Service searchService = Service.create(SERVICE_SEARCH);
        // Endpoint Address
        String endpointAddress = "http://localhost:8084/aic12/CustomerService";
        String endpointSearchService = "http://localhost:8084/aic12/QueryService";
        // Add a port to the Service
        service.addPort(SERVICE_CUSTOMER_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        searchService.addPort(SERVICE_SEARCH_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointSearchService);

        CustomerService customerService = service.getPort(CustomerService.class);
        QueryService searchServiceImpl = searchService.getPort(QueryService.class);
        System.out.println(customerService.registerCustomer("test1", "test1", "Google"));
        String token = customerService.login("test1", "test1");
        System.out.println(token);
        String jobId = searchServiceImpl.registerService(token);
        System.out.println("JOB ID: " + jobId);
    }
}
