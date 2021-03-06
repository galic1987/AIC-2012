package tuwien.aic12.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author bisanov
 */
@Entity(name = "job")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "subject")
    private String subject;
    @Column(name = "dateFrom")
    private String dateFrom;
    @Column(name = "dateTo")
    private String dateTo;
    @Column(name = "jobStatus")
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    @Column(name = "jobPayedStatus")
    @Enumerated(EnumType.STRING)
    private JobPayedStatus jobPayedStatus;
    @JoinColumn(name = "customerId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "ratingId")
    private Rating rating;
    @Column(name = "price")
    private Double price;

    public enum JobPayedStatus {

        UNPAYED, PAYED;
    }

    public enum JobStatus {

        SCHEDULED, RUNNING, FINISHED;
    }

    public Job() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public JobPayedStatus getJobPayedStatus() {
        return jobPayedStatus;
    }

    public void setJobPayedStatus(JobPayedStatus jobPayedStatus) {
        this.jobPayedStatus = jobPayedStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
