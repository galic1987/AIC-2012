/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.aic12.server;

import java.util.List;
import tuwien.aic12.model.Job;
import tuwien.aic12.server.dao.JobDao;

/**
 *
 * @author amin
 */
public class MonitoringTest {
    
   
    
    public static void main(String[] args) throws InterruptedException {
        JobDao jobDao = new JobDao();
        //while(1==1) {
            //Reading registered jobs and creating new thread
            
            List<Job> jobs = jobDao.readRegisteredJobs();
            
            for (Job job : jobs) {
                MonitorJob m = new MonitorJob();
                m.setJob(job);
                m.run();
            }
            Thread.sleep(10000);
       // }
    }
    
}
