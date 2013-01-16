package tuwien.aic12.server.util;

import java.text.SimpleDateFormat;
import tuwien.aic12.dto.JobDTO;
import tuwien.aic12.model.Job;

/**
 *
 * @author vanjalee
 */
public class Util {

    public static SimpleDateFormat sdfDateDTO = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isNotEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static JobDTO createDTOforJob(Job job) {
        JobDTO jobDto = new JobDTO();
        jobDto.setJobId(job.getId());
        if (job.getRating().getRatingStart() != null) {
            jobDto.setStartTime(sdfDateDTO.format(job.getRating().getRatingStart()));
        }
        if (job.getRating().getRatingEnd() != null) {
            jobDto.setEndTime(sdfDateDTO.format(job.getRating().getRatingEnd()));
        }
        jobDto.setRating(job.getRating().getRating());
        jobDto.setSubject(job.getSubject());
        jobDto.setPrice(job.getPrice());
        jobDto.setFrom(job.getDateFrom());
        jobDto.setTo(job.getDateTo());
        if (job.getJobPayedStatus().equals(Job.JobPayedStatus.PAYED)) {
            jobDto.setPayed(true);
        } else {
            jobDto.setPayed(false);
        }
        if (job.getJobStatus().equals(Job.JobStatus.SCHEDULED)) {
            jobDto.setJobStatus(0);
        } else if (job.getJobStatus().equals(Job.JobStatus.RUNNING)) {
            jobDto.setJobStatus(1);
        } else {
            jobDto.setJobStatus(2);
        }
        return jobDto;
    }
}
