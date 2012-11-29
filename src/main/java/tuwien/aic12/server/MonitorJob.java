/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import tuwien.aic12.model.Job;
import tuwien.aic12.server.service.*;

/**
 *
 * @author Amin
 */
public class MonitorJob implements Runnable {
    
    private Job job;
    private String SERVER_HOME = "http://service.server.aic12.tuwien/";
    private QName SERVICE_SEARCH = new QName(SERVER_HOME,
            "MonitoringService");
    private QName MONITOR_SERVICE_PORT = new QName(SERVER_HOME,
            "MonitoringServicePort");
    
    @Override
    public void run() {
        
        Service monitorService = Service.create(SERVICE_SEARCH);
        // Endpoint Address
        String endpointSearchService = "http://localhost:8084/aic12/monitoringService";
        // Add a port to the Service
        monitorService.addPort(MONITOR_SERVICE_PORT, SOAPBinding.SOAP11HTTP_BINDING, endpointSearchService);
        
        MonitoringService monitorServiceImpl = monitorService.getPort(MonitoringService.class);
        monitorServiceImpl.analyse(job.getCustid(), job.getId());
        
//        CustomerDao cd = new CustomerDao();
//        Customer cust = cd.read(job.getCustid());
//        
//        TwitterService twitterService = new TwitterService();
//        Double result;
//        Date start = new Date();
//        result = twitterService.getOpinionOf(cust.getCompany_name());
//         Date end = new Date();
//        // Evaluation usually lasts over one minute
//        long duration = end.getTime() - start.getTime();
//        
//        RatingDao rd = new RatingDao();
//        Rating rating = new Rating();
//        rating.setCustomer(cust.getId());
//        rating.setJob(job.getId());
//        rating.setRating(result);
//        rating.setFee(10);
//        rating.setDuration(duration);
//        rd.create(rating);
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }
    
    
    
}
