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
 * @author alexsiba
 */
public class MonitorThread extends Thread{
    
    JobDao jobDao = new JobDao();
    List<Job> jobList= jobDao.readRegisteredJobs();
    
    
    
}
