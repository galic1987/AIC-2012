package tuwien.aic12.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author galic1987
 */
@XmlRootElement(name = "JobDTO")
public class JobDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long jobId;
    private String subject;
    private String to;
    private String from;
    private Boolean payed;
    private Double price;
    private String startTime;
    private String endTime;
    private Double rating;
    /**
     * scheduled = 0, running = 1, finished = 2
     */
    private Integer jobStatus;

    public JobDTO() {
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public String toString() {
        return "JobDTO{" + "jobId=" + jobId + ", subject=" + subject + ", to=" + to + ", from=" + from + ", payed=" + payed + ", price=" + price + ", startTime=" + startTime + ", endTime=" + endTime + ", rating=" + rating + ", jobStatus=" + jobStatus + '}';
    }
}
