package tuwien.aic12.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bisanov
 */
@Entity(name = "rating")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   
    @Column(name = "rating")
    private Double rating;
    @Column(name = "duration")
    private Long duration;
    @Column(name = "ratingStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ratingStart;
    @Column(name = "ratingEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ratingEnd;
    @Column(name = "fee")
    private Double fee;

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getRatingStart() {
        return ratingStart;
    }

    public void setRatingStart(Date ratingStart) {
        this.ratingStart = ratingStart;
    }

    public Date getRatingEnd() {
        return ratingEnd;
    }

    public void setRatingEnd(Date ratingEnd) {
        this.ratingEnd = ratingEnd;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
