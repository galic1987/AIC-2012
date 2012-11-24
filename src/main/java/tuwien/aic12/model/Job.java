package tuwien.aic12.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @Column(name = "date_from")
    private String dateFrom;
    @Column(name = "date_to")
    private String dateTo;
    @Column(name = "intervl")
    private Double intervl;
    @Column(name = "registred")
    private Boolean registred;
    @Column(name = "custid")
    private Long custid;
    @Column(name = "status")
    private Integer status;

    public Job() {
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
    public Double getInterval() {
        return intervl;
    }

    public void setInterval(Double interval) {
        this.intervl = interval;
    }
    public Boolean getRegistred() {
        return registred;
    }

    public void setRegistred(Boolean registred) {
        this.registred = registred;
    }

    /**
     * @return the custid
     */
    public Long getCustid() {
        return custid;
    }

    /**
     * @param custid the custid to set
     */
    public void setCustid(Long custid) {
        this.custid = custid;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
